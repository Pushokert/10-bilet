package ru.rksp.processorservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rksp.processorservice.service.ClickHouseService;
import ru.rksp.processorservice.service.EventProcessorService;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Events API", description = "API для работы с событиями")
public class EventController {

    @Autowired
    private EventProcessorService eventProcessorService;

    @Autowired
    private ClickHouseService clickHouseService;

    @PostMapping("/events/count")
    @Operation(summary = "Подсчитать события", description = "Получает количество записей из PostgreSQL и записывает в ClickHouse")
    public ResponseEntity<String> countEvents() {
        try {
            long count = eventProcessorService.getEventCount();
            clickHouseService.saveEventCount(count);
            return ResponseEntity.ok("Event count: " + count + " saved to ClickHouse");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error counting events: " + e.getMessage());
        }
    }
}
