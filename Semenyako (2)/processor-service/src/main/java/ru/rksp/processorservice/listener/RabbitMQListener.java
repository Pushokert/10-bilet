package ru.rksp.processorservice.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rksp.processorservice.dto.EventDto;
import ru.rksp.processorservice.entity.RawPaymentEvent;
import ru.rksp.processorservice.service.EventProcessorService;

@Component
public class RabbitMQListener {

    @Autowired
    private EventProcessorService eventProcessorService;

    @RabbitListener(queues = "events.raw")
    public void receiveMessage(EventDto eventDto) {
        try {
            RawPaymentEvent event = new RawPaymentEvent(
                eventDto.getIdentifier(),
                eventDto.getPayerFullName(),
                eventDto.getAmount(),
                eventDto.getCurrency(),
                eventDto.getPaymentMethod(),
                eventDto.getEventDate()
            );

            eventProcessorService.saveEvent(event);
            System.out.println("Event saved to PostgreSQL: " + event.getIdentifier());

        } catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
