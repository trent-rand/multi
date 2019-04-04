package com.TrentRand_RonTieu.COE865;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class Router implements Runnable {

    private int type;
    private int name;
    private InetAddress hostIP;
    private ArrayList<NetworkLink> links;
    private ArrayList<InetAddress> forwardAddress;
    private InetAddress receivingAddress;
    private Thread routerTask;

    public void DumpTable() {
        System.out.println("Dumping the address table of router - "+name);
        System.out.println(" -- Receiving From -- ");
        //System.out.println(getForwardAddress().toArray().toString());
        System.out.println(receivingAddress);
        System.out.println("");
        System.out.println(" -- Forwarding To -- ");
        for(InetAddress addr : forwardAddress) {
            System.out.println(addr);
        }
        System.out.println("");

    }

    public void Ping(String message) {
        if(type == 1) { //We're the source!!
            for(InetAddress addr : forwardAddress) {
                try {
                    sendUDPMessage(message, addr, 4321);
                } catch (Exception e) {
                    System.err.println("Error attempting to ping!");
                }
            }
        }
    }

    public void Startup() {
        routerTask = new Thread(this::run);
        routerTask.start();
    }


    //Main router "listening" protocol.

    public void receiveUDPMessage(InetAddress ip, int port) throws IOException {
        byte[] buffer = new byte[1024];
        DatagramSocket socket = new DatagramSocket(port);
        socket.connect(ip, port);

        while(true) {
            System.out.println("Waiting for datagram message...");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            socket.receive(packet);
            String msg = new String(packet.getData(),
                    packet.getOffset(),packet.getLength());
            System.out.println("[Multicast UDP message received]"+msg);
            if("OK".equals(msg)) {
                System.out.println("End of message... "+msg);

                if (type != 2) { //not type 2, therefore our boy here is a forwarding router!
                    System.out.println("Forwarding Router "+name+" is no forwarding the packet...");

                    for(InetAddress addr : forwardAddress) {
                        try {
                            sendUDPMessage(msg, addr, 4321);
                        } catch (Exception e) {
                            System.err.println("Error attempting to ping!");
                        }
                    }
                }
            }
        }

        //No reason to close out the connection, we want to Stay Hot!
        //socket.leaveGroup(ip);
        //socket.close();
    }

    public static void sendUDPMessage(String message,
                                      InetAddress ipAddress, int port) throws IOException {
        DatagramSocket socket = new DatagramSocket();

        byte[] msg = message.getBytes();
        DatagramPacket packet = new DatagramPacket(msg, msg.length,
                ipAddress, port);
        socket.send(packet);
        socket.close();
    }

    //Runnable for thread.
    @Override
    public void run() {
        try {
            if (getType() != 1) {
                receiveUDPMessage(receivingAddress, 4321);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


        //Constructors, Getters, and Setters are foundd below.


    public Router(int type, int name) {
        this.type = type;
        this.name = name;
    }

    public Router() {
        forwardAddress = new ArrayList<InetAddress>();
    }

    public int getName() {
        return name;
    }

    public ArrayList<InetAddress> getForwardAddress() {
        return forwardAddress;
    }

    public void setForwardAddress(ArrayList<InetAddress> forwardAddress) {
        this.forwardAddress = forwardAddress;
    }

    public InetAddress getReceivingAddress() {
        return receivingAddress;
    }

    public void setReceivingAddress(InetAddress receivingAddress) {
        this.receivingAddress = receivingAddress;
    }

    public InetAddress getHostIP() {
        return hostIP;
    }

    public void setHostIP(InetAddress hostIP) {
        this.hostIP = hostIP;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<NetworkLink> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<NetworkLink> links) {
        this.links = links;
    }
}
