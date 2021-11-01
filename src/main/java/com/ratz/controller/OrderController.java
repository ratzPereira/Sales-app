package com.ratz.controller;


import com.ratz.service.ClientServiceImpl;
import com.ratz.service.OrderServiceImpl;
import com.ratz.service.ProductServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderServiceImpl orderService;
    private final ClientServiceImpl clientService;
    private final ProductServiceImpl productService;

    public OrderController(OrderServiceImpl orderService, ClientServiceImpl clientService, ProductServiceImpl productService) {
        this.orderService = orderService;
        this.clientService = clientService;
        this.productService = productService;
    }
}
