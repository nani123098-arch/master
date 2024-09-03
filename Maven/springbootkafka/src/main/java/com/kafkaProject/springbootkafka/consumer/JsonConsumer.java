package com.kafkaProject.springbootkafka.consumer;

import com.kafkaProject.springbootkafka.Entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonConsumer.class);

    @KafkaListener(topics = "Json_topic", groupId = "myGroup")
    public void receiveUserJson(User user)
    {
        LOGGER.info(String.format("Json message is received %s", user.toString()));
    }
}
