package com.system.bank_system.Security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
@RequiredArgsConstructor
public class JWTService {
    private final JwtProperties  jwtProperties;

    private SecretKey getSignInKey(){
        byte[] KeyBytes = Decoders.BASE64.decode(jwtProperties.getSecretKey());
        return Keys.hmacShaKeyFor(KeyBytes);
    }

    private Claims ExtractAllClaims(String token){
        return Jwts.parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        final Claims claims = ExtractAllClaims(token);
        return claimResolver.apply(claims);
    
    }

    @SuppressWarnings("null")
    public String extractEmail(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken( Map<String, Object> extractClaim, UserDetails userDetails ){
        return Jwts.builder()
                .claims(extractClaim)
                .subject(userDetails.getUsername())
                .issuedAt(new Date( System.currentTimeMillis() ))
                .expiration(new Date( System.currentTimeMillis() + 1000 * 60 * 24 ))
                .signWith(getSignInKey())
                .compact();
    }


    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid( String token, UserDetails userDetails ){
        final String email = extractEmail(token) ;
        return ( email.equals(userDetails.getUsername()) && !isTokenExpired(token) );
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    @SuppressWarnings("null")
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
}