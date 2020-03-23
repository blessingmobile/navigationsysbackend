package com.blessingmobile.navigationsys.soap.client;

import com.blessingmobile.navigationsys.dijkstra.engine.ShortestPathCalculator;
import com.blessingmobile.navigationsys.gen.*;
import com.blessingmobile.navigationsys.model.DistanceResponse;
import com.blessingmobile.navigationsys.model.Path;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class DistanceRepository {
    
    @Autowired
    private ShortestPathCalculator logic;

    public Distance findDistance(String origin, String destination, boolean isTrafficEnabled) {

        Path path = new Path(origin, destination, isTrafficEnabled); 
        
        DistanceResponse distanceResponse = logic.getShortestPath(path);

        Distance distance = new Distance();
        distance.setDirections(distanceResponse.getVertices().toString());
        BigDecimal bigDecimal = new BigDecimal(distanceResponse.getDistance());
        distance.setPathdistance(bigDecimal);
        
        return distance;
    }
}
