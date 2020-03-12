/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faber.airplaneappfinal.repositories;

import com.faber.airplaneappfinal.entities.Ticket;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tunganh
 */
@Repository
public interface TicketRepository  extends JpaRepository<Ticket,Integer>{
//    @Query("SELECT  f  from  Flight f "
//            + " left join Airport a on f.departureAirport.id = a.id and f.arrivalAirport.id = a.id"
//            + " where f.departureAirport.id = ?1 and f.arrivalAirport.id = ?2"
//            + " and f.departureDatetime BETWEEN ?3 and ?4")
   @Query("SELECT t.seatNumber from Ticket t left join Flight f on t.flight.id = f.id "
           + " where t.flight.id = ?1") 
    public List<String> findOrderedSeatCodeListByFlightId(int flightId);
}
