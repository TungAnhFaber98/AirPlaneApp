/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faber.airplaneappfinal.controllers;

import com.faber.airplaneappfinal.entities.Airport;
import com.faber.airplaneappfinal.entities.Flight;
import com.faber.airplaneappfinal.models.OrderModel;
import com.faber.airplaneappfinal.services.AirportService;
import com.faber.airplaneappfinal.services.FlightService;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Engineer_Account
 */
@Controller
@RequestMapping(value="")
public class MainController {
    @Autowired
    AirportService airportService;
    
    @Autowired
    FlightService flightService;
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
    
    @RequestMapping()
    public String index(Model model, HttpSession session){
        ArrayList<Airport> airportList = (ArrayList<Airport>) airportService.findAll();
        model.addAttribute("airportList", airportList);
        OrderModel orderModel= new OrderModel();
        orderModel.setReturnType(true);
        model.addAttribute("orderModel",orderModel);
        return "index";
    }
    
    @PostMapping(path="/flight/search")
    public String searchFlight(@ModelAttribute("orderModel") OrderModel orderModel){
        Flight flight = new Flight();
      
        ArrayList<Flight> flightList = (ArrayList<Flight>) flightService.findFlightListForCustomer(orderModel.getDepartureAirportId(),orderModel.getArrivalAirportId());
        for(Flight f:flightList){
            LOGGER.info(f.getFlightCode());
        }
        return "test";
    }
}
