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
import com.faber.airplanefinal.utils.FormatDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Engineer_Account
 */
@Controller
@RequestMapping(value = "")
public class MainController {

    @Autowired
    AirportService airportService;

    @Autowired
    FlightService flightService;
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    // get to main page, for searching flight
    @RequestMapping()
    public String index(Model model, HttpSession session) {
        ArrayList<Airport> airportList = (ArrayList<Airport>) airportService.findAll();
        model.addAttribute("airportList", airportList);
        OrderModel orderModel = new OrderModel();
        orderModel.setReturnType(true);
        model.addAttribute("orderModel", orderModel);
        return "index";
    }

    // handle submit from customer search page
    @PostMapping(path = "/flight/search")
    public String searchFlight(
            @ModelAttribute("orderModel") OrderModel orderModel,
            HttpSession session,
            ModelMap modelMap) {

        // get departure date
        Date utilDepartureDate = orderModel.getDepartureDate();
        
        // get departure and arrival airport
        Airport departureAirport = airportService.findAirportById(orderModel.getDepartureAirportId());
        Airport arrivalAirport = airportService.findAirportById(orderModel.getArrivalAirportId());

        ArrayList<Flight> departureFlightList
                = (ArrayList<Flight>) flightService.findFlightListForCustomerOnewayType(orderModel);
        ArrayList<Flight> returnFlightList =new ArrayList<>();
        
        if(orderModel.isReturnType()){
            Date utilReturnDate = orderModel.getReturnDate();
            returnFlightList = (ArrayList<Flight>) flightService.findFlightListForCustomerReturn(orderModel);
        }
        
        modelMap.addAttribute("orderModel", orderModel);
        modelMap.addAttribute("departureAirport", departureAirport);
        modelMap.addAttribute("arrivalAirport", arrivalAirport);
        modelMap.addAttribute("departureFlightList", departureFlightList);
        modelMap.addAttribute("returnFlightList", returnFlightList);
        return "flight-list";
    }
    
    @GetMapping(path="/flight/test")
    public String test(@ModelAttribute("orderModel") OrderModel orderModel){
        LOGGER.info(orderModel.getDepartureFlightId()+"");
        return "test";
    }

}
