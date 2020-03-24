package com.demoairline.flightmonitoring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demoairline.flightmonitoring.entity.Runway;

@Repository
public interface RunwayRepository extends JpaRepository<Runway,Long> {

	
}
