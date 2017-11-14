/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package installer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author root
 */
public class Common {
    public static boolean ping(byte[] ip) throws UnknownHostException, IOException{
        InetAddress inet = InetAddress.getByAddress(ip);
        if(inet.isReachable(5000)){
            return true;
        }
        return false;
    }
}
