package com.TrentRand_RonTieu.COE865;

import java.util.ArrayList;

public class Network {

    private int numberOfRouters;

    private ArrayList<Router> Routers = new ArrayList<Router>();
    private ArrayList<Vertex> Vertices = new ArrayList<Vertex>();
    private ArrayList<NetworkLink> Links = new ArrayList<NetworkLink>();


    public ArrayList<Vertex> getVertices() {
        return Vertices;
    }

    public Vertex getVertexByName(String name) {
        for(Vertex vertex : Vertices) {
            if(vertex.getName().equals(name)) {
                return vertex;
            }
        }
        return null;
    }

    public Router getRouterByName(int name) {
        for(Router router : Routers) {
            if(router.getName() == name) {
                return router;
            }
        }
        return null;
    }

    public Router getRouterByType(int type) {
        for(Router router : Routers) {
            if(router.getType() == type) {
                return router;
            }
        }
        return null;
    }

    /*  public NetworkLink getLinkByName(String name) {
        NetworkLink toReturn;
        for(NetworkLink link : Links) {
            if(link.getName().equals(name)) {
                toReturn = link;
            }
        }

        return toReturn;
    }   */

    public void setVertices(ArrayList<Vertex> vertices) {
        Vertices = vertices;
    }

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
