package com.example.domaindriverdesign.shared.event;

public interface DomainEventSender {
    void send(DomainEvent event);
}
