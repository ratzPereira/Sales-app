package com.ratz.controller;

import com.ratz.entity.Client;
import com.ratz.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientServiceImpl clientService;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity getClientById(@PathVariable Integer id) {

       Optional<Client> client = clientService.findClientById(id);

       if(!client.isPresent()){
           System.out.println("Not Found");
           return ResponseEntity.notFound().build();

       }
       System.out.println("Found");
       return ResponseEntity.ok(client);
    }

    @PostMapping()
    @ResponseBody
    public ResponseEntity saveClient(@RequestBody Client client){
        Client clientSaved = clientService.saveClient(client);
        return ResponseEntity.ok(clientSaved);
    }
}
