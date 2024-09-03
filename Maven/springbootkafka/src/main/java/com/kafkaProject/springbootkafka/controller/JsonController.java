package com.kafkaProject.springbootkafka.controller;
import com.kafkaProject.springbootkafka.Entity.User;
import com.kafkaProject.springbootkafka.kafka.JsonProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class JsonController {

   private JsonProducer jsonProducer;

    public JsonController(JsonProducer jsonProducer) {
        this.jsonProducer = jsonProducer;
    }

    @PostMapping
    public ResponseEntity<User> sendUser(@RequestBody User user)
    {
       jsonProducer.sendJson(user);
       return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
