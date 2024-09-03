package com.kafkaProject.springbootkafka.controller;


import com.kafkaProject.springbootkafka.Entity.User;
import com.kafkaProject.springbootkafka.kafka.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kafka")
public class HomeController {

    private KafkaProducer kafkaProducer;

    public HomeController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    //http://localhost:8080/kafka/publish?message=hello world
    @GetMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam("message") String message)
    {

        kafkaProducer.sendMessage(message);

        return ResponseEntity.ok("message successfully sent");
    }


}
