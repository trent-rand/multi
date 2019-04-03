package com.TrentRand_RonTieu.COE865;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

public class Router implements Runnable {

    private int type;
    private int name;
    private InetAddress hostIP;
    private ArrayList<NetworkLink> links;
    private Thread routerTask;

    public void DumpTable() {
        System.out.println("Dumping the address table of router - "+name);
        System.out.println(" -- ");
        System.out.print(getLinks().toArray().toString());
        System.out.println(" -- ");
    }

    public void Ping() {}

    public void Startup() {
        routerTask = new Thread(this::run);
        routerTask.start();
    }


    //Main router "listening" protocol.

    public void receiveUDPMessage(InetAddress ip, int port) throws IOException {
        byte[] buffer = new byte[1024];
        MulticastSocket socket = new MulticastSocket(port);
        socket.joinGroup(ip);

        while(true) {
            System.out.println("Waiting for multicast message...");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            socket.receive(packet);
            String msg = new String(packet.getData(),
                    packet.getOffset(),packet.getLength());
            System.out.println("[Multicast UDP message received]"+msg);
            if("OK".equals(msg)) {
                System.out.println("End of message... "+msg);
            }
        }

        //No reason to close out the connection, we want to Stay Hot!
        //socket.leaveGroup(ip);
        //socket.close();
    }

    //Runnable for thread.

    @Override
    public void run() {
        try {
            receiveUDPMessage(hostIP, 4321);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


        //Constructors, Getters, and Setters are foundd below.


    public Router(int type, int name) {
        this.type = type;
        this.name = name;
    }

    public Router() {}

    public int getName() {
        return name;
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
