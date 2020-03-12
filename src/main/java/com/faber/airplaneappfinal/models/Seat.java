/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faber.airplaneappfinal.models;

/**
 *
 * @author tunganh
 */
public class Seat {
    private String seatCode;
    private int status;

    public Seat() {
    }

    public Seat(String seatCode, int status) {
        this.seatCode = seatCode;
        this.status = status;
    }

    
    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
