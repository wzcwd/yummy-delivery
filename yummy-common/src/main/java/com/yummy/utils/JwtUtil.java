package com.yummy.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    /**
     * generate jwt
     * use Hs256 Encryption Algorithm, private key is static
     *
     * @param secretKey jwt key
     * @param ttlMillis jwt valid time
     * @param claims    claim settings
     * @return
     */
    public static String createJWT(String secretKey, long ttlMillis, Map<String, Object> claims) {
        // assign Encryption Algorithm
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // calculate the expiration time = currentTime+ ttl(validTime )
        long expMillis = System.currentTimeMillis() + ttlMillis;
        Date exp = new Date(expMillis);

        // build the jwt using Jwts.builder()
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                // .getBytes Convert the secretKey string into a byte array
                // because the signature algorithm requires byte data, not a string
                .signWith(signatureAlgorithm, secretKey.getBytes(StandardCharsets.UTF_8))
                // set expiration time
                .setExpiration(exp);

        return builder.compact(); // use compact() to generate the final jwt, it returns a String
    }

    /**
     * Token Decryption
     *
     * @param secretKey The JWT secret key must be kept secure on the server and should not be exposed
     *                  If integrating with multiple clients, it is recommended to create multiple secret keys
     * @param token    token get from front-end
     * @return claims
     */
    public static Claims parseJWT(String secretKey, String token) {
        // get DefaultJwtParser
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                // the token that needs to be decrypted
                .parseClaimsJws(token).getBody();
        return claims;
    }

}
