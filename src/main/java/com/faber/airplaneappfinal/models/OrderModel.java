/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faber.airplaneappfinal.models;

import java.util.ArrayList;
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
    private ArrayList<String> listSeatDeparture;
    private ArrayList<String> listSeatReturn;
    private ArrayList<String> adultList;
    private ArrayList<String> childrenList;
    private ArrayList<String> seniorList;
    private String representativeName;
    private String email;
    private String phone;
        
    
    
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

    public ArrayList<String> getListSeatDeparture() {
        return listSeatDeparture;
    }

    public void setListSeatDeparture(ArrayList<String> listSeatDeparture) {
        this.listSeatDeparture = listSeatDeparture;
    }

    public ArrayList<String> getAdultList() {
        return adultList;
    }

    public void setAdultList(ArrayList<String> adultList) {
        this.adultList = adultList;
    }

    public ArrayList<String> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(ArrayList<String> childrenList) {
        this.childrenList = childrenList;
    }

    public ArrayList<String> getSeniorList() {
        return seniorList;
    }

    public void setSeniorList(ArrayList<String> seniorList) {
        this.seniorList = seniorList;
    }

    public String getRepresentativeName() {
        return representativeName;
    }

    public void setRepresentativeName(String representativeName) {
        this.representativeName = representativeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email= email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<String> getListSeatReturn() {
        return listSeatReturn;
    }

    public void setListSeatReturn(ArrayList<String> listSeatReturn) {
        this.listSeatReturn = listSeatReturn;
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
