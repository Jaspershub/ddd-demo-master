package com.example.domaindriverdesign.shared;

import com.example.domaindriverdesign.shared.event.DomainEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DomainEventTest {

    @Test
    public void shouldCreateEvent(){
        DomainEvent domainEvent = new DomainEvent() {
        };

        assertNotNull(domainEvent);
    }
}
