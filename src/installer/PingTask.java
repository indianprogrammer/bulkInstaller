/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package installer;

import java.net.InetAddress;
import java.util.concurrent.Callable;

public class PingTask implements Callable<PingResult> {

    private String ipAddress;

    public PingTask(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public PingResult call() {
        InetAddress inet = null;
        try {
            inet = InetAddress.getByName(ipAddress);
//      int resultCode = inet.isReachable(5000) ? 0 : -1;
//      return new PingResult(ipAddress, resultCode);
            return new PingResult(ipAddress, inet.isReachable(5000));
        } catch (Exception e) {
            e.printStackTrace();
            return new PingResult(ipAddress, false);
        }
    }
}
