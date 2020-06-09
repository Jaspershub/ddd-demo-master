package com.example.domaindriverdesign.order.order;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderPaymentProxy {
    public void pay(String orderId, BigDecimal paidPrice) {
        //call remote payment service
    }
}
