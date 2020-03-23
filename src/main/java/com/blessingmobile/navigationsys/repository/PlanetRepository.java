package com.blessingmobile.navigationsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.blessingmobile.navigationsys.model.Planet;
import java.util.List;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long>{
    @Override
    List<Planet> findAll();
}
