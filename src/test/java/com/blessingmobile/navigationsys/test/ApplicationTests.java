package com.blessingmobile.navigationsys.test;

import com.blessingmobile.navigationsys.dao.PlanetDAO;
import com.blessingmobile.navigationsys.dao.RouteDAO;
import com.blessingmobile.navigationsys.dao.TrafficDAO;
import com.blessingmobile.navigationsys.dijkstra.engine.ShortestPathCalculator;
import com.blessingmobile.navigationsys.model.Planet;
import com.blessingmobile.navigationsys.model.Route;
import com.blessingmobile.navigationsys.model.Traffic;
import com.blessingmobile.navigationsys.service.FileService;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private FileService fileService;

    @Autowired
    PlanetDAO planetDao;

    @Autowired
    RouteDAO routeDao;

    @Autowired
    TrafficDAO trafficDao;

    @Test
    public void contextLoads() {

    }
    
     @Test
    public void testFileUpload() {

        try {
            String fPath = "";
            //fPath = "C:\\Users\\USER\\Desktop\\HR-Offsite_AssignmentV3_0.xlsx";
            fPath = "HR-Offsite_AssignmentV3_0.xlsx";
            FileInputStream inputStream = new FileInputStream(fPath);
            String response  = fileService.importData(inputStream);
            System.out.println("response=" + response);
            Assert.assertNotNull(response);
            // "Data Successfully loaded.";
            //No data was loaded to database.

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
    

    @Test
    public void testPlanetsDao() {

        List<Planet> listPlanets = planetDao.findAll();
        Assert.assertEquals(37, listPlanets.size());
    }

    @Test
    public void testRoutesDao() {

        List<Route> listRoute = routeDao.findAll();
        Assert.assertEquals(45, listRoute.size());
    }

    @Test
    public void testTrafficDao() {

        List<Traffic> listTraffic = trafficDao.findAll();
        Assert.assertEquals(45, listTraffic.size());
        Traffic traffic = listTraffic.get(0);
        Assert.assertNotNull(traffic.getPlanetOrigin());
        Assert.assertNotNull(traffic.getPlanetDestination());
        Assert.assertNotNull(traffic.getRouteId());
        Assert.assertNotNull(traffic.toString());
    }

   
    
    
    

}
