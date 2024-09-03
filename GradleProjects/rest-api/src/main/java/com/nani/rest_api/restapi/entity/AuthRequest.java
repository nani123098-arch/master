package com.nani.rest_api.restapi.entity;

import lombok.Data;

@Data
public class AuthRequest {

    private String username;
    private String password;
}
