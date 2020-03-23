/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blessingmobile.navigationsys.dijkstra.engine;

import com.blessingmobile.navigationsys.dao.PlanetDAO;
import com.blessingmobile.navigationsys.dao.RouteDAO;
import com.blessingmobile.navigationsys.dao.TrafficDAO;
import com.blessingmobile.navigationsys.dijkstra.model.Edge;
import com.blessingmobile.navigationsys.model.Planet;
import com.blessingmobile.navigationsys.model.Route;
import com.blessingmobile.navigationsys.model.Traffic;
import com.blessingmobile.navigationsys.model.Path;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.blessingmobile.navigationsys.dijkstra.model.Graph;
import com.blessingmobile.navigationsys.dijkstra.model.Vertex;
import com.blessingmobile.navigationsys.model.DistanceResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

/**
 *
 * @author USER Shortest path calculator extracted from
 * //https://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
 */
@Component
public class ShortestPathCalculator {

    private List<com.blessingmobile.navigationsys.dijkstra.model.Vertex> nodes;
    private List<com.blessingmobile.navigationsys.dijkstra.model.Edge> edges;

    @Autowired
    private PlanetDAO planetDAO;
    @Autowired
    private RouteDAO routeDAO;
    @Autowired
    private TrafficDAO trafficDAO;

    public DistanceResponse getShortestPath(Path apath) {

        List<Planet> listPlanets = planetDAO.findAll();
        List<Route> listRoutes = routeDAO.findAll();
        List<Traffic> listWithTraffic = trafficDAO.findAll();

        Map<String, Planet> planetHelper = new HashMap<>();
        Map<String, Integer> edgeHelper = new HashMap<>();
        nodes = new ArrayList<>();
        edges = new ArrayList<>();

        for (int i = 0; i < listPlanets.size(); i++) {
            Planet planet = listPlanets.get(i);
            Vertex location = new Vertex(planet.getPlanetNode(), planet.getPlanetName());
            nodes.add(location);
            edgeHelper.put(planet.getPlanetNode(), i);
            planetHelper.put(planet.getPlanetNode(), planet);
        }

        for (int i = 0; i < listRoutes.size(); i++) {
            Route route = listRoutes.get(i);
            Traffic traffic = listWithTraffic.get(i);
            traffic.setDistance(traffic.getDistance() + route.getDistance());//route+traffic

            if (edgeHelper.containsKey(route.getPlanetOrigin())
                    && edgeHelper.containsKey(route.getPlanetDestination())
                    && apath.isTrafficEnabled() == false) {
                int originNode = edgeHelper.get(route.getPlanetOrigin());
                int destinationNode = edgeHelper.get(route.getPlanetDestination());

                addLane("Edge_" + i, originNode,
                         destinationNode,
                        route.getDistance());

            } else if (edgeHelper.containsKey(route.getPlanetOrigin())
                    && edgeHelper.containsKey(route.getPlanetDestination())
                    && apath.isTrafficEnabled() == true) {
                int originNode = edgeHelper.get(route.getPlanetOrigin());
                int destinationNode = edgeHelper.get(route.getPlanetDestination());
                addLane("Edge_" + i, originNode,
                         destinationNode,
                        traffic.getDistance());
            }
        }

        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(nodes.get(Integer.parseInt(apath.getFromRouteId())));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(Integer.parseInt(apath.getToRouteId())));

        for (Vertex vertex : path) {

            //System.out.println(planetHelper.get(vertex.getId()));
        }

        Map<Vertex, Double> distanceMap = dijkstra.getDistanceMap();
        
        DistanceResponse distanceResponse = new DistanceResponse(distanceMap.get(path.getLast())
        ,path);
        
        //System.out.println("distanceResponse=" + distanceResponse);

        return distanceResponse;
    }

    private void addLane(String laneId, int sourceLocNo, int destLocNo,
            double duration) {
        com.blessingmobile.navigationsys.dijkstra.model.Edge lane = new com.blessingmobile.navigationsys.dijkstra.model.Edge(laneId, nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
        edges.add(lane);
    }

   
}
