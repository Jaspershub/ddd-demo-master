package com.example.domaindriverdesign.order.order;

import com.example.domaindriverdesign.order.order.command.ChangeProductCountCommand;
import com.example.domaindriverdesign.order.order.command.CreateOrderCommand;
import com.example.domaindriverdesign.order.order.command.PayOrderCommand;
import com.example.domaindriverdesign.order.order.model.Order;
import com.example.domaindriverdesign.order.order.model.OrderFactory;
import com.example.domaindriverdesign.order.order.model.OrderItem;
import com.example.domaindriverdesign.shared.utils.Page4Navigator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@CacheConfig(cacheNames="orders")
public class OrderApplicationService {
    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;
    @Autowired
    private final OrderPaymentService orderPaymentService;
    @Autowired
    private final OrderDao orderDao;

    public OrderApplicationService(OrderRepository orderRepository,
                                   OrderFactory orderFactory,
                                   OrderPaymentService orderPaymentService,
                                   OrderDao orderDao) {
        this.orderRepository = orderRepository;
        this.orderFactory = orderFactory;
        this.orderPaymentService = orderPaymentService;
        this.orderDao = orderDao;
    }

    @Transactional
    @CacheEvict(allEntries=true)
    public String createOrder(CreateOrderCommand command) {
        List<OrderItem> items = command.getItems().stream()
                .map(item -> OrderItem.create(item.getProductId(),
                        item.getCount(),
                        item.getItemPrice()))
                .collect(Collectors.toList());

        Order order = orderFactory.create(items, command.getAddress());
        orderRepository.save(order);
        log.info("Created order[{}].", order.getId());
        return order.getId();
    }

    @Transactional
    @CacheEvict(allEntries=true)
    public void changeProductCount(String id, ChangeProductCountCommand command) {
        Order order = orderRepository.byId(id);
        order.changeProductCount(command.getProductId(), command.getCount());
        orderRepository.save(order);
    }

    @Transactional
    @CacheEvict(allEntries=true)
    public void pay(String id, PayOrderCommand command) {
        Order order = orderRepository.byId(id);
        orderPaymentService.pay(order, command.getPaidPrice());
        orderRepository.save(order);
    }

    @Transactional
    @CacheEvict(allEntries=true)
    public void changeAddressDetail(String id, String detail) {
        Order order = orderRepository.byId(id);
        order.changeAddressDetail(detail);
        orderRepository.save(order);
    }

//    @Override
    @Cacheable(key="'category '+#p0.offset + '-' + #p0.pageSize ")
    public Page4Navigator<Order> list(Pageable pageable) {
        Page<Order> pageFromJPA=  orderDao.findAll(pageable);
        Page4Navigator<Order> page = new Page4Navigator<>(pageFromJPA,5);
        return page;
    }
}
