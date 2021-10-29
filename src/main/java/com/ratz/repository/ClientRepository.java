package com.ratz.repository;


import com.ratz.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findByNameContains (String name);

    List<Client> findByNameOrId(String name, Integer id);

    boolean existsByName(String name);

    @Query("select c from Client c left join fetch c.orders p where c.id = :id")
    Client findClientFetchOrders(@Param("id") Integer id);
}
