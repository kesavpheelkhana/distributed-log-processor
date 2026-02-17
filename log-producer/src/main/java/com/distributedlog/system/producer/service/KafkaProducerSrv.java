package com.distributedlog.system.producer.service;

import com.distributedlog.system.model.LogEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KafkaProducerSrv {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerSrv.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${app.kafka.topic.log-events}")
    private String logEventsTopic;

    @Transactional(value="transactionManager")
    public void sendLogEvent(LogEvent logEvent) {
        try {
            String message = objectMapper.writeValueAsString(logEvent);

            // Use organizationId as partition key for ordering
            String partitionKey = logEvent.getOrganizationId();

            ListenableFuture<SendResult<String, String>> future =
                    kafkaTemplate.send(logEventsTopic, partitionKey, message);

            future.addCallback(
                    result -> {
                        logger.debug("Sent log event: {} to partition: {}",
                                logEvent.getId(), result.getRecordMetadata().partition());
                    },
                    ex -> {
                        logger.error("Failed to send log event: {}", logEvent.getId(), ex);
                        throw new RuntimeException("Failed to send log event", ex);
                    }
            );

        } catch (JsonProcessingException e) {
            logger.error("Failed to serialize log event: {}", logEvent.getId(), e);
            throw new RuntimeException("Failed to serialize log event", e);
        }
    }

}
