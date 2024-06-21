package com.naniguides.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private Long fieldId;


    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldId) {

        super(String.format("%s is not found with %s: '%s'",resourceName,fieldName,fieldId));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldId = fieldId;
    }
}
