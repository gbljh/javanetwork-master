/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.chmnu.ki.networks.tcp.simple;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author svpuzyrov
 */
public class SimpleMultiClientAppLab06 {
    public static <e> void main(String[] args) throws InterruptedException, IOException {
        String[] testData = {
            "Hello, I'm here!",
        };
        Scanner in = new Scanner(System.in);
        System.out.print("Input a host range: ");
        String host = in.next();
        int port = 5558;
        for(int i = 0;i<256;i++) {
            try {
                ExecutorService executor = Executors.newFixedThreadPool(10);
                for (String line : testData) {
                    executor.submit(new SimpleTCPClient(host+i, port, line));
                }

                executor.shutdown();
            } catch (Exception e) {
            }
        }
    }
}
