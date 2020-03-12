/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faber.airplaneappfinal.services;

import com.faber.airplaneappfinal.entities.OrderFlight;
import com.faber.airplaneappfinal.exception.RecordNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.faber.airplaneappfinal.repositories.OrderFlightRepository;

/**
 *
 * @author tunganh
 */
@Service
public class OrderFlightService {

    @Autowired
    OrderFlightRepository orderRepository;

    public OrderFlight findOrderFlightById(int id) {
        return orderRepository.getOne(id);
    }
}
