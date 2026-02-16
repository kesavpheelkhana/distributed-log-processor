package com.distributedlog.system.producer.controller;

import com.distributedlog.system.model.LogEvent;
import com.distributedlog.system.producer.listener.DemoListener;
import com.distributedlog.system.producer.service.KafkaProducerSrv;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.micrometer.core.instrument.Counter;

import java.util.Map;

@RestController
@RequestMapping("/api/logs")
public class LogEventController {
    private static final Logger logger = LoggerFactory.getLogger(LogEventController.class);

    @Autowired
    private KafkaProducerSrv kafkaProducerService;

    private final Counter logCounter;

    @Autowired
    private DemoListener demoListener;

    public LogEventController(MeterRegistry meterRegistry) {
        this.logCounter = Counter.builder("log_events_received_total")
                .description("Total number of log events received")
                .register(meterRegistry);
    }

    @PostMapping
    @Timed(value = "log_event_processing_time", description = "Time taken to process log event")
    @CircuitBreaker(name = "kafka-producer", fallbackMethod = "fallbackLogEvent")
    public ResponseEntity<Map<String, String>> createLogEvent(@RequestBody LogEvent logEvent) {
        try {
            // Generate ID if not provided
            if (logEvent.getId() == null) {
                logEvent.setId(java.util.UUID.randomUUID().toString());
            }

            kafkaProducerService.sendLogEvent(logEvent);
            logCounter.increment();

            logger.info("Log event created successfully: {}", logEvent.getId());

            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "id", logEvent.getId(),
                    "message", "Log event queued for processing"
            ));

        } catch (Exception e) {
            logger.error("Failed to process log event", e);
            return ResponseEntity.internalServerError()
                    .body(Map.of("status", "error", "message", "Failed to process log event"));
        }
    }

    public ResponseEntity<Map<String, String>> fallbackLogEvent(LogEvent logEvent, Exception ex) {
        logger.warn("Circuit breaker activated for log event: {}", logEvent.getId(), ex);
        return ResponseEntity.status(503)
                .body(Map.of(
                        "status", "service_unavailable",
                        "message", "Log processing temporarily unavailable"
                ));
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "healthy"));
    }

    @PostMapping("/sendMsg")
    public ResponseEntity<String> sendMessage2Topic(@RequestParam String value) throws ClassNotFoundException {
        demoListener.listen1(value);
        return  new ResponseEntity<String>(HttpStatus.OK);
    }
}
