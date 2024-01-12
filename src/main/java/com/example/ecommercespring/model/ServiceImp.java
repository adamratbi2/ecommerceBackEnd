package com.example.ecommercespring.model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class ServiceImp implements IService{
    @Override
    public void afficherDate() {
        System.out.println(LocalDate.now()+" "+ LocalTime.now());
    }
}
