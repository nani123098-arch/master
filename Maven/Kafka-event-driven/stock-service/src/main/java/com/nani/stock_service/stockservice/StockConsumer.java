package com.nani.stock_service.stockservice;

import com.nani.basic_details.basicdetails.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StockConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockConsumer.class);

//    @Value("${spring.kafka.topic}")
//    private String topic;
//    @Value("${spring.kafka.consumer.group-id}")
//    private  String group;

    @KafkaListener(topics = "${spring.kafka.topic}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void emailConsumer(OrderEvent orderEvent)
    {


        LOGGER.info(String.format("Order Event is received -> %s", orderEvent.toString()));



    }
}
