package com.ratz.security;

import com.ratz.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.expire}")
    private String expirationTime;

    @Value("${security.jwt.secret-key}")
    private String appKey;

    public String tokenGeneration(User user) {

        long expString = Long.valueOf(expirationTime);
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(expString);

        Instant instant = expirationTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        return Jwts.builder()
                .setSubject(user.getUserName())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, appKey)
                .compact();
    }

    private Claims getClaims (String token) throws ExpiredJwtException {
        return Jwts.parser()
                .setSigningKey(appKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = getClaims(token);
            Date expirationDate = claims.getExpiration();
            LocalDateTime date = expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            return !LocalDateTime.now().isAfter(date);

        }catch (Exception e){
            return false;
        }
    }

    public String getUserUserName(String token) throws ExpiredJwtException {
        return getClaims(token).getSubject();
    }
}
