package ru.rksp.processorservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rksp.processorservice.entity.RawPaymentEvent;

@Repository
public interface RawPaymentEventRepository extends JpaRepository<RawPaymentEvent, String> {
    long count();
}
