package com.recruiter.recruiter.jwt;

import com.recruiter.recruiter.exception.JwtExeption;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.io.Decoders;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

//provide utils function for generate, verify ... token
@Component
public class JwtProvider {

    @Value("${jwt.secret}") //get info from application.property
    private String seceretKey ;

    @Value("${jwt.timeExpired}")
    private Long timeExpired ;

    public JwtProvider() {
    }

    //generate token
    //when validate success spring will receive an authentication have information about user
    public String generateToken(Authentication authentication) {

        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + timeExpired * 1000) ;

        String token = Jwts.builder()
                .setSubject(username) // setpayload
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(key())
                .compact();

        return token;


        }
    // Method to generate a new secret key
    private SecretKey generateSecretKey() {
        // Specify the desired SignatureAlgorithm (e.g., HS256, HS384, or HS512)
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // Generate a new secure key
        return Keys.secretKeyFor(signatureAlgorithm);
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(seceretKey));
    }

    public String getTokenFromRequestHeader(HttpServletRequest request) {
        String headerBearerRequest = request.getHeader("Authorization");
        if (headerBearerRequest != null && headerBearerRequest.startsWith("Bearer ")) {
            return headerBearerRequest.substring(7);
        }
        return null;
    }

    public Boolean verifyToken(String tokenNeedVerify) throws JwtExeption {
        if (tokenNeedVerify == null) {
            return false;
        }
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(tokenNeedVerify);
            return true;
        } catch (MalformedJwtException ex) {
            throw new JwtExeption("InvalidJwt");
        } catch (ExpiredJwtException ex) {
            throw new JwtExeption("Expired Jwt token");
        } catch (UnsupportedJwtException ex) {
            throw new JwtExeption("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new JwtExeption("JWT claims string is empty");
        } catch (Exception ex) {
            // throw new TokenException("loi chua dinh nghia");
            throw new JwtExeption("Other error");

        }


    }
    public String getPayloadfromtoken(String tokenValidated) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(tokenValidated)
                .getBody();
        return claims.getSubject();
    }

}
