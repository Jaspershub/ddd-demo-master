package com.example.domaindriverdesign.order.order.exception;

import com.example.domaindriverdesign.shared.exception.AppException;

import static com.example.domaindriverdesign.order.OrderErrorCode.ORDER_NOT_FOUND;
import static com.google.common.collect.ImmutableMap.of;

public class OrderNotFoundException extends AppException {
    public OrderNotFoundException(String orderId) {
        super(ORDER_NOT_FOUND, of("orderId", orderId));
    }
}
