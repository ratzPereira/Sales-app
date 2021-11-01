package com.ratz.service;

import com.ratz.dto.OrderDTO;
import com.ratz.entity.Order;

public interface OrderService {
    Order saveOrder(OrderDTO order);
}
