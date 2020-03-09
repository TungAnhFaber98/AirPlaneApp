/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faber.airplaneappfinal.controllers;

import com.faber.airplaneappfinal.constant.ConstantVariables;
import com.faber.airplaneappfinal.entities.Airport;
import com.faber.airplaneappfinal.entities.Flight;
import com.faber.airplaneappfinal.exception.RecordNotFoundException;
import com.faber.airplaneappfinal.services.AirportService;
import com.faber.airplaneappfinal.services.FlightService;
import java.sql.Date;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Engineer_Account
 */
@Controller
@RequestMapping(path = "/flight")
public class FlightController {

    @Autowired
    FlightService flightService;

    @Autowired
    AirportService airportService;

    @PostMapping("/create")
    public String createFlight(Flight flight) {
        flightService.createOrUpdateFlight(flight);
        return "redirect:/flight/all";
    }

    @GetMapping(value = {"/all", "/"})
    public String findAll(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = page.orElse(5);

        Page<Flight> flightPage = flightService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("flightPage", flightPage);

        int totalPages = flightPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "flight/view-all";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editFlightById(Model model, @PathVariable("id") Optional<Integer> id) throws RecordNotFoundException {
        Flight flight = new Flight( Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), 1500000, 150);        
        if (id.isPresent()) {
            flight = flightService.getFlightById(id.get());
            model.addAttribute("status", ConstantVariables.updateStatus);
        } else {
            model.addAttribute("status", ConstantVariables.createStatus);
            model.addAttribute("departureAirport", new Airport());
            model.addAttribute("arrivalAirport", new Airport());
            model.addAttribute("flight", flight);
        }
        List<Airport> airportList = airportService.findAll();
        model.addAttribute("airportList", airportList);
        
        return "flight/add-edit";
    }

}
