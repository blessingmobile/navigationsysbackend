package com.blessingmobile.navigationsys.test;

import com.blessingmobile.navigationsys.Application;
import com.blessingmobile.navigationsys.dijkstra.engine.ShortestPathCalculator;
import com.blessingmobile.navigationsys.dijkstra.model.Vertex;
import com.blessingmobile.navigationsys.gen.Distance;
import com.blessingmobile.navigationsys.model.DistanceResponse;
import com.blessingmobile.navigationsys.model.Path;
import com.blessingmobile.navigationsys.soap.client.DistanceRepository;
import com.blessingmobile.navigationsys.soap.client.SoapDistanceService;
import java.util.LinkedList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DijkstrasShortestPathTest {

    @Autowired
    DistanceRepository distanceRepository;
    
    @Autowired
    ShortestPathCalculator logic;
    
    @Autowired
    SoapDistanceService soapDistanceService;

    @Test
    public void shortestPathNoTraffic() {

        Path path = new Path("0", "36", false);
        DistanceResponse distance = logic.getShortestPath(path);
        double pathLen = distance.getDistance();
        LinkedList<Vertex> vertices = distance.getVertices();

        Assert.assertEquals(pathLen, 60.82000000000001, 0);
        Assert.assertEquals(vertices.size(), 8, 0);

    }
    
    @Test
    public void shortestPathWithTraffic() {

        Path path = new Path("0", "36", true);
        DistanceResponse distance = logic.getShortestPath(path);
        double pathLen = distance.getDistance();
        LinkedList<Vertex> vertices = distance.getVertices();

        Assert.assertEquals(92.42, pathLen, 0);
        Assert.assertEquals(8, vertices.size(), 0);
        
        Assert.assertNotNull(distance.toString());

    }
    
   @Test
    public void testDistanceRepositoryWithTraffic() 
    {
        Path path = new Path("0", "1", true);
        DistanceResponse distance = logic.getShortestPath(path);
        double pathLen = distance.getDistance();
        LinkedList<Vertex> vertices = distance.getVertices();

        Assert.assertEquals(0.74, pathLen, 0);
        Assert.assertEquals(2, vertices.size(),  0);
    }
    
      
    

}
