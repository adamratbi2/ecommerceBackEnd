package com.example.ecommercespring;

import com.example.ecommercespring.model.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceSpringApplication implements CommandLineRunner {

    @Autowired
    private IService service;

    public static void main(String[] args) {
        SpringApplication.run(EcommerceSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        service.afficherDate();
    }
}
