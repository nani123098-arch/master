package com.nani.spring_security.springsecurity.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    private static final String SECRET = "8928A007ABEA333B184218455744A2F0E5DA32497176F5D718CEF6AE965826FBAFE76E81B59B73449FDC7A47683D04AEB1A0CD3695C03435D01B397275891C7E5AA807A95E3D49ABD0750052B51BB441808372BC6AE7518328A6B8F1949B0FA4A6924A7B10C437D75BB6E9BB6E0214BBAC6A1804C4D90C469E75ACC03F697009";

//    Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }




    public String generateToken(String username)
    {
        Map<String, Object> claims= new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String username) {

        return Jwts.builder()
                .setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(key, SignatureAlgorithm.HS256).compact();



    }
//    private Key getSignKey() {
//        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }



//    private Key getSignKey() {
//        byte[] bytes = Decoders.BASE64.decode(key);
//        return Keys.hmacShaKeyFor(bytes);
//    }
}
