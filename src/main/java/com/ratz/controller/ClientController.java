package com.ratz.controller;

import com.ratz.entity.Client;
import com.ratz.service.ClientService;
import com.ratz.service.ClientServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
@Api("Api Clients")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    @ApiOperation("Get client details")
    @ApiResponses({@ApiResponse(code = 200,message = "Client found."), @ApiResponse(code = 404, message = "Client not found")})
    public Client getClientById(@PathVariable @ApiParam("Client id") Integer id) {

        return clientService.findClientById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Client with the id " + id + " not found.")
        );
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Save new Client")
    @ApiResponses({@ApiResponse(code = 201, message = "Client Saved."), @ApiResponse(code = 400, message = "Validation Error")})
    public Client saveClient(@RequestBody @Valid Client client) {
        return clientService.saveClient(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Integer id) {

        clientService.findClientById(id).map(client -> {
            clientService.deleteClientById(id);
            return client;
        }).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Client with the id " + id + " not found.")
        );

    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserById(@PathVariable Integer id, @RequestBody @Valid Client client) {

        clientService.findClientById(id).map(actualClient -> {
            client.setId(actualClient.getId());
            clientService.saveClient(client);
            return client;
        }).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Client with the id " + id + " not found.")
        );
    }

    @GetMapping("/find")
    public List<Client> findClients(Client filter) {

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, matcher);

        return clientService.findAllClients(example);
    }
}


