package com.ratz.service;

import com.ratz.dto.OrderDTO;
import com.ratz.entity.Order;

import java.util.Optional;

public interface OrderService {
    Order saveOrder(OrderDTO order);
    Optional<Order> getOrderDetails(Integer id);
}
