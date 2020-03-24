package com.demoairline.flightmonitoring.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demoairline.flightmonitoring.entity.FlightSchedule;

@Repository
public interface FlightScheduleRepository extends JpaRepository<FlightSchedule,Long>{
	
	Optional<FlightSchedule> findByRunwayIDAndScheduledDateTime(Long runwayId,LocalDateTime scheduledDateTime);
	Optional<FlightSchedule> findByScheduleId(Long scheduleId);
}
