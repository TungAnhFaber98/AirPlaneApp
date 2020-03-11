/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faber.airplaneappfinal.repositories;

import com.faber.airplaneappfinal.entities.Flight;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Engineer_Account
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

    // for customer to fight flights based on there search 
    @Query("SELECT  f  from  Flight f "
            + " left join Airport a on f.departureAirport.id = a.id and f.arrivalAirport.id = a.id"
            + " where f.departureAirport.id = ?1 and f.arrivalAirport.id = ?2"
            + " and f.departureDatetime BETWEEN ?3 and ?4")
    
//    @Query(" select f2 from (SELECT f from  Flight f " 
//            + " left join Airport a on f.departureAirport.id = a.id and f.arrivalAirport.id = a.id) Flight f2"
//            + " where f.departureAirport.id = ?1 and f.arrivalAirport.id = ?2"
//            + " and f.departureDatetime >= ?3)  ")
    
    List<Flight> findFlightListForCustomerOnewayType(int departureAiportId, int arrivalAirportId, Date departureDate, Date nextDate);

}
