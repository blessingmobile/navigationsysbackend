/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blessingmobile.navigationsys.soap.client;

import com.blessingmobile.navigationsys.gen.Distance;
import com.blessingmobile.navigationsys.gen.GetDistanceRequest;
import com.blessingmobile.navigationsys.gen.GetDistanceResponse;
import org.springframework.stereotype.Service;
import com.blessingmobile.navigationsys.model.Path;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author USER
 */
@Service
public class SoapDistanceService {
    
    private Distance distance;
        
    @Autowired
    SOAPConnector soapConnector;

    public SoapDistanceService() {
    }

    public Distance calculateDistance(Path path) {
        
        
            GetDistanceRequest request = new GetDistanceRequest();
            request.setOrigin(path.getFromRouteId());
            request.setDestination(path.getToRouteId());
            request.setTrafficEnabled(path.isTrafficEnabled());
            GetDistanceResponse response =(GetDistanceResponse) soapConnector.callWebService("http://localhost:8081/navigationsys/ws", request);
            Distance distanceResponse =  response.getDistance();
            return distanceResponse;
        
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }
    
    
    
    
}
