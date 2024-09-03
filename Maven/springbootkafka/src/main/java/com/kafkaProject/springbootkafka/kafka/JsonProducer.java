package com.kafkaProject.springbootkafka.kafka;

import com.kafkaProject.springbootkafka.Entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonProducer.class);
    private KafkaTemplate<String, User> kafkaTemplate;

    public JsonProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendJson(User user)
    {
        LOGGER.info(String.format("User data send from producer -> %s", user));

        Message message = MessageBuilder.withPayload(user).setHeader(KafkaHeaders.TOPIC, "Json_topic").build();

        kafkaTemplate.send(message);
    }
}
