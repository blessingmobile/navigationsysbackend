/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blessingmobile.navigationsys.dao;

import java.util.List;

/**
 *
 * @author USER
 */
public interface DAO<T> {
    public void saveAll(List<T> list);
    public List<T> findAll();
    public long count();
}
