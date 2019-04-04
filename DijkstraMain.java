package com.TrentRand_RonTieu.COE865;

import java.util.*;

public class DijkstraMain {

    public static void main(String[] args) {

        Vertex vertex1 = new Vertex("1");
        Vertex vertex2 = new Vertex("2");
        Vertex vertex3 = new Vertex("3");
        Vertex vertex4 = new Vertex("4");
        Vertex vertex5 = new Vertex("5");
        Vertex vertex6 = new Vertex("6");
        Vertex vertex7 = new Vertex("7");

        vertex1.addNeighbour(new Edge(2,vertex1,vertex2));
        vertex1.addNeighbour(new Edge(5,vertex1,vertex3));
        vertex2.addNeighbour(new Edge(6,vertex2,vertex3));
        vertex2.addNeighbour(new Edge(4,vertex2,vertex4));
        vertex2.addNeighbour(new Edge(5,vertex2,vertex5));
        vertex3.addNeighbour(new Edge(2,vertex3,vertex5));
        vertex3.addNeighbour(new Edge(2,vertex3,vertex6));
        vertex3.addNeighbour(new Edge(5,vertex3,vertex7));
        vertex4.addNeighbour(new Edge(5,vertex4,vertex5));
        vertex4.addNeighbour(new Edge(4,vertex4,vertex7));
        vertex5.addNeighbour(new Edge(2,vertex5,vertex7));
        vertex6.addNeighbour(new Edge(5,vertex6,vertex7));

        DijkstraShortestPath shortestPath = new DijkstraShortestPath();
        shortestPath.computeShortestPaths(vertex1);

        System.out.println("======================================");
        System.out.println("Info Dump");
        System.out.println("======================================");

        System.out.println("Path from 1 to 2: "+ shortestPath.getShortestPathTo(vertex2) + " cost: " + vertex2.getDistance());
        System.out.println("Path from 1 to 3: "+ shortestPath.getShortestPathTo(vertex3) + " cost: " + vertex3.getDistance());
        System.out.println("Path from 1 to 4: "+ shortestPath.getShortestPathTo(vertex4) + " cost: " + vertex4.getDistance());
        System.out.println("Path from 1 to 5: "+ shortestPath.getShortestPathTo(vertex5) + " cost: " + vertex5.getDistance());
        System.out.println("Path from 1 to 6: "+ shortestPath.getShortestPathTo(vertex6) + " cost: " + vertex6.getDistance());
        System.out.println("Path from 1 to 7: "+ shortestPath.getShortestPathTo(vertex7) + " cost: " + vertex7.getDistance());

        int size2 = shortestPath.getShortestPathTo(vertex2).size();
        int size3 = shortestPath.getShortestPathTo(vertex3).size();
        int size4 = shortestPath.getShortestPathTo(vertex4).size();
        int size5 = shortestPath.getShortestPathTo(vertex5).size();
        int size6 = shortestPath.getShortestPathTo(vertex6).size();
        int size7 = shortestPath.getShortestPathTo(vertex7).size();

        List<String> nexthop1 = new ArrayList<String>();
        List<String> nexthop2 = new ArrayList<String>();
        List<String> nexthop3 = new ArrayList<String>();
        List<String> nexthop4 = new ArrayList<String>();
        List<String> nexthop5 = new ArrayList<String>();
        List<String> nexthop6 = new ArrayList<String>();
        List<String> nexthop7 = new ArrayList<String>();

        StringBuilder s2 = new StringBuilder();
        StringBuilder s3 = new StringBuilder();
        StringBuilder s4 = new StringBuilder();
        StringBuilder s5 = new StringBuilder();
        StringBuilder s6 = new StringBuilder();
        StringBuilder s7 = new StringBuilder();

        int i1=1;
        int i2=1;
        int i3=1;
        int i4=1;
        int i5=1;
        int i6=1;
        int i7=1;


        ////////
        // R1 //
        ////////

        if(i2<size2){
            for(int i=i2;i<size2;i++){
                s2.append(shortestPath.getShortestPathTo(vertex2).get(i)+ ",");
            }
            if(s2.charAt(0) == '2'){
                nexthop1.add("(NODE 2: NEXT HOP 2)");
            }
            else if(s2.charAt(0) == '3'){
                nexthop1.add("(NODE 2: NEXT HOP 3)");
            }
            i2++;
        }

        if(i3<size3){
            for(int i=i3;i<size2;i++){
                s3.append(shortestPath.getShortestPathTo(vertex3).get(i) + ",");
                if(s3.charAt(0) == '2'){
                    nexthop1.add("(NODE 3: NEXT HOP 2)");
                }
                else if(s3.charAt(0) == '3'){
                    nexthop1.add("(NODE 3: NEXT HOP 3)");
                }
                i3++;
            }

            if(i4<size4){
                for(int i=i4;i<size4;i++){
                    s4.append(shortestPath.getShortestPathTo(vertex4).get(i)+",");
                }
                if(s4.charAt(0) == '2'){
                    nexthop1.add("(NODE 4: NEXT HOP 2)");
                }
                else if(s4.charAt(0) == '3'){
                    nexthop1.add("(NODE 4: NEXT HOP 3)");
                }
                i4++;
            }

            if(i5<size5){
                for(int i=i5;i<size5;i++){
                    s5.append(shortestPath.getShortestPathTo(vertex5).get(i) +",");
                }
                if(s5.charAt(0) == '2'){
                    nexthop1.add("(NODE 5: NEXT HOP 2)");
                }
                else if(s5.charAt(0) == '3'){
                    nexthop1.add("(NODE 5: NEXT HOP 3)");
                }
                i5++;
            }

            if(i6<size6){
                for(int i=i6;i<size6;i++){
                    s6.append(shortestPath.getShortestPathTo(vertex6).get(i)+",");
                }
                if(s6.charAt(0) == '2'){
                    nexthop1.add("(NODE 6: NEXT HOP 2)");
                }
                else if(s6.charAt(0) == '3'){
                    nexthop1.add("(NODE 6: NEXT HOP 3)");
                }
                i6++;
            }

            if(i7<size7){
                for(int i=i7;i<size7;i++){
                    s7.append(shortestPath.getShortestPathTo(vertex7).get(i)+",");
                }
                if(s7.charAt(0) == '2'){
                    nexthop1.add("(NODE 7: NEXT HOP 2)");
                }
                else if(s7.charAt(0) == '3'){
                    nexthop1.add("(NODE 7: NEXT HOP 3)");
                }
                i7++;
            }

            System.out.println("\nTree at Table 1: \n"+nexthop1+"\n");


            ///////////////////////////////////////////
            // PRETEND EVERYTHING GETS SENT UDP HERE //
            ///////////////////////////////////////////

            ////////
            // R2 //
            ////////

            if(i2<size2){
                s2.delete(0,2);
                i2++;
                if(s2.charAt(0) == '3'){
                    nexthop2.add("(NODE 2: NEXT HOP 3)");
                }
                else if(s2.charAt(0) == '4'){
                    nexthop2.add("(NODE 2: NEXT HOP 4)");
                }
                else if(s2.charAt(0) == '5'){
                    nexthop2.add("(NODE 2: NEXT HOP 5)");
                }
            }

            if(i4<size4){
                s4.delete(0,2);
                i4++;
                if(s4.charAt(0) == '3'){
                    nexthop2.add("(NODE 4: NEXT HOP 3)");
                }
                else if(s4.charAt(0) == '4'){
                    nexthop2.add("(NODE 4: NEXT HOP 4)");
                }
                else if(s4.charAt(0) == '5'){
                    nexthop2.add("(NODE 4: NEXT HOP 5)");
                }
            }

            if(i5<size5){
                s5.delete(0,2);
                i5++;
                if(s5.charAt(0) == '3'){
                    nexthop2.add("(NODE 5: NEXT HOP 3)");
                }
                else if(s5.charAt(0) == '4'){
                    nexthop2.add("(NODE 5: NEXT HOP 4)");
                }
                else if(s5.charAt(0) == '5'){
                    nexthop2.add("(NODE 5: NEXT HOP 5)");
                }
            }

            if(i7<size7){
                s7.delete(0,2);
                i7++;
                if(s7.charAt(0) == '3'){
                    nexthop2.add("(NODE 7: NEXT HOP 3)");
                }
                else if(s7.charAt(0) == '4'){
                    nexthop2.add("(NODE 7: NEXT HOP 4)");
                }
                else if(s7.charAt(0) == '5'){
                    nexthop2.add("(NODE 7: NEXT HOP 5)");
                }
            }

            System.out.println("Tree at Table 2: \n"+nexthop2+"\n");

            ////////
            // R3 //
            ////////

            if(i3<size3){
                s3.delete(0,2);
                i3++;
                if(s3.charAt(0) == '2'){
                    nexthop3.add("(NODE 3: NEXT HOP 2)");
                }
                else if(s3.charAt(0) == '5'){
                    nexthop3.add("(NODE 3: NEXT HOP 5)");
                }
                else if(s3.charAt(0) == '6'){
                    nexthop3.add("(NODE 3: NEXT HOP 6)");
                }
                else if(s3.charAt(0) == '7'){
                    nexthop3.add("(NODE 3: NEXT HOP 7)");
                }
            }

            if(i6<size6){
                s6.delete(0,2);
                i6++;
                if(s6.charAt(0) == '2'){
                    nexthop3.add("(NODE 6: NEXT HOP 2)");
                }
                else if(s6.charAt(0) == '5'){
                    nexthop3.add("(NODE 6: NEXT HOP 5)");
                }
                else if(s6.charAt(0) == '6'){
                    nexthop3.add("(NODE 6: NEXT HOP 6)");
                }
                else if(s6.charAt(0) == '7'){
                    nexthop3.add("(NODE 6: NEXT HOP 7)");
                }
            }

            System.out.println("Tree at Table 3: \n"+nexthop3+"\n");

            ////////
            // R4 //
            ////////

            if(i4<size4){
                s4.delete(0,2);
                i4++;
                if(s4.charAt(0) == '5'){
                    nexthop4.add("(NODE 4: NEXT HOP 2)");
                }
                else if(s4.charAt(0) == '7'){
                    nexthop4.add("(NODE 4: NEXT HOP 5)");
                }
            }
            System.out.println("Tree at Table 4: \n"+nexthop4+"\n");

            ////////
            // R5 //
            ////////

            if(i5<size5){
                s5.delete(0,2);
                i5++;
                if(s5.charAt(0) == '3'){
                    nexthop5.add("(NODE 5: NEXT HOP 3)");
                }
                else if(s5.charAt(0) == '4'){
                    nexthop5.add("(NODE 5: NEXT HOP 4)");
                }
                else if(s5.charAt(0) == '7'){
                    nexthop5.add("(NODE 5: NEXT HOP 7)");
                }
            }

            if(i7<size7){
                s7.delete(0,2);
                i7++;
                if(s7.charAt(0) == '3'){
                    nexthop5.add("(NODE 7: NEXT HOP 3)");
                }
                else if(s7.charAt(0) == '4'){
                    nexthop5.add("(NODE 7: NEXT HOP 4)");
                }
                else if(s7.charAt(0) == '7'){
                    nexthop5.add("(NODE 7: NEXT HOP 7)");
                }
            }
            System.out.println("Tree at Table 5: \n"+nexthop5+"\n");

            ////////
            // R6 //
            ////////

            if(i6<size6){
                s6.delete(0,2);
                i6++;
                if(s6.charAt(0) == '7'){
                    nexthop4.add("(NODE 6: NEXT HOP 7)");
                }
            }
            System.out.println("Tree at Table 6: \n"+nexthop6+"\n");

            ////////
            // R7 //
            ////////

            if(i7<size7){
                s7.delete(0,2);
                i7++;
                if(s7.charAt(0) == '3'){
                    nexthop7.add("(NODE 7: NEXT HOP 3)");
                }
                else if(s7.charAt(0) == '4'){
                    nexthop7.add("(NODE 7: NEXT HOP 4)");
                }
                else if(s7.charAt(0) == '6'){
                    nexthop7.add("(NODE 7: NEXT HOP 6)");
                }
            }
            System.out.println("Tree at Table 7: \n"+nexthop7+"\n");

        }
    }
}