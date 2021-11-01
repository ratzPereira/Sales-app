package com.ratz.service;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderService service;

    public OrderServiceImpl(OrderService service) {
        this.service = service;
    }
}
