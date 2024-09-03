package com.nani.rest_api.restapi.controller;

import com.nani.rest_api.restapi.entity.AuthRequest;
import com.nani.rest_api.restapi.entity.Product;
import com.nani.rest_api.restapi.entity.User;
import com.nani.rest_api.restapi.service.JwtService;
import com.nani.rest_api.restapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("save")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product)
    {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @GetMapping("findAll")
    public ResponseEntity<List<Product>> findAllProducts()
    {
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.FOUND);
    }

    @PutMapping("updateProduct")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws Exception {
        return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.OK);
    }

    @GetMapping("findProduct/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Product> getProduct(@PathVariable Long id)
    {
        return new ResponseEntity<>(productService.getById(id), HttpStatus.FOUND);
    }

    @PostMapping("saveUser")
    public ResponseEntity<User> saveUser(@RequestBody User user)
    {
        user = productService.saveUser(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }



    @PostMapping("auth")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws Exception {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));

        if(authentication.isAuthenticated())
        {
            return jwtService.generateToken(authRequest.getUsername());
        }
        else {
            throw  new Exception("Invalid User credentials");
        }

    }


}
