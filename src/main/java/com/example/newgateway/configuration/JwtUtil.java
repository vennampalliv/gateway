package com.example.newgateway.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("YOUR_SUPER_SECRET_KEY_THAT_IS_LONG_ENOUGH_FOR_HS256_ALGORITHM_SIGNING\"")
    private String secret;

    private Key key;

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims getAllClaimsFromToken(String token){

        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public boolean isExpired(String token){
        return this.getAllClaimsFromToken(token).getExpiration().before(new Date());
    }

    public boolean isValidToken(String token){

        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
           // throw new RuntimeException(e);
        }
    }

    private Key getSingingKey(){
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email){
         Date date = new Date();
        Instant updatedInstant = date.toInstant().plus(Duration.ofHours(24));
        Date newDate = Date.from(updatedInstant);
        return Jwts.builder().setSubject(email).setIssuedAt(date).
                setExpiration(Date.from(updatedInstant)).signWith(getSingingKey(), SignatureAlgorithm.HS256).compact();
    }


}
