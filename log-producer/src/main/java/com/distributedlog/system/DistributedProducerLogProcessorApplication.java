package com.distributedlog.system;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;

@Slf4j
@SpringBootApplication
public class DistributedProducerLogProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedProducerLogProcessorApplication.class, args);
	}

    @Bean("transactionManager")
    JpaTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public ApplicationRunner transactionManagersId(
            PlatformTransactionManager transactionManager,
            KafkaTransactionManager<String, String> kafkaTransactionManager
    ) {
        return args -> {
            log.info("Transaction manager: {}", transactionManager.getClass().getName());
            log.info("Kafka transaction manager: {}", kafkaTransactionManager.getClass().getName());
        };
    }

    @Bean
    public ApplicationRunner sendKafkaMessage(KafkaTemplate<String, String> template) {
        return args -> template.executeInTransaction(t -> t.send("topic1", "test"));
    }

    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name("topic1").build();
    }

    @Bean
    public NewTopic topic2() {
        return TopicBuilder.name("topic2").build();
    }

}
