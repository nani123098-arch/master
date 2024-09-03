package com.nani.order_service.orderservice.controller;

import com.nani.basic_details.basicdetails.dto.Order;
import com.nani.basic_details.basicdetails.dto.OrderEvent;
import com.nani.order_service.orderservice.kafka.OrderProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class OrderController {

    private OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/order")
    public ResponseEntity<OrderEvent> sendOrderEvent(@RequestBody Order order)
    {
        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setOrderStatus("PENDING");
        orderEvent.setMessage("Sending the order please be available");
        orderEvent.setOrder(order);

        orderProducer.sendOrder(orderEvent);
        return new ResponseEntity<>(orderEvent, HttpStatus.CREATED);


    }
}
