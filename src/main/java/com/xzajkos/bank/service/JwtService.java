package com.xzajkos.bank.Service;

import com.xzajkos.bank.Dto.UserDto;
import com.xzajkos.bank.Model.AuthenticationResponse;
import com.xzajkos.bank.Model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private String secretKey = "n+Godm0pB3LtjG9R87aUpMgZ6VgoKgSz85ZpyDXiNuOre1WwRnn0rTb3pzobGc98";

    private SecretKey signingKey(){
        byte[] keyBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(User user) {
        return Jwts
                .builder()
                .signWith(signingKey())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 *60 * 24))
                .subject(user.getUsername())
                .compact();
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .verifyWith(signingKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private boolean isExpired(String token){
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public boolean validateToken(String token, User user){
        String username = extractClaim(token, Claims::getSubject);
        return user.getUsername().equals(username);
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
}

