package com.blessingmobile.navigationsys.controller;

import com.blessingmobile.navigationsys.dao.PlanetDAO;
import com.blessingmobile.navigationsys.dijkstra.engine.ShortestPathCalculator;
import com.blessingmobile.navigationsys.gen.Distance;
import com.blessingmobile.navigationsys.model.DistanceResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blessingmobile.navigationsys.model.Path;
import com.blessingmobile.navigationsys.model.Planet;
import com.blessingmobile.navigationsys.service.FileService;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.blessingmobile.navigationsys.soap.client.SoapDistanceService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ShortestPathController {

    @Autowired
    SoapDistanceService soapDistanceService;

    @Autowired
    private ShortestPathCalculator logic;

    @Autowired
    private FileService fileService;

    @Autowired
    private PlanetDAO planetDAO;

    @Autowired
    public ShortestPathController(FileService fileService) {
        
        this.fileService = fileService;
        
    }

    @PostMapping(value = "/fileImport")
    @ResponseStatus(HttpStatus.OK)
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
       
        return fileService.importData(file.getInputStream());
        
    }

    @RequestMapping(value = "/loadData", method = RequestMethod.POST)
    public ResponseEntity<Object> loadData(@RequestBody Path path) {

        return new ResponseEntity<>("Data loaded", HttpStatus.OK);
        
    }

    @RequestMapping(value = "/shortestPathRest", method = RequestMethod.POST)
    public ResponseEntity<Object> getShortestPathREST(@RequestBody Path path) {

        DistanceResponse distanceResponse = logic.getShortestPath(path);
        return new ResponseEntity<>(distanceResponse, HttpStatus.OK);

    }

    @RequestMapping(value = "/shortestPathSoap", method = RequestMethod.POST)
    public ResponseEntity<Object> getShortestPathSOAP(@RequestBody Path path) {

        Distance distanceResponse = soapDistanceService.calculateDistance(path);
        return new ResponseEntity<>(distanceResponse, HttpStatus.OK); 

    }

    @RequestMapping(value = "/planets", method = RequestMethod.GET)
    public ResponseEntity<Object> getPlanets() {

        List<Planet> listPlanets = planetDAO.findAll();
        return new ResponseEntity<>(listPlanets, HttpStatus.OK);

    }

}
