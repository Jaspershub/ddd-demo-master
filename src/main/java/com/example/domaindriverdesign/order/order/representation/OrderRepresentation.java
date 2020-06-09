package com.example.domaindriverdesign.order.order.representation;

import com.example.domaindriverdesign.order.order.model.OrderStatus;
import com.example.domaindriverdesign.shared.model.Address;
import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Value
public class OrderRepresentation {
    private String id;
    private List<OrderItemRepresentation> items;
    private BigDecimal totalPrice;
    private String status;
    private Address address;
    private Instant createdAt;
}
