/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faber.airplaneappfinal.controllers;

import com.faber.airplaneappfinal.constant.ConstantVariables;
import com.faber.airplaneappfinal.entities.Airport;
import com.faber.airplaneappfinal.entities.Flight;
import com.faber.airplaneappfinal.entities.OrderFlight;
import com.faber.airplaneappfinal.exception.RecordNotFoundException;
import com.faber.airplaneappfinal.models.OrderModel;
import com.faber.airplaneappfinal.models.Seat;
import com.faber.airplaneappfinal.services.AirportService;
import com.faber.airplaneappfinal.services.FlightService;
import com.faber.airplaneappfinal.services.OrderFlightService;
import com.faber.airplaneappfinal.services.TicketService;
import com.faber.airplanefinal.utils.UtilHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

    @Autowired
    OrderFlightService orderFlightService;

    @Autowired
    TicketService ticketService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    // get to main page, for searching flight
    @RequestMapping()
    public String index(Model model) {
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
            Model model) {

        // get departure date
        Date utilDepartureDate = orderModel.getDepartureDate();

        // get departure and arrival airport
        Airport departureAirport = airportService.findAirportById(orderModel.getDepartureAirportId());
        Airport arrivalAirport = airportService.findAirportById(orderModel.getArrivalAirportId());

        ArrayList<Flight> departureFlightList
                = (ArrayList<Flight>) flightService.findFlightListForCustomerOnewayType(orderModel);
        ArrayList<Flight> returnFlightList = new ArrayList<>();

        if (orderModel.isReturnType()) {
            Date utilReturnDate = orderModel.getReturnDate();
            returnFlightList = (ArrayList<Flight>) flightService.findFlightListForCustomerReturn(orderModel);
        }

        model.addAttribute("orderModel", orderModel);
        model.addAttribute("departureAirport", departureAirport);
        model.addAttribute("arrivalAirport", arrivalAirport);
        model.addAttribute("departureFlightList", departureFlightList);
        model.addAttribute("returnFlightList", returnFlightList);
        return "flight-list";
    }

    // check price of both departure and return flight (if have)
    @GetMapping(path = "/flight/checkPrice")
    public String checkPrice(@ModelAttribute("orderModel") OrderModel orderModel,
            Model model) throws RecordNotFoundException {

        Flight departureFlight = flightService.getFlightById(orderModel.getDepartureFlightId());
        Airport departureAirport = airportService.getAirportById(orderModel.getDepartureAirportId());
        Airport arrivalAirport = airportService.getAirportById(orderModel.getArrivalAirportId());

        // calculate total price of departure flight order
        int departFlightPrice = departureFlight.getPrice();
        int departureFlightPriceDiscount = new UtilHelper().calculateDiscountPrice(departFlightPrice, ConstantVariables.feeDiscount);
        int departureTotalPrice = departFlightPrice * orderModel.getNumOfAdult() + orderModel.getNumOfChildren() * departureFlightPriceDiscount
                + departureFlightPriceDiscount * orderModel.getNumOfSenior();

        // add departure flight discount price
        model.addAttribute("departureFlightPriceDiscount", departureFlightPriceDiscount);
        // add departure flight order object and its total price
        model.addAttribute("departureTotalPrice", departureTotalPrice);

        // if customer order return type
        if (orderModel.isReturnType()) {
            Flight returnFlight = flightService.getFlightById(orderModel.getReturnFlightId());

            // calculate total price of return flight order
            int returnFlightPrice = returnFlight.getPrice();
            int returnFlightPriceDiscount = new UtilHelper().calculateDiscountPrice(returnFlightPrice, ConstantVariables.feeDiscount);
            int returnTotalPrice = returnFlightPrice * orderModel.getNumOfAdult() + returnFlightPriceDiscount * orderModel.getNumOfChildren()
                    + returnFlightPriceDiscount * orderModel.getNumOfSenior();

            // add return flight discount price
            model.addAttribute("returnFlightPriceDiscount", returnFlightPriceDiscount);
            // add return flight order object and its total price
            model.addAttribute("returnFlight", returnFlight);
            model.addAttribute("returnTotalPrice", returnTotalPrice);
        }

        model.addAttribute("departureAirport", departureAirport);
        model.addAttribute("arrivalAirport", arrivalAirport);
        model.addAttribute("departureFlight", departureFlight);
        return "check-price";
    }

    @GetMapping(path = "/flight/chooseSeat")
    public String chooseSeat(@ModelAttribute("orderModel") OrderModel orderModel,
            Model model) throws RecordNotFoundException {

        ArrayList<String> listSeatCode = new UtilHelper().generate150SeatCode();
        ArrayList<String> listOrderedSeatCode = (ArrayList<String>) ticketService.findByFlightId(15);
        ArrayList<Seat> listSeatCodeWithStatus = new ArrayList<>();
        for (int i = 0; i < listSeatCode.size(); i++) {
            if (listOrderedSeatCode.contains(listSeatCode.get(i))) {
                listSeatCodeWithStatus.add(new Seat(listSeatCode.get(i), ConstantVariables.seatNotAvailabe));
            } else {
                listSeatCodeWithStatus.add(new Seat(listSeatCode.get(i), ConstantVariables.seatAvailable));
            }
        }
        model.addAttribute("listSeat",listSeatCodeWithStatus);
        String[] bkb = new String[3];
        orderModel.setListSeat(bkb);
        model.addAttribute("orderModel", orderModel);
        return "choose-seat";
    }

    @GetMapping(path = "/flight/customerName")
    public String customerName(@ModelAttribute("orderModel") OrderModel orderModel) {
        LOGGER.info(orderModel.getListSeat().length+"");
        return "customer-name";
    }

}
