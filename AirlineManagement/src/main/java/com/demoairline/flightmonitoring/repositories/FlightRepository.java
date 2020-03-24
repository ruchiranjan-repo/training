package com.demoairline.flightmonitoring.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demoairline.flightmonitoring.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
	
	Optional<Flight> findByFlightId(Long flightId);

}
