package io.github.eappezo.soundary.services.authentication.infrastructure;

import io.github.eappezo.soundary.core.identification.Identifier;
import io.github.eappezo.soundary.services.authentication.application.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Map;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider implements TokenProvider {
    private final JwtProperties jwtProperties;

    public String generateAccessToken(Identifier userId) {
        return Jwts.builder()
                .claims(buildClaim(userId.toString()))
                .issuedAt(now())
                .expiration(new Date(getAccessTokenExpirationTime()))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.secretKey())
                .compact();
    }

    public String generateRefreshToken(Identifier userId){
        return Jwts.builder()
                .claims(buildClaim(userId.toString()))
                .issuedAt(now())
                .expiration(new Date(getRefreshTokenExpirationTime()))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.secretKey())
                .compact();
    }

    public Long getAccessTokenExpirationTime(){
        return System.currentTimeMillis() + jwtProperties.accessTokenExpire();
    }

    private Long getRefreshTokenExpirationTime(){
        return System.currentTimeMillis() + jwtProperties.refreshTokenExpire();
    }

    private Map<String, Object> buildClaim(String userId){
        return Map.of("userId", userId);
    }

    private Date now(){
        return new Date(System.currentTimeMillis());
    }
}
