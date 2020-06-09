package com.example.domaindriverdesign.order.order.model;

import com.example.domaindriverdesign.order.order.exception.OrderCannotBeModifiedException;
import com.example.domaindriverdesign.order.order.exception.PaidPriceNotSameWithOrderPriceException;
import com.example.domaindriverdesign.order.order.exception.ProductNotInOrderException;
import com.example.domaindriverdesign.order.order.representation.OrderItemRepresentation;
import com.example.domaindriverdesign.order.order.representation.OrderRepresentation;
import com.example.domaindriverdesign.order.order.representation.OrderSummaryRepresentation;
import com.example.domaindriverdesign.shared.model.Address;
import com.example.domaindriverdesign.shared.model.BaseAggregate;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.domaindriverdesign.order.order.model.OrderStatus.CREATED;
import static com.example.domaindriverdesign.order.order.model.OrderStatus.PAID;
import static java.math.BigDecimal.ZERO;
import static java.time.Instant.now;

@Getter
@Builder
public class Order extends BaseAggregate {
    private String id;
    private List<OrderItem> items;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private Address address;
    private Instant createdAt;

    public static Order create(String id, List<OrderItem> items, Address address){
        Order order = Order.builder()
                .id(id)
                .items(items)
                .totalPrice(calculateTotalPrice(items))
                .status(CREATED)
                .address(address)
                .createdAt(now())
                .build();
        return order;
    }

    private static BigDecimal calculateTotalPrice(List<OrderItem> items) {
        return items.stream()
                .map(OrderItem::totalPrice)
                .reduce(ZERO, BigDecimal::add);
    }

    public void changeProductCount(String productId, int count) {
        if (this.status == PAID) {
            throw new OrderCannotBeModifiedException(this.id);
        }

        OrderItem orderItem = retrieveItem(productId);
        int originalCount = orderItem.getCount();
        orderItem.updateCount(count);
        this.totalPrice = calculateTotalPrice(items);
    }

    private OrderItem retrieveItem(String productId) {
        return items.stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ProductNotInOrderException(productId, id));
    }

    public void pay(BigDecimal paidPrice) {
        if (!this.totalPrice.equals(paidPrice)) {
            throw new PaidPriceNotSameWithOrderPriceException(id);
        }
        this.status = PAID;
    }

    public void changeAddressDetail(String detail) {
        if (this.status == PAID) {
            throw new OrderCannotBeModifiedException(this.id);
        }

        this.address = this.address.changeDetailTo(detail);
    }

    public OrderRepresentation toRepresentation() {
        List<OrderItemRepresentation> itemRepresentations =this.getItems().stream()
                .map(orderItem -> new OrderItemRepresentation(orderItem.getProductId().toString(),
                        orderItem.getCount(),
                        orderItem.getItemPrice()))
                .collect(Collectors.toList());

        return new OrderRepresentation(this.getId().toString(),
                itemRepresentations,
                this.getTotalPrice(),
                this.getStatus().name(),
                this.getAddress(),
                this.getCreatedAt());
    }

    public OrderSummaryRepresentation toSummary() {
        return new OrderSummaryRepresentation(this.getId(),
                this.getTotalPrice(),
                this.getStatus().name(),
                this.getCreatedAt(),
                this.getAddress());
    }
}
