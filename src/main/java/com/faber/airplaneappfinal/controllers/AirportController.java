/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faber.airplaneappfinal.controllers;

import com.faber.airplaneappfinal.constant.ConstantVariables;
import com.faber.airplaneappfinal.entities.Airport;
import com.faber.airplaneappfinal.exception.RecordNotFoundException;
import com.faber.airplaneappfinal.services.AirportService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Engineer_Account
 */
@Controller
@RequestMapping("/airport")
public class AirportController {

    @Autowired
    AirportService airportService;

    @PostMapping("/create")
    public String createAirport(Airport airport) {
        airportService.createOrUpdateNewAirport(airport);
        return "redirect:/airport/all";
    }

    @GetMapping(value = "/all")
    public String findAll(Model model) {
        ArrayList<Airport> airports = (ArrayList<Airport>) airportService.findAll();
        model.addAttribute("airports", airports);
        return "airport/view-all";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editEmployeeById(Model model, @PathVariable("id") Optional<Integer> id) throws RecordNotFoundException{
        if(id.isPresent()){
            Airport airport = airportService.getAirportById(id.get());
            model.addAttribute("status",ConstantVariables.updateStatus);
            model.addAttribute("airport",airport);
        }
        else{
            model.addAttribute("status",ConstantVariables.createStatus);
             model.addAttribute("airport", new Airport());
        }
        return "airport/add-edit";
    }

}
