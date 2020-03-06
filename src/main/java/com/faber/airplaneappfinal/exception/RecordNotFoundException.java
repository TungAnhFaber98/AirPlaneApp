/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faber.airplaneappfinal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Engineer_Account
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends Exception{
    private static final long serialVersioUID = 1L;
    public RecordNotFoundException(String message){
        super(message);
    }
    public RecordNotFoundException(String message, Throwable t){
        super(message,t);
    }
}
