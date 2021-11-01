package com.ratz.controller;


import com.ratz.dto.OrderDTO;
import com.ratz.entity.Order;
import com.ratz.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private OrderService orderService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer saveOrder(@RequestBody OrderDTO order) {
        System.out.println(order);
        Order orderToSave = orderService.saveOrder(order);
        return orderToSave.getId();
    }
}
