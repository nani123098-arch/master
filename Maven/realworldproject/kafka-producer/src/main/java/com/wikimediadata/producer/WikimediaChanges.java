package com.wikimediadata.producer;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

public class WikimediaChanges implements EventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChanges.class);
    private KafkaTemplate<String, String> kafkaTemplate;
    private String topic;

    public WikimediaChanges(KafkaTemplate<String, String> kafkaTemplate, String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {

        LOGGER.info(String.format("data sent from consumer -> %s", messageEvent.getData()));
        kafkaTemplate.send(topic, messageEvent.getData());


    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
