package com.nani.spring_security.springsecurity.mapper;

import com.nani.spring_security.springsecurity.entity.AuthRequest;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnRowMapper implements RowMapper {
    @Override
    public AuthRequest mapRow(ResultSet rs, int rowNum) throws SQLException {

        AuthRequest authRequest = new AuthRequest();
       authRequest.setUsername(rs.getString(1));
       authRequest.setUsername(rs.getString(2));
        return authRequest;
    }
}
