/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faber.airplaneappfinal.services;

import com.faber.airplaneappfinal.entities.Flight;
import com.faber.airplaneappfinal.entities.Ticket;
import com.faber.airplaneappfinal.repositories.TicketRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tunganh
 */
@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    
    public Ticket findByTicketId(int id){
        return ticketRepository.getOne(id);
    }
    public List<String> findByFlightId(int id){
        return ticketRepository.findOrderedSeatCodeListByFlightId(id);
    }
}
