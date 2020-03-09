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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        airportService.createOrUpdateAirport(airport);
        return "redirect:/airport/all";
    }

    @GetMapping(value = {"/all","/"})
    public String findAll(Model model,@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
//        ArrayList<Airport> airports = (ArrayList<Airport>) airportService.findAll();
//        model.addAttribute("airports", airports);
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        
        Page<Airport> airportPage = airportService.findPaginated(PageRequest.of(currentPage-1, pageSize));
        model.addAttribute("airportPage",airportPage);
        
        int totalPages = airportPage.getTotalPages();
        if(totalPages>0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers",pageNumbers);
        }
        return "airport/view-all";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editAirportById(Model model, @PathVariable("id") Optional<Integer> id) throws RecordNotFoundException{
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
    
    @RequestMapping(path="/delete/{id}")
    public String deleteAirportById(Model model,@PathVariable("id")Integer id)
            throws RecordNotFoundException{
        airportService.deleteAirportById(id);
        return "redirect:/airport/";
    }
    
   
    

}
