package com.example.ecommercespring.client;

import com.example.ecommercespring.model.Client;
import com.example.ecommercespring.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path="/trying")
public class ClientWebController {

    @Autowired
    private ClientService clientService;


    @GetMapping("/list")
    public String getClientList(Model model) {
        List<Client> clients = clientService.getCLients();
        model.addAttribute("clients", clients);
        return "index";
    }


    @GetMapping("/delete/{clientId}")
    public String deleteClient(@PathVariable int clientId) {
        System.out.println(clientId);
        clientService.deleteCLient(clientService.getCLientById(clientId));
        return "redirect:/trying/list";
    }

}
