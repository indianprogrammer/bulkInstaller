/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package installer;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.InputStream;

/**
 *
 * @author root
 */
public class SshCaller extends Thread{
    private String host,user,password,flag;
    
    public SshCaller(String hostname, String username, String pass, String flags){
        host = hostname;
        user = username;
        password = pass;
        flag = flags;
    }
    
    @Override
    public void run(){
        switch(flag){
            case "provision" :{
                provision();
            }
        }
    }
    
    private boolean provision(){
        //String provisionCommand = "wget http://139.59.77.240/install/firmware.sh && chmod a+x firmware.sh && ./firmware.sh";
        String provisionCommand = "ifconfig";
        
        return ssh(host,user,password,provisionCommand);
    }
    
    private boolean ssh(String host, String user, String password, String command){
        System.out.println(host + "--" + user + "--" + password + "--" + command );
        //return false;
       
        try {
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            System.out.println("Connected");

            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);

            InputStream in = channel.getInputStream();
            channel.connect();
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    System.out.print(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    System.out.println("exit-status: " + channel.getExitStatus());
                    break;
                }
                try {
                    //Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }
            channel.disconnect();
            session.disconnect();
            System.out.println("success IP --" + host);
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("failed IP --" + host);
        }
        return false;
             
    }

}
