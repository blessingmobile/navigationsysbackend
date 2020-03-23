/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blessingmobile.navigationsys.dao;

import com.blessingmobile.navigationsys.model.Planet;
import com.blessingmobile.navigationsys.repository.PlanetRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author USER
 */
@Component
public class PlanetDAO implements DAO<Planet>{
    
    @Autowired
    private PlanetRepository planetRepository;

    @Override
    public void saveAll(List<Planet> list) {
        planetRepository.saveAll(list);
    }

    @Override
    public List<Planet> findAll() {
        
        List<Planet> listPlanets = planetRepository.findAll();
         for (int i = 0; i < listPlanets.size(); i++) {
            Planet p = listPlanets.get(i);
            p.setId(i);//Adding indices that will be used by UI drop downs for origin and destination
        }
        
        return listPlanets;
    }

    @Override
    public long count() {
        return planetRepository.count();
    }
    
}
