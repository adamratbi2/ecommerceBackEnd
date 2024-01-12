package com.example.ecommercespring.client;
import com.example.ecommercespring.model.Client;
import com.example.ecommercespring.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path="/clients")
public class ControlleurClient {


    @Autowired
    private ClientService clientService;


    @GetMapping(path="/",produces="application/json")
    public List<Client> getClients(){
        return clientService.getCLients();
    }


    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addClient(@RequestBody Client client){
        clientService.addCLient(client);
    }


    @GetMapping("/{id}")
    public Client getClientById(@PathVariable("id")int id){
        return clientService.getCLientById(id);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable("id")int id){
        clientService.deleteCLient(clientService.getCLientById(id));
    }



}
