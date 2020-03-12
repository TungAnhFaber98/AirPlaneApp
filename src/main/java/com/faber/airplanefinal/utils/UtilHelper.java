/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faber.airplanefinal.utils;

import java.util.ArrayList;

/**
 *
 * @author Engineer_Account
 */
public class UtilHelper {

    public ArrayList<String> generate150SeatCode() {
        ArrayList<String> listSeatCode = new ArrayList<>();
        String seatCode = "";
        for (int i = 1; i <= 25; i++) {
            listSeatCode.add(new String(i + "A"));
            listSeatCode.add(new String(i + "B"));
            listSeatCode.add(new String(i + "C"));
            listSeatCode.add(new String(i + "D"));
            listSeatCode.add(new String(i + "E"));
            listSeatCode.add(new String(i + "F"));
        }
        return listSeatCode;
    }
    
    public  int calculateDiscountPrice (int normalPrice,double discountRate){
        return (int) Math.round(normalPrice*discountRate);
    }
}
