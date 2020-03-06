/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faber.airplaneappfinal.services;

import com.faber.airplaneappfinal.entities.Airport;
import com.faber.airplaneappfinal.exception.RecordNotFoundException;
import com.faber.airplaneappfinal.repositories.AirportRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Engineer_Account
 */
@Service
public class AirportService {
    @Autowired
    AirportRepository airportRepository;
    
    public Airport findAirportById(int id){
        return airportRepository.getOne(id);
    }
    public Airport createOrUpdateNewAirport(Airport airport){
        if(airport.getId()==null){
            airport = airportRepository.save(airport);
            return airport;
        }
        else{
            Optional<Airport> airportOptional = airportRepository.findById(airport.getId());
            if(airportOptional.isPresent()){
                Airport newAirport = airportOptional.get();
                newAirport.setLocation(airport.getLocation());
                newAirport.setName(airport.getName());
                
                newAirport =airportRepository.save(newAirport);
                return newAirport;
            }
            else{
                airport = airportRepository.save(airport);
                return airport;
            }
        }      
    }
    public List<Airport> findAll(){
        return airportRepository.findAll();
    }
    
    public Airport getAirportById(Integer id) throws RecordNotFoundException{
        Optional<Airport> airportOptional = airportRepository.findById(id);
        if(airportOptional.isPresent()){
            return airportOptional.get();
        }
        else{
            throw new RecordNotFoundException("No airport record exist for given id");
        }
    }
    
}
