package com.distributedlog.system.consumer.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Topic2DemoListener {
    private final KafkaTemplate<String, String> kafkaTemplate;


    @KafkaListener(id = "group1", topics = "topic2")
    public void listen2(String in) {
        log.info("Received from topic2: {}", in);
    }

}
