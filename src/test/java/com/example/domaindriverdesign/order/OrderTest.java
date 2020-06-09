package com.example.domaindriverdesign.order;

import com.example.domaindriverdesign.order.order.command.CreateOrderCommand;
import com.example.domaindriverdesign.order.order.command.OrderItemCommand;
import com.example.domaindriverdesign.order.order.model.Order;
import com.example.domaindriverdesign.order.order.model.OrderItem;
import com.example.domaindriverdesign.shared.model.Address;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.domaindriverdesign.order.order.model.OrderItem.create;
import static com.example.domaindriverdesign.order.order.model.OrderStatus.CREATED;
import static com.example.domaindriverdesign.shared.utils.UuidGenerator.newUuid;
import static com.google.common.collect.Lists.newArrayList;
import static java.math.BigDecimal.valueOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    @Test
    public void should_create_order() {
        Address address = Address.of("广东", "惠州", "惠城一路1号");

        OrderItem orderItem1 = create(newUuid(), 2, valueOf(20));
        OrderItem orderItem2 = create(newUuid(), 3, valueOf(30));
        Order order = Order.create(newUuid(), newArrayList(orderItem1, orderItem2), address);
        assertThat(valueOf(130), comparesEqualTo(order.getTotalPrice()));
        assertEquals(CREATED, order.getStatus());
        

    }
}
