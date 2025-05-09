package com.task.test.Jwt.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {       //Implement JWT Validation

    private static final String Secret_Key = "57111c700ee3cf6d6e2d51534d403a05a42a8309cccdc237a77ad0e14e32b37d";   //STEP 3.1

    public String extractUsername(String token) {           //STEP 1
        return extractClaim(token, Claims::getSubject);    //return the Subject(the Username)of the token using extractClaim which extracting one claim.
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {         //STEP 4    //Generic Method
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);                                              //return any claim not all claims.
    }
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role",userDetails.getAuthorities());
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))            //date of the created token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 + 24))      //1000 ms & 60 min & 24 hours
                .signWith(getSignInKey())
                .compact();                                     //to generate and return the token.

    }
    public Boolean isTokenValid(String token,UserDetails userDetails){  //STEP 6   //Check if token belongs to this userDetails.
        final String username = extractUsername(token);
        return (username == userDetails.getUsername()) && ! isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {          //STEP 6.1
        return extractExpiration(token).before(new Date());         //expiration Date before the date now.
    }

    private Date extractExpiration(String token) {          //STEP 6.2
        return extractClaim(token ,Claims::getExpiration);      //extract the expiration date of token.
    }

    private Claims extractAllClaims(String token) {              //STEP 2
        return Jwts
                .parserBuilder()                                //To validate or parse the JWT token
                .setSigningKey(getSignInKey())   //We should make secret key & To Decode or generate the token & While parsing the JWT token we need to pass Signing key to verify the JWT signature.
                .build()
                .parseClaimsJws(token)          //Parse the token
                .getBody();                 //then get all the claims we have in the token
    }

    private Key getSignInKey() {                    //STEP 3
        byte[] keyBytes = Decoders.BASE64.decode(Secret_Key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}