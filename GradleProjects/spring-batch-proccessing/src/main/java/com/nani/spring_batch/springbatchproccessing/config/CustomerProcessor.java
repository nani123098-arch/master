package com.nani.spring_batch.springbatchproccessing.config;

import com.nani.spring_batch.springbatchproccessing.entity.Customer;
import org.springframework.batch.item.ItemProcessor;

public class CustomerProcessor implements ItemProcessor<Customer,Customer> {
    @Override
    public Customer process(Customer item) throws Exception {
        return item;
    }
}
