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
public class Path {

    private String fromRouteId;
    private String toRouteId;
    private boolean isTrafficEnabled;

    public Path() {
    }

    public Path(String fromRouteId, String toRouteId, boolean isTrafficEnabled) {
        this.fromRouteId = fromRouteId;
        this.toRouteId = toRouteId;
        this.isTrafficEnabled = isTrafficEnabled;
    }
    
    

    public String getFromRouteId() {
        return fromRouteId;
    }

    public void setFromRouteId(String fromRouteId) {
        this.fromRouteId = fromRouteId;
    }

    public String getToRouteId() {
        return toRouteId;
    }

    public void setToRouteId(String toRouteId) {
        this.toRouteId = toRouteId;
    }

    public boolean isTrafficEnabled() {
        return isTrafficEnabled;
    }

    public void setIsTrafficEnabled(boolean isTrafficEnabled) {
        this.isTrafficEnabled = isTrafficEnabled;
    }
    
    

}
