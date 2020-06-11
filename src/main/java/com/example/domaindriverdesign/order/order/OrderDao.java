package com.example.domaindriverdesign.order.order;

import com.example.domaindriverdesign.order.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order, String> {

}
