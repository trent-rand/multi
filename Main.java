package com.TrentRand_RonTieu.COE865;

import java.awt.image.ImagingOpException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    private static Network network;
    private static DijkstraShortestPath shortPath;

    public static void main(String[] args) throws IOException {

        welcomeMessage();
        network = new Network();
        shortPath = new DijkstraShortestPath();

        BufferedReader userInputReader =
                new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please enter the file name of the config/startup file. (.txt)");

        String filename = userInputReader.readLine();

        System.out.println(filename);
        readFile(filename);

        bootstrapNetwork();

        System.out.println("Network has been successfully bootstrapped!");
        System.out.println("You may issue the following commands in order to test and evaluate the network.");
        System.out.println("Ping - Ping a router with hostname (int).");
        System.out.println("Dump - Dump the routing table of the router at hostname (int).");
        System.out.println("Exit - 'Let me out! ... Let me out!!! ... '");


        while(true) {

            String command = userInputReader.readLine();
            if (command.equals("Ping")) {
                System.out.println("Please enter the desired host name:");
                String hostname = userInputReader.readLine();

                Router router = network.getRouterByType(1);
                router.Ping(hostname);

            } else if (command.equals("Dump")) {
                System.out.println("Please enter the desired host name:");
                String hostname = userInputReader.readLine();
                int host = Integer.parseInt(hostname);

                Router router = network.getRouterByName(host);
                router.DumpTable();

            } else if (command.equals("Exit")) { //Get me the f*ck outta here!
                System.out.println("Goodbye!");
                System.exit(0);
            }
        }

    }


    private static List<String> readFile(String filename) {
        List<String> records = new ArrayList<String>();
        //ArrayList<Vertex> vertices = new ArrayList<>();
        //ArrayList<Edge> edges = new ArrayList<>();



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
                            Vertex newVertex = new Vertex(""+c);

                            network.getVertices().add(newVertex);

                        } else if (ind == 2) {
                            newRouter.setType(Integer.parseInt(""+c));

                        } else if (ind > 3) {
                            address = address + c;
                        }
                        ind++;
                    }

                    System.out.println("New router created with host address: "+address);

                    newRouter.setHostIP(InetAddress.getByName(address));
                    network.getRouters().add(newRouter);



                } else { //Parsing Links
                    int ind = 0;
                    Edge newEdge = new Edge();

                    System.out.println("Parsing Connection: "+line);

                    String startName = ""; //This.
                    String endName = "";   //Is.
                    String weight = "";    //Bad.
                    for(char c : line.toCharArray() ) {
                        if(ind == 0) {
                            startName = ""+c;
                        } else if (ind == 2) {
                            endName = ""+c;
                        } else if (ind == 4) {
                            weight = ""+c;
                        }
                        ind++;
                    }

                    //while(vertexIterator.hasNext()) {
                    //    if(vertexIterator.next().getName().equals(startName)) {
                    //        newEdge.setStartVertex(vertexIterator.next());
                    //    } else if (vertexIterator.next().getName().equals(endName)) {
                    //        newEdge.setTargetVertex(vertexIterator.next());
                    //    }
                    //}


                    newEdge.setStartVertex(network.getVertexByName(startName));
                    newEdge.setTargetVertex(network.getVertexByName(endName));

                    newEdge.setWeight(Double.parseDouble(weight));
                    network.getVertexByName(startName).addNeighbour(newEdge); //What a line. Wow. Programming is WHACK!
                    network.getVertexByName(endName).addNeighbour(newEdge);

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

    private static void bootstrapNetwork() {

        //First iteration over the routers identifies the desired source, and uses it to populate the tree.
        for (Router router : network.getRouters()) {
            if (router.getType() == 1) { //This is our source router!
                shortPath.computeShortestPaths(network.getVertexByName(""+router.getName()));
            }
        }

        //Map from the abstract Vertices and Edges into known Network Links for each Router.
        for (Router router : network.getRouters()) {
            for (Vertex vert : network.getVertices()) {
                if(vert.getName().equals(""+router.getName())) { //This type of int-String casting is embarrassing...
                    try {
                        Vertex destVertex = vert.getPredecessor();
                        Router destRouter = network.getRouterByName(Integer.parseInt(destVertex.getName()));

                        router.setReceivingAddress(destRouter.getHostIP());
                        network.getRouterByName(Integer.parseInt(destVertex.getName())).getForwardAddress().add(router.getHostIP());
                    } catch (Exception e) {
                        //System.err.println("Warning! Not all Routing Assignments were issued correctly!");
                        //System.err.println("Router No. "+vert.getName());
                        //System.err.println(e);
                    }
                }
            }
            router.Startup();
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

