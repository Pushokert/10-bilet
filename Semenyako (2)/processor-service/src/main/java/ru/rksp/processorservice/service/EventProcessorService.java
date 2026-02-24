package ru.rksp.processorservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rksp.processorservice.entity.RawPaymentEvent;
import ru.rksp.processorservice.repository.RawPaymentEventRepository;

@Service
public class EventProcessorService {

    @Autowired
    private RawPaymentEventRepository repository;

    @Transactional
    public void saveEvent(RawPaymentEvent event) {
        repository.save(event);
    }

    public long getEventCount() {
        return repository.count();
    }
}
