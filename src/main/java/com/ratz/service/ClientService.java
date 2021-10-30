package com.ratz.service;

import com.ratz.entity.Client;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    Optional<Client> findClientById(Integer id);
    Client saveClient(Client client);
    void deleteClientById(Integer id);
    List<Client> findAllClients(Example example);
}
