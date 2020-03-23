/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blessingmobile.navigationsys.dao;

import com.blessingmobile.navigationsys.model.Route;
import com.blessingmobile.navigationsys.repository.RouteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author USER
 */
@Component
public class RouteDAO implements DAO<Route>{
    
    @Autowired
    private RouteRepository routeRepository;

    @Override
    public void saveAll(List<Route> list) {
        routeRepository.saveAll(list);
    }

    @Override
    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    @Override
    public long count() {
        return routeRepository.count();
    }
    
}
