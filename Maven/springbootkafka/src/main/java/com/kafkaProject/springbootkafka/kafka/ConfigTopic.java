package com.kafkaProject.springbootkafka.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class ConfigTopic {

    @Bean
    public NewTopic createTopic()
    {
        return TopicBuilder.name("java").build();
    }

    @Bean
    public NewTopic jsonTopic()
    {
        return TopicBuilder.name("Json_topic").build();
    }
}
