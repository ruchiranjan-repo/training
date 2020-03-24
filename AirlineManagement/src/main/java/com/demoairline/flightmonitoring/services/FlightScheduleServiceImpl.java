package com.demoairline.flightmonitoring.services;

import java.time.LocalDateTime;
/**
 * Implementation for Flight schedule service.
 * @author Ruchi
 */
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.demoairline.flightmonitoring.constants.RunwayAvailabilityStatusEnum;
import com.demoairline.flightmonitoring.dto.FlightScheduleRequestDTO;
import com.demoairline.flightmonitoring.dto.FlightScheduleUpdateRequest;
import com.demoairline.flightmonitoring.dto.MessageResponseDto;
import com.demoairline.flightmonitoring.entity.Airport;
import com.demoairline.flightmonitoring.entity.Flight;
import com.demoairline.flightmonitoring.entity.FlightSchedule;
import com.demoairline.flightmonitoring.entity.Runway;
import com.demoairline.flightmonitoring.exception.AirportNotFoundException;
import com.demoairline.flightmonitoring.exception.AirportRunwayNotFoundException;
import com.demoairline.flightmonitoring.exception.FlightNotFoundException;
import com.demoairline.flightmonitoring.exception.FlightScheduleDateTimeException;
import com.demoairline.flightmonitoring.exception.FlightScheduleNotFoundException;
import com.demoairline.flightmonitoring.exception.NoRunwayAvailableException;
import com.demoairline.flightmonitoring.repositories.Airportrepository;
import com.demoairline.flightmonitoring.repositories.FlightRepository;
import com.demoairline.flightmonitoring.repositories.FlightScheduleRepository;
import com.demoairline.flightmonitoring.repositories.RunwayRepository;

@Service
public class FlightScheduleServiceImpl implements FlightScheduleService {

	@Autowired
	FlightRepository flightRepository;
	@Autowired
	Airportrepository airportRepository;
	@Autowired
	RunwayRepository runwayRepository;
	@Autowired
	FlightScheduleRepository flightScheduleRepository;

	Logger log = Logger.getLogger(FlightScheduleServiceImpl.class);

	/**
	 * Method is used to add flight schedule.
	 * 
	 * @param flightScheduleRequestDTO FlightScheduleRequestDTO
	 */
	@Override
	public MessageResponseDto addFlightSchedule(FlightScheduleRequestDTO flightScheduleRequestDTO) {
		if (flightScheduleRequestDTO.getScheduledDateTime().isBefore(LocalDateTime.now())) {
			log.warn("Flight schedule date should be in future.");
			throw new FlightScheduleDateTimeException(flightScheduleRequestDTO.getScheduledDateTime());
		}
		Optional<Flight> flight = flightRepository.findByFlightId(flightScheduleRequestDTO.getFlightId());
		if (flight.isPresent()) {
			Optional<Airport> airport = airportRepository.findByAirportId(flightScheduleRequestDTO.getAirportId());
			if (airport.isPresent()) {
				List<Runway> runways = airport.get().getRunways();
				if (!CollectionUtils.isEmpty(runways)) {

					for (Runway runway : runways) {
						Optional<FlightSchedule> scheduledFlightForRunwayAndDateTime = flightScheduleRepository
								.findByRunwayIDAndScheduledDateTime(runway.getRunwayID(),
										flightScheduleRequestDTO.getScheduledDateTime());
						if (!scheduledFlightForRunwayAndDateTime.isPresent()) {
							Long runwayId = runway.getRunwayID();

							FlightSchedule flightSchedule = new FlightSchedule();
							flightSchedule.setAirport(airport.get());
							flightSchedule.setFlight(flight.get());
							flightSchedule.setFlightCode(flight.get().getFlightCode());
							flightSchedule.setRunwayID(runwayId);
							flightSchedule.setScheduledDateTime(flightScheduleRequestDTO.getScheduledDateTime());
							flightSchedule.setScheduleType(flightScheduleRequestDTO.getScheduleType());

							flightScheduleRepository.save(flightSchedule);

							MessageResponseDto messageResponseDto = new MessageResponseDto();
							messageResponseDto.setMessage("Flight Scheduled  successfully");
							messageResponseDto.setStatusCode(620L);
							log.info("Flight scheduled successfully .");
							return messageResponseDto;

						}
					}

				}
				log.warn("No runway available to schedule the takoff or landing of the flight "
						+ flightScheduleRequestDTO.getFlightId() + " on airport  "
						+ flightScheduleRequestDTO.getAirportId());
				throw new NoRunwayAvailableException(flightScheduleRequestDTO.getFlightId(),
						flightScheduleRequestDTO.getAirportId());
			}
			log.warn("Airport with airport id " + flightScheduleRequestDTO.getAirportId() + "does not exists.");
			throw new AirportNotFoundException(flightScheduleRequestDTO.getAirportId());

		}
		log.warn("Flight with flight id " + flightScheduleRequestDTO.getFlightId() + "does not exists.");
		throw new FlightNotFoundException(flightScheduleRequestDTO.getFlightId());

	}

	/**
	 * Method is used to update flight schedule.
	 * 
	 * @param flightScheduleRequestDTO FlightScheduleUpdateRequest
	 */
	@Override
	public MessageResponseDto updateFlightSchedule(FlightScheduleUpdateRequest flightScheduleRequestDTO) {

		Optional<FlightSchedule> flightSchedule = flightScheduleRepository
				.findByScheduleId(flightScheduleRequestDTO.getScheduleId());

		if (flightSchedule.isPresent()) {
			if (flightScheduleRequestDTO.getScheduledDateTime() != null) {
				if (flightScheduleRequestDTO.getScheduledDateTime().isAfter(LocalDateTime.now())) {
					flightSchedule.get().setScheduledDateTime(flightScheduleRequestDTO.getScheduledDateTime());
				} else {
					log.warn("Flight schedule date should be in future.");
					throw new FlightScheduleDateTimeException(flightScheduleRequestDTO.getScheduledDateTime());
				}
			}

			if (flightScheduleRequestDTO.getRunwayId() != null) {
				Airport airport = flightSchedule.get().getAirport();
				if (airport != null) {
					if (!CollectionUtils.isEmpty(airport.getRunways())) {

						boolean isRunwayFound = false;
						for (Runway runway : airport.getRunways()) {
							if (flightScheduleRequestDTO.getRunwayId() == runway.getRunwayID()) {
								isRunwayFound = true;
								Optional<FlightSchedule> scheduledFlightForRunwayAndDateTime = flightScheduleRepository
										.findByRunwayIDAndScheduledDateTime(flightScheduleRequestDTO.getRunwayId(),
												flightSchedule.get().getScheduledDateTime());

								if (!scheduledFlightForRunwayAndDateTime.isPresent()) {

									flightSchedule.get().setRunwayID(flightScheduleRequestDTO.getRunwayId());

								} else {
									log.warn("Provided runway is not available to schedule the takoff or landing of the flight "
											+ flightSchedule.get().getFlight().getFlightId() + " on airport  "
											+ flightSchedule.get().getAirport().getAirportId());
									throw new NoRunwayAvailableException(flightSchedule.get().getFlight().getFlightId(),
											flightSchedule.get().getAirport().getAirportId());
								}
							}

						}

						if (isRunwayFound == false) {
							log.warn("Runway with runwayId "
									+ (flightScheduleRequestDTO.getRunwayId() + " is not available on airport id "
											+ flightSchedule.get().getAirport().getAirportId()));
							throw new AirportRunwayNotFoundException(flightScheduleRequestDTO.getRunwayId(),
									flightSchedule.get().getAirport().getAirportId());
						}

					} else {
						log.warn("Runway with runwayId " + flightScheduleRequestDTO.getRunwayId()
								+ " is not available on airport id "
								+ flightSchedule.get().getAirport().getAirportId());
						throw new AirportRunwayNotFoundException(flightScheduleRequestDTO.getRunwayId(),
								flightSchedule.get().getAirport().getAirportId());
					}
				}
			}
			flightScheduleRepository.save(flightSchedule.get());
			MessageResponseDto messageResponseDto = new MessageResponseDto();
			messageResponseDto.setMessage("Flight Scheduled updated successfully");
			messageResponseDto.setStatusCode(620L);
			log.info("Flight schedule updated successfully");
			return messageResponseDto;

		} else {
			throw new FlightScheduleNotFoundException(flightScheduleRequestDTO.getScheduleId());
		}
	}

}
