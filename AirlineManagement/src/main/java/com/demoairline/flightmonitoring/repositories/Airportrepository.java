package com.demoairline.flightmonitoring.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demoairline.flightmonitoring.entity.Airport;

@Repository
public interface Airportrepository extends JpaRepository<Airport, Long> {

	Optional<Airport> findByAirportId(Long airportId);
}
