package com.wikimediadata.configtopic;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicCreartion {

    public NewTopic wikimedia()
    {
        return TopicBuilder.name("wikimedia").build();
    }

}
