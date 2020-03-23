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
@Table(name = "planet")
public class Planet {
    
    private long id;
    private String planetNode;
    private String planetName;

    public Planet() {
    }
    
   

    public Planet(String planetNode, String planetName) {
        this.planetNode = planetNode;
        this.planetName = planetName;
    }
    
     @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

        @Column(name = "planet_node", nullable = false)
    public String getPlanetNode() {
        return planetNode;
    }

    public void setPlanetNode(String planetNode) {
        this.planetNode = planetNode;
    }

    @Column(name = "planet_name", nullable = false)
    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    @Override
    public String toString() {
        return "Planet{" + "planetNode=" + planetNode + ", planetName=" + planetName + '}';
    }
    
    
    
}
