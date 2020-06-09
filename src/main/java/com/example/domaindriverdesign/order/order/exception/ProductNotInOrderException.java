package com.example.domaindriverdesign.order.order.exception;

import com.example.domaindriverdesign.shared.exception.AppException;

import static com.example.domaindriverdesign.order.OrderErrorCode.PRODUCT_NOT_IN_ORDER;
import static com.google.common.collect.ImmutableMap.of;

public class ProductNotInOrderException extends AppException {
    public ProductNotInOrderException(String productId, String orderId) {
        super(PRODUCT_NOT_IN_ORDER, of("productId", productId,
                "orderId", orderId));
    }
}
