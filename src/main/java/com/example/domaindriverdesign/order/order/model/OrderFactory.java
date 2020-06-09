package com.example.domaindriverdesign.order.order.model;

import com.example.domaindriverdesign.shared.model.Address;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderFactory {
    private final OrderIdGenerator idGenerator;

    public OrderFactory(OrderIdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Order create(List<OrderItem> items, Address address) {
        String orderId = idGenerator.generate();
        return Order.create(orderId, items, address);
    }
}
