package com.example.ecommercespring.service;


import com.example.ecommercespring.model.Client;
import com.example.ecommercespring.repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;



    public List<Client> getCLients(){
        return clientRepository.findAll();

    }

    public void addCLient(Client client){
        clientRepository.save(client);
    }

    public Client getCLientById(int id){
       return clientRepository.findById(id).get();

    }
    public void deleteCLient(Client client){
        clientRepository.delete(client);
    }

}
