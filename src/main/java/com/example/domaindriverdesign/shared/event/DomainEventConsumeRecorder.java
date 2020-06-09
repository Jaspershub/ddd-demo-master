package com.example.domaindriverdesign.shared.event;

public interface DomainEventConsumeRecorder {

    boolean record(DomainEvent event);

    void deleteAll();
}
