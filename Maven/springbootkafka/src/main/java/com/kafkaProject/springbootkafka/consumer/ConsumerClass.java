package com.kafkaProject.springbootkafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerClass {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerClass.class);

    @KafkaListener(topics = "java", groupId = "myGroup")
    public void receiveMessage(String message)
    {

        LOGGER.info(String.format("Message received -> %s", message));
    }
}
