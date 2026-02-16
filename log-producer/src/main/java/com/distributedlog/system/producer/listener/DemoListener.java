package com.distributedlog.system.producer.listener;

import com.distributedlog.system.producer.entity.DemoEntity;
import com.distributedlog.system.producer.repository.DemoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class DemoListener {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final DemoRepository demoRepository;

    @KafkaListener(id = "group1", topics = "topic1")
    @Transactional(value = "transactionManager", rollbackFor = RuntimeException.class)
//    @Transactional("kafkaTransactionManager")
    public void listen1(String in) throws ClassNotFoundException {
        log.info("Received from topic1: {}", in);

        log.info("Sending to topic2: {}", in.toUpperCase());
        kafkaTemplate.send("topic2", in.toUpperCase());

        log.info("Writing to database: {}", in);
        if(in.equals("error")){
            throw new RuntimeException("got error message");
        }
        if(in.equals("warning")){
            throw new ClassNotFoundException("got warning message");
        }
        demoRepository.save(
                DemoEntity.builder()
                        .name(in)
                        .timestamp(System.currentTimeMillis())
                        .build()
        );
//        throw new RuntimeException("rolling back the transaction");
    }
}
