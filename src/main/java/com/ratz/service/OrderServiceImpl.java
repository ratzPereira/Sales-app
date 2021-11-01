package com.ratz.service;

import com.ratz.dto.ItemOrderedDTO;
import com.ratz.dto.OrderDTO;
import com.ratz.entity.Client;
import com.ratz.entity.ItemOrdered;
import com.ratz.entity.Order;
import com.ratz.entity.Product;
import com.ratz.exception.CustomException;
import com.ratz.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ItemOrderedService itemOrderedService;



    @Override
    @Transactional
    public Order saveOrder(OrderDTO order) {

        Integer clientId = order.getClient();

        Client client = clientService.findClientById(clientId).orElseThrow(() -> new CustomException("Invalid client ID."));


        Order orderToSave = new Order();

        orderToSave.setTotal(order.getTotal());
        orderToSave.setDataOrder(LocalDate.now());
        orderToSave.setClient(client);

        System.out.println(order.getItemList());

        List<ItemOrdered> orderItems = convertItems(orderToSave, order.getItemList());
        System.out.println("order to save is " + orderToSave);
        repository.save(orderToSave);
        itemOrderedService.saveAllItemOrdered(orderItems);
        orderToSave.setItems(orderItems);

        return orderToSave;
    }


    @Override
    public Optional<Order> getOrderDetails(Integer id) {
      return repository.findByIdFetchItems(id);
    }



    private List<ItemOrdered> convertItems(Order order, List<ItemOrderedDTO> items) {
        if (items.isEmpty()) {
            System.out.println("Esta aqui?");
            throw new CustomException("The items list can't be empty");
        }

        return items.stream().map(dto -> {

            Integer productId = dto.getProduct();
            Product product = productService.findProductById(productId).orElseThrow(() -> new CustomException("Item with id " + productId + " does not exist."));

            ItemOrdered itemOrdered = new ItemOrdered();
            itemOrdered.setQuantity(dto.getQuantity());
            itemOrdered.setOrders(order);
            itemOrdered.setProduct(product);
            return itemOrdered;
        }).collect(Collectors.toList());
    }
}
