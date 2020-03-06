/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faber.airplaneappfinal.services;

import com.faber.airplaneappfinal.entities.CustomerType;
import com.faber.airplaneappfinal.repositories.CustomerTypeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Engineer_Account
 */
@Service
public class CustomerTypeService {

    @Autowired
    CustomerTypeRepository customerTypeRepository;

    public CustomerType findCustomerTypeById(int id) {
        return customerTypeRepository.getOne(id);
    }
    public List<CustomerType> findAll(){
        return customerTypeRepository.findAll();
    }

}
