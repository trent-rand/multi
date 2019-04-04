package com.TrentRand_RonTieu.COE865;

public class NetworkLink {

    private Router routerA;
    private Router routerB;
    private int cost;

    public NetworkLink(Router routerA, Router routerB) {
        this.routerA = routerA;
        this.routerB = routerB;
    }

    public NetworkLink() {
    }


    public Router getRouterA() {
        return routerA;
    }

    public void setRouterA(Router routerA) {
        this.routerA = routerA;
    }

    public Router getRouterB() {
        return routerB;
    }

    public void setRouterB(Router routerB) {
        this.routerB = routerB;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
