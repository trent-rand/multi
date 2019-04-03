package com.TrentRand_RonTieu.COE865;

import java.awt.image.ImagingOpException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    private static Network network;

    public static void main(String[] args) throws IOException {

        welcomeMessage();
        network = new Network();

        BufferedReader userInputReader =
                new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please enter the file name of the config/startup file. (.txt)");

        String filename = userInputReader.readLine();

        System.out.println(filename);
        readFile(filename, network);

        Iterator<Router> networkIterator = network.getRouters().iterator();

        System.out.println("Network has been successfully bootstrapped!");
        System.out.println("You may issue the following commands in order to test and evaluate the network.");
        System.out.println("Ping [Hostname] - Ping a router with hostname (int).");
        System.out.println("Dump [Hostname] - Dump the routing table of the router at hostname (int).");


        while(true) {

            String command = userInputReader.readLine();
            if (command.equals("Ping")) {
                System.out.println("Please enter the desired host name:");
                String hostname = userInputReader.readLine();
                int host = Integer.parseInt(hostname);

                while(networkIterator.hasNext()) {
                     if(networkIterator.next().getName() == host) {
                         networkIterator.next().Ping();
                     }
                }

            } else if (command.equals("Dump")) {
                System.out.println("Please enter the desired host name:");
                String hostname = userInputReader.readLine();
                int host = Integer.parseInt(hostname);

                while(networkIterator.hasNext()) {
                    if(networkIterator.next().getName() == host) {
                        networkIterator.next().DumpTable();
                    }
                }
            }
        }

    }


    private static List<String> readFile(String filename, Network network) {
        List<String> records = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            int index = 0;
            int tempSize = 0;
            while ((line = reader.readLine()) != null) {
                records.add(line);

                //System.out.println("Reading from file: "+line);

                if(index == 0) { //Parse Size fo Network
                        tempSize = Integer.parseInt(line);
                        network.setNumberOfRouters(tempSize);

                } else if (index <= tempSize) { //Parsing Routers
                    int ind = 0;
                    Router newRouter = new Router();
                    String address = "";
                    for(char c : line.toCharArray() ) {
                        if(ind == 0) {
                            System.out.println("New Router: "+c);
                            newRouter.setName(Integer.parseInt(""+c));
                        } else if (ind == 2) {
                            newRouter.setType(Integer.parseInt(""+c));

                        } else if (ind > 3) {
                            address = address + c;
                        }
                        ind++;
                    }

                    System.out.println("New router created with host address: "+address);

                    newRouter.setHostIP(InetAddress.getByName(address));
                    newRouter.Startup();
                    network.getRouters().add(newRouter);

                } else { //Parsing Links




                }
            index++;
            }
            reader.close();


            return records;
        }
        catch (Exception e) {
            System.err.format("Exception occurred trying to parse '%s'.", filename);
            e.printStackTrace();
            return null;
        }
    }

    private static void welcomeMessage() {
        System.out.println("Multicast Overlay Networking - Ron Tieu, Trent Rand");
        System.out.println("COE 865 - Final Project - W2019");
        System.out.println("---");
        System.out.println("Implemented in Java using UDP Socket Programming.");
        System.out.println("---");
    }
}

