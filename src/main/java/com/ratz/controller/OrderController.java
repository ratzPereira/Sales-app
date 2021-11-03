package com.ratz.controller;


import com.ratz.dto.ItemOrderedDetailsDTO;
import com.ratz.dto.OrderDTO;
import com.ratz.dto.OrderDetailsDTO;
import com.ratz.dto.OrderStatusChangeDTO;
import com.ratz.entity.ItemOrdered;
import com.ratz.entity.Order;
import com.ratz.enums.OrderStatus;
import com.ratz.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/{id}")
    public OrderDetailsDTO findOrderById(@PathVariable Integer id) {
        return orderService.getOrderDetails(id).map(p -> convert(p)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private OrderDetailsDTO convert(Order order) {
        return OrderDetailsDTO.builder()
                .id(order.getId())
                .clientName(order.getClient().getName())
                .total(order.getTotal())
                .status(order.getStatus().name())
                .orderDate(order.getDataOrder().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .items(converter(order.getItems())).build();
    }

    private List<ItemOrderedDetailsDTO> converter(List<ItemOrdered> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }

        return items.stream().map(item -> ItemOrderedDetailsDTO.builder().
                description(item.getProduct().getDescription())
                .unitPrice(item.getProduct().getUnitPrice())
                .quantity(item.getQuantity()).build()
        ).collect(Collectors.toList());
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@RequestBody OrderStatusChangeDTO dto,
                             @PathVariable Integer id) {

        String newStatus = dto.getNewStatus();
        orderService.updateOrderStatus(id, OrderStatus.valueOf(newStatus));
    }

}
