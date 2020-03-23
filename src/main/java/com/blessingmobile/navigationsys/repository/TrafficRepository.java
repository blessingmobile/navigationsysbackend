package com.blessingmobile.navigationsys.repository;

import com.blessingmobile.navigationsys.model.Traffic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrafficRepository extends JpaRepository<Traffic, Long>{

}
