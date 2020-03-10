/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faber.airplaneappfinal.services;

import com.faber.airplaneappfinal.entities.Flight;
import com.faber.airplaneappfinal.exception.RecordNotFoundException;
import com.faber.airplaneappfinal.repositories.AirportRepository;
import com.faber.airplaneappfinal.repositories.FlightRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Engineer_Account
 */
@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    AirportRepository airportRepository;
    
    public Page<Flight> findPaginated(Pageable pageable) {
        List<Flight> flights = flightRepository.findAll();
        for(Flight flight: flights){
            flight.setRemainingQuantity(150);
        }
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Flight> list;
        if (flights.size() < startItem ) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, flights.size());
            list = flights.subList(startItem, toIndex);
        }
        Page<Flight> flightPage = new PageImpl<Flight>(list, PageRequest.of(currentPage, pageSize), flights.size());
        return flightPage;
    }

    public Flight createOrUpdateFlight(Flight flight) {
        if (flight.getId() == null) {
            flight = flightRepository.save(flight);
            return flight;
        } else {
            Optional<Flight> flightOptional = flightRepository.findById(flight.getId());
            if (flightOptional.isPresent()) {
                Flight newFlight = flightOptional.get();
                newFlight.setFlightCode(flight.getFlightCode());
                newFlight.setDepartureAirport(flight.getDepartureAirport());
                newFlight.setArrivalAirport(flight.getArrivalAirport());
                newFlight.setDepartureDatetime(flight.getDepartureDatetime());
                newFlight.setArrivalDatetime(flight.getDepartureDatetime());
                newFlight.setPrice(flight.getPrice());
                newFlight.setSeatQuantity(flight.getSeatQuantity());

                newFlight = flightRepository.save(newFlight);
                return newFlight;
            } else {
                flight = flightRepository.save(flight);
                return flight;
            }
        }
    }

    public Flight getFlightById(Integer id) throws RecordNotFoundException {
        Optional<Flight> flightOptional = flightRepository.findById(id);
        if (flightOptional.isPresent()) {
            return flightOptional.get();
        } else {
            throw new RecordNotFoundException("No flight record exists for given id");
        }
    }

    public void deleteFlightById(Integer id) throws RecordNotFoundException {
        Optional<Flight> flightOptional = flightRepository.findById(id);
        if (flightOptional.isPresent()) {
            flightRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No flight record exists for given id");
        }
    }
    
    public List<Flight> findFlightListForCustomer(int departureAirportId,int arrivalAirportId){
        return flightRepository.findFlightListForCustomer(departureAirportId, arrivalAirportId);
    }
}