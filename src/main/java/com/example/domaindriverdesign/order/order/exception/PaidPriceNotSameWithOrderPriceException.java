package com.example.domaindriverdesign.order.order.exception;

import com.example.domaindriverdesign.shared.exception.AppException;

import static com.example.domaindriverdesign.order.OrderErrorCode.PAID_PRICE_NOT_SAME_WITH_ORDER_PRICE;
import static com.google.common.collect.ImmutableMap.of;

public class PaidPriceNotSameWithOrderPriceException extends AppException {
    public PaidPriceNotSameWithOrderPriceException(String id) {
        super(PAID_PRICE_NOT_SAME_WITH_ORDER_PRICE, of("orderId", id));
    }
}
