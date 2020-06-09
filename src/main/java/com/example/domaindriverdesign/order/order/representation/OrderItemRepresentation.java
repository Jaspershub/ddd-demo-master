package com.example.domaindriverdesign.order.order.representation;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class OrderItemRepresentation {
    private final String productId;
    private final int count;
    private final BigDecimal itemPrice;
}
