package com.nani.rest_api.restapi.service;

import com.nani.rest_api.restapi.entity.Product;
import com.nani.rest_api.restapi.entity.User;
import com.nani.rest_api.restapi.repository.ProductRepository;
import com.nani.rest_api.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Product save(Product product)
    {
       return productRepository.save(product);
    }

    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    public Product getById(Long id)
    {
        return  productRepository.findById(id).get();
    }

    public User saveUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       return userRepository.save(user);
    }

    public Product updateProduct(Product product) throws Exception {
     Product  product2 = productRepository.findById(product.getId()).orElseThrow(()->new Exception("User Not found"));

    if(product.getProductName()!=null&&product.getProductPrice()!=0&&product.getProductDescription()!=null)
    {
        product2.setId(product.getId());
        product2.setProductName(product.getProductName());
        product2.setProductDescription(product.getProductDescription());
        product2.setProductPrice(product.getProductPrice());
    }
    else {
        throw new RuntimeException("Please provide all the fields not null");
    }

    return productRepository.save(product2);



    }

}
