/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blessingmobile.navigationsys.dao;

import com.blessingmobile.navigationsys.model.Traffic;
import com.blessingmobile.navigationsys.repository.TrafficRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author USER
 */
@Component
public class TrafficDAO implements DAO<Traffic>{
    
    @Autowired
    private TrafficRepository trafficRepository;

    @Override
    public void saveAll(List<Traffic> list) {
        trafficRepository.saveAll(list);
    }

    @Override
    public List<Traffic> findAll() {
        return trafficRepository.findAll();
    }

    @Override
    public long count() {
        return trafficRepository.count();
    }
    
}
