package com.example.domaindriverdesign.order.order.exception;

import com.example.domaindriverdesign.shared.exception.AppException;

import static com.example.domaindriverdesign.order.OrderErrorCode.ORDER_CANNOT_BE_MODIFIED;
import static com.google.common.collect.ImmutableMap.of;

public class OrderCannotBeModifiedException extends AppException {
    public OrderCannotBeModifiedException(String id) {
        super(ORDER_CANNOT_BE_MODIFIED, of("orderId", id));
    }
}
