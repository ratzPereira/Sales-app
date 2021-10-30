package com.ratz.service;

import com.ratz.entity.Client;
import com.ratz.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Optional<Client> findClientById(Integer id) {
        return this.clientRepository.findById(id);
    }

    @Override
    public Client saveClient(Client client) {
        return this.clientRepository.save(client);
    }

    public void deleteClientById(Integer id) {
        this.clientRepository.deleteById(id);
    }

    @Override
    public List<Client> findAllClients(Example example) {
        return clientRepository.findAll(example);
    }


}
