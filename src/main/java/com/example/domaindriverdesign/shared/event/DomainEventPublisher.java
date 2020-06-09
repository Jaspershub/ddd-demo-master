package com.example.domaindriverdesign.shared.event;

public interface DomainEventPublisher {
    void publishNextBatch();

    void publishNextBatch(int size);

    void forcePublish(String eventId);
}
