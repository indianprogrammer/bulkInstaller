/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package installer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PingParallel {

    //public static void main(String[] args) {
    public static List<Future<PingResult>> PingParallel(String ipAddress, int totalIps) {
        //int totalIps = 255;
        ExecutorService executor = Executors.newFixedThreadPool(totalIps);
        List<Future<PingResult>> list = new ArrayList<Future<PingResult>>();
        Callable<PingResult> callable = null;
        for(int i=1; i< totalIps; i++){
            callable = new PingTask(ipAddress+"."+i); // Get the ipAddres buttons[i].getText());
            Future<PingResult> future = executor.submit(callable);
            list.add(future);
        }
        
        executor.shutdown();
        return list;
    }
}