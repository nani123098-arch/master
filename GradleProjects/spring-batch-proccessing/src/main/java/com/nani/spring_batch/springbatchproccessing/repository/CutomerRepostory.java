package com.nani.spring_batch.springbatchproccessing.repository;

import com.nani.spring_batch.springbatchproccessing.entity.Customer;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CutomerRepostory extends JpaRepository<Customer, Integer> {
}
