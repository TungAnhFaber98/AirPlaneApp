/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faber.airplaneappfinal.models;

import java.util.Date;
import javax.persistence.Entity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Engineer_Account
 */
public class OrderModel {

    private Integer id;
    private Integer departureAirportId;
    private Integer arrivalAirportId;
    private boolean returnType;
    private int numOfAdult;
    private int numOfChildren;
    private int numOfSenior;
    private int departureFlightId;
    private int returnFlightId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;

    public OrderModel() {
        numOfAdult = 1;
        numOfChildren = 0;
        numOfSenior = 0;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartureAirportId() {
        return departureAirportId;
    }

    public void setDepartureAirportId(Integer departureAirportId) {
        this.departureAirportId = departureAirportId;
    }

    public Integer getArrivalAirportId() {
        return arrivalAirportId;
    }

    public void setArrivalAirportId(Integer arrivalAirportId) {
        this.arrivalAirportId = arrivalAirportId;
    }

    public int getNumOfAdult() {
        return numOfAdult;
    }

    public void setNumOfAdult(int numOfAdult) {
        this.numOfAdult = numOfAdult;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturnType() {
        return returnType;
    }

    public void setReturnType(boolean returnType) {
        this.returnType = returnType;
    }

    public int getNumOfChildren() {
        return numOfChildren;
    }

    public void setNumOfChildren(int numOfChildren) {
        this.numOfChildren = numOfChildren;
    }

    public int getNumOfSenior() {
        return numOfSenior;
    }

    public void setNumOfSenior(int numOfSenior) {
        this.numOfSenior = numOfSenior;
    }

    public int getDepartureFlightId() {
        return departureFlightId;
    }

    public void setDepartureFlightId(int departureFlightId) {
        this.departureFlightId = departureFlightId;
    }

    public int getReturnFlightId() {
        return returnFlightId;
    }

    public void setReturnFlightId(int returnFlightId) {
        this.returnFlightId = returnFlightId;
    }

    
    public String toString() {
        return "Departure Airport: " + departureAirportId
                + " | Arrival Airport: " + arrivalAirportId
                + " | Is Return ? " + isReturnType()
                + " | Num of Passengers: " + numOfAdult
                + " | Depart Date: " + departureDate
                + " | Return Date: " + returnDate;
    }

}
