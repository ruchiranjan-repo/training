package com.demoairline.flightmonitoring.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demoairline.flightmonitoring.entity.FlightSchedule;

@Repository
public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Long> {

	Optional<FlightSchedule> findByScheduleId(Long scheduleId);

	List<FlightSchedule> findAllByFlightCode(String flightCode);

	Optional<FlightSchedule> findByRunwayIDAndScheduledDateTimeAndScheduleStatus(Long runwayId,
			LocalDateTime scheduledDateTime, String scheduleStatus);
}
