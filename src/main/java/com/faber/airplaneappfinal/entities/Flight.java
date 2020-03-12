/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faber.airplaneappfinal.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Engineer_Account
 */
@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "flight_code")
    private String flightCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "departure_airport_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Airport departureAirport;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "arrival_airport_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Airport arrivalAirport;

    @Column(name = "departure_date_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date departureDatetime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "arrival_date_time")
    private Date arrivalDatetime;

    private int price;

    @Column(name = "seat_quantity")
    private int seatQuantity;

    @Transient
    private int remainingQuantity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "flight")
    List<Ticket> tickets = new ArrayList<>();

    public Flight(Date departureDatetime, Date arrivalDatetime, int price, int seatQuantity) {

        this.departureDatetime = departureDatetime;
        this.arrivalDatetime = arrivalDatetime;
        this.price = price;
        this.seatQuantity = seatQuantity;
    }

    public Flight() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Date getDepartureDatetime() {
        return departureDatetime;
    }

    public void setDepartureDatetime(Date departureDatetime) {
        this.departureDatetime = departureDatetime;
    }

    public Date getArrivalDatetime() {
        return arrivalDatetime;
    }

    public void setArrivalDatetime(Date arrivalDatetime) {
        this.arrivalDatetime = arrivalDatetime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSeatQuantity() {
        return seatQuantity;
    }

    public void setSeatQuantity(int seatQuantity) {
        this.seatQuantity = seatQuantity;
    }

    public int getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(int remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

}
