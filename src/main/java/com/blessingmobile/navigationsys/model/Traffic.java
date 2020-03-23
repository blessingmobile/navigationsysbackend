/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blessingmobile.navigationsys.model;

/**
 *
 * @author USER
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "traffic")
public class Traffic {

    private long id;
    private int routeId;
    private String planetOrigin;
    private String planetDestination;
    private double distance;

    public Traffic() {
    }

    public Traffic(int routeId, String planetOrigin, String planetDestination, double distance) {
        this.routeId = routeId;
        this.planetOrigin = planetOrigin;
        this.planetDestination = planetDestination;
        this.distance = distance;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "route_id", nullable = false)
    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    @Column(name = "planet_origin", nullable = false)
    public String getPlanetOrigin() {
        return planetOrigin;
    }

    public void setPlanetOrigin(String planetOrigin) {
        this.planetOrigin = planetOrigin;
    }

    @Column(name = "planet_destination", nullable = false)
    public String getPlanetDestination() {
        return planetDestination;
    }

    public void setPlanetDestination(String planetDestination) {
        this.planetDestination = planetDestination;
    }

    @Column(name = "distance", nullable = false)
    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Traffic{" + "routeId=" + routeId + ", planetOrigin=" + planetOrigin + ", planetDestination=" + planetDestination + ", distance=" + distance + '}';
    }

}
