/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faber.airplaneappfinal.repositories;

import com.faber.airplaneappfinal.entities.OrderFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tunganh
 */
@Repository
public interface OrderFlightRepository extends JpaRepository<OrderFlight, Integer>{
    
}
