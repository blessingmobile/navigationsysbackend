/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blessingmobile.navigationsys.model;

import com.blessingmobile.navigationsys.dijkstra.model.Vertex;
import java.util.LinkedList;

/**
 *
 * @author USER
 */
public class DistanceResponse {
    
    private double distance;
    private LinkedList<Vertex> vertices;

    public DistanceResponse() {
    }

    public DistanceResponse(double distance, LinkedList<Vertex> vertices) {
        this.distance = distance;
        this.vertices = vertices;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public LinkedList<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(LinkedList<Vertex> vertices) {
        this.vertices = vertices;
    }

    @Override
    public String toString() {
        return "DistanceResponse{" + "distance=" + distance + ", vertices=" + vertices + '}';
    }
    
}
