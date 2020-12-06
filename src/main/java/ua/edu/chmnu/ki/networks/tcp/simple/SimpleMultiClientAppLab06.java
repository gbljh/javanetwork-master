package ua.edu.chmnu.ki.networks.tcp.simple;

import ua.edu.chmnu.ki.networks.tcp.simple.SimpleTCPClient;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.*;

class SimpleMultiClientAppLab06 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                JPanel gui = new JPanel(new BorderLayout(3,3));
                final JPanel panel = new JPanel(new GridLayout(0,1));
                JTextField input = new JTextField("127.0.0", 5);
                JScrollPane scroll = new JScrollPane(panel);
                scroll.setPreferredSize(new Dimension(80,100));
                gui.add(scroll, BorderLayout.CENTER);
                JButton addLabel = new JButton("Start");
                gui.add(addLabel, BorderLayout.SOUTH);
                gui.add(input, BorderLayout.NORTH);
                ActionListener listener = new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        String host = input.getText();
                        String[] testData = {
                                "Hello, I'm here!",
                        };
                        int port = 5558;
                        for(int i = 0;i<256;i++) {
                            try {
                                ExecutorService executor = Executors.newFixedThreadPool(10);
                                for (String line : testData) {
                                    executor.submit(new SimpleTCPClient(host+"."+i, port, line));
                                }

                                executor.shutdown();
                                panel.add(new JLabel(host+"."+i+" is opened on port 5558"));
                                panel.revalidate();
                                int height = (int)panel.getPreferredSize().getHeight();
                                Rectangle rect = new Rectangle(0,height,10,10);
                                panel.scrollRectToVisible(rect);
                            } catch (Exception e2) {
                            }
                        }
                    }
                };
                addLabel.addActionListener(listener);
                JOptionPane.showMessageDialog(null, gui);
            }
        });
    }
}