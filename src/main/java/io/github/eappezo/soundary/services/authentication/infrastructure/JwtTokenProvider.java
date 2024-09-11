package io.github.eappezo.soundary.services.authentication.infrastructure;

import io.github.eappezo.soundary.core.identification.Identifier;
import io.github.eappezo.soundary.core.user.User;
import io.github.eappezo.soundary.services.authentication.application.TokenProvider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider implements TokenProvider {
    private final JwtProperties jwtProperties;
    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        secretKey = new SecretKeySpec(jwtProperties.getSecretKey().getBytes(), "HmacSHA512");
    }

    public String generateAccessTokenFrom(User user) {
        return Jwts.builder()
                .claims(buildClaim(user))
                .issuedAt(now())
                .expiration(new Date(getAccessTokenExpirationTime()))
                .signWith(secretKey)
                .compact();
    }

    public String generateRefreshTokenFrom(User user) {
        return Jwts.builder()
                .claims(buildClaim(user))
                .issuedAt(now())
                .expiration(new Date(getRefreshTokenExpirationTime()))
                .signWith(secretKey)
                .compact();
    }

    public Long getAccessTokenExpirationTime() {
        return System.currentTimeMillis() + jwtProperties.getAccessTokenExpiration();
    }

    private Long getRefreshTokenExpirationTime() {
        return System.currentTimeMillis() + jwtProperties.getRefreshTokenExpiration();
    }

    private Map<String, Object> buildClaim(User user) {
        return Map.of(
                "userId", user.getIdentifier().toString(),
                "roles", user.getRoles().stream().map(Enum::name).collect(Collectors.joining("::"))
        );
    }

    private Date now() {
        return new Date(System.currentTimeMillis());
    }
}
