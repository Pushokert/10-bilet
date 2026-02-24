package ru.rksp.ingestservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rksp.ingestservice.dto.EventDto;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Events API", description = "API для приёма событий платежей")
public class EventController {

    private static final String QUEUE_NAME = "events.raw";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/events")
    @Operation(summary = "Принять событие", description = "Принимает событие и отправляет его в RabbitMQ очередь events.raw")
    public ResponseEntity<String> receiveEvent(@RequestBody EventDto event) {
        try {
            rabbitTemplate.convertAndSend(QUEUE_NAME, event);
            return ResponseEntity.ok("Event sent to queue successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error sending event to queue: " + e.getMessage());
        }
    }
}
