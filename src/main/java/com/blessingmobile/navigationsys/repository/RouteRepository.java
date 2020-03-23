package com.blessingmobile.navigationsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.blessingmobile.navigationsys.model.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long>{

}
