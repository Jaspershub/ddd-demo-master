package com.example.domaindriverdesign.order.order.model;

import com.example.domaindriverdesign.shared.utils.UuidGenerator;
import org.springframework.stereotype.Component;

@Component
public class OrderIdGenerator {
    public String generate() {
        return UuidGenerator.newUuid();
    }
}
