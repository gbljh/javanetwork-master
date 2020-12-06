package ua.edu.chmnu.ki.networks.udp.multicast;

import java.io.IOException;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static ua.edu.chmnu.ki.networks.common.CmdLineParser.extractValue;


public class MultiCastSenderAppLab07 {

    public static void main(String[] args) throws SocketException, IOException {
        ExecutorService service = Executors.newCachedThreadPool();

        String[] group = new String[3];
        group[0] = "224.0.0.1";
        group[1] = "224.0.0.2";
        group[2] = "224.0.0.3";
        int[] delay = new int[3];
        delay[0] = 3;
        delay[1] = 5;
        delay[2] = 10;
        // До 224.0.0.1 - відправляє в час кратний 3 секундам
        // До 224.0.0.2 - відправляє в час кратний 5 секундам
        // До 224.0.0.3 - відправляє в час кратний 10 секундам
        int port = 5559;
        for(int index = 0;index<group.length;index++) {
            for (int i = 0; i < args.length; ++i) {
                String value = extractValue(args[i], "-g:");
                if (value != null) {
                    group[index] = value;
                    continue;
                }

                value = extractValue(args[i], "-p:");
                if (value != null) {
                    port = Integer.parseInt(value);
                }

            }

            int finalIndex = index;
            MultiCastSender sender = new MultiCastSender(delay[index],group[index], port).setAction(() -> {
                String toSend = "["+group[finalIndex]+"] MSG";
                return toSend.getBytes();
            });
            service.submit(sender);
//            try
//            {
//                Thread.sleep(100000000);
//            }
//            catch(InterruptedException ex)
//            {
//                Thread.currentThread().interrupt();
//            }
//
//            System.out.println("To stop pressssss");
//            sender.setActive(false);
        }
//        service.shutdown();
    }
}
