package com.TrentRand_RonTieu.COE865;

import java.util.ArrayList;

public class Network {

    private int numberOfRouters;

    private ArrayList<Router> Routers = new ArrayList<Router>();
    private ArrayList<NetworkLink> Links = new ArrayList<NetworkLink>();

    public int getNumberOfRouters() {
        return numberOfRouters;
    }

    public void setNumberOfRouters(int numberOfRouters) {
        this.numberOfRouters = numberOfRouters;
    }

    public ArrayList<Router> getRouters() {
        return Routers;
    }

    public void setRouters(ArrayList<Router> routers) {
        Routers = routers;
    }

    public ArrayList<NetworkLink> getLinks() {
        return Links;
    }

    public void setLinks(ArrayList<NetworkLink> links) {
        Links = links;
    }
}
