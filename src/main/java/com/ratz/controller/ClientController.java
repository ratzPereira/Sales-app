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

        if (!client.isPresent()) {
            System.out.println("Not Found");
            return ResponseEntity.notFound().build();

        }
        System.out.println("Found");
        return ResponseEntity.ok(client);
    }

    @PostMapping()
    @ResponseBody
    public ResponseEntity saveClient(@RequestBody Client client) {
        Client clientSaved = clientService.saveClient(client);
        return ResponseEntity.ok(clientSaved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable Integer id) {

        Optional<Client> clientToDelete = clientService.findClientById(id);

        if (clientToDelete.isPresent()) {
            clientService.deleteClientById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    public ResponseEntity updateUserById(@PathVariable Integer id, @RequestBody Client client) {

        return clientService.findClientById(id).map(actualClient -> {
            client.setId(actualClient.getId());
            clientService.saveClient(client);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}


