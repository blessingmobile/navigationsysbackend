/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blessingmobile.navigationsys.service;

import com.blessingmobile.navigationsys.dao.PlanetDAO;
import com.blessingmobile.navigationsys.model.Planet;
import com.blessingmobile.navigationsys.model.Route;
import com.blessingmobile.navigationsys.model.Traffic;
import com.blessingmobile.navigationsys.repository.PlanetRepository;
import com.blessingmobile.navigationsys.repository.RouteRepository;
import com.blessingmobile.navigationsys.repository.TrafficRepository;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class FileService {

    @Autowired
    private PlanetDAO planetDAO;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private TrafficRepository trafficRepository;

    public String importData(InputStream stream) throws IOException {

        Workbook workbook = new XSSFWorkbook(stream);

        //Planets
        Sheet sheetPlanets = workbook.getSheetAt(0);
        List<Planet> listPlanets = new ArrayList<>();
        for (int i = 1; i < sheetPlanets.getLastRowNum() + 1; i++) {
            Row row = sheetPlanets.getRow(i);
            Cell cellPlanetNode = row.getCell(0);
            Cell cellPlanetName = row.getCell(1);
            String strPlanetNode = cellPlanetNode.getStringCellValue();
            String strPlanetName = cellPlanetName.getStringCellValue();
            Planet planet = new Planet(strPlanetNode, strPlanetName);
            listPlanets.add(planet);
        }

        //Routes
        Sheet sheetRoutes = workbook.getSheetAt(1);
        List<Route> listRoutes = new ArrayList<>();
        for (int i = 1; i < sheetRoutes.getLastRowNum() + 1; i++) {
            Row row = sheetRoutes.getRow(i);
            Cell cellRouteId = row.getCell(0);
            Cell cellPlanetOrigin = row.getCell(1);
            Cell cellPlanetDestination = row.getCell(2);
            Cell cellDistance = row.getCell(3);
            int strRouteId = (int) cellRouteId.getNumericCellValue();
            String strPlanetOrigin = cellPlanetOrigin.getStringCellValue();
            String strPlanetDestination = cellPlanetDestination.getStringCellValue();
            Double strPlanetDistance = cellDistance.getNumericCellValue();
            Route route = new Route(strRouteId, strPlanetOrigin, strPlanetDestination, strPlanetDistance);
            listRoutes.add(route);
        }

        //Traffic
        Sheet sheetTraffic = workbook.getSheetAt(2);
        List<Traffic> listTraffic = new ArrayList<>();
        for (int i = 1; i < sheetTraffic.getLastRowNum() + 1; i++) {
            Row row = sheetTraffic.getRow(i);
            Cell cellRouteId = row.getCell(0);
            Cell cellPlanetOrigin = row.getCell(1);
            Cell cellPlanetDestination = row.getCell(2);
            Cell cellDistance = row.getCell(3);
            int strRouteId = (int) cellRouteId.getNumericCellValue();
            String strPlanetOrigin = cellPlanetOrigin.getStringCellValue();
            String strPlanetDestination = cellPlanetDestination.getStringCellValue();
            Double strPlanetDistance = cellDistance.getNumericCellValue();
            Traffic traffic = new Traffic(strRouteId, strPlanetOrigin, strPlanetDestination, strPlanetDistance);
            listTraffic.add(traffic);
        }

        if (planetDAO.count() == 0
                && routeRepository.count() == 0
                && trafficRepository.count() == 0) {

            planetDAO.saveAll(listPlanets);
            routeRepository.saveAll(listRoutes);
            trafficRepository.saveAll(listTraffic);

            return "Data Successfully loaded.";

        }

        return "No data was loaded to database.";
        
    }

}
