package com.ratz.service;

import com.ratz.entity.Client;

import java.util.Optional;

public interface ClientService {

    Optional<Client> findClientById(Integer id);
    Client saveClient(Client client);
}
