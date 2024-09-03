package com.nani.rest_api.restapi.repository;

import com.nani.rest_api.restapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
