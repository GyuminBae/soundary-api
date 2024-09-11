package io.github.eappezo.soundary.services.authentication.infrastructure;

import io.github.eappezo.soundary.core.identification.Identifier;
import io.github.eappezo.soundary.core.user.UserRole;
import io.github.eappezo.soundary.services.authentication.infrastructure.security.APIAuthentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationExtractor {
    private final JwtProperties jwtProperties;
    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        secretKey = new SecretKeySpec(jwtProperties.getSecretKey().getBytes(), "HmacSHA512");
    }

    public APIAuthentication extractAuthenticationFrom(String token) {
        Claims parser = Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        Identifier userId = Identifier.fromString(parser.get("userId").toString());
        List<UserRole> roles = Stream.of(parser.get("roles").toString().split("::"))
                .map(UserRole::valueOf)
                .toList();
        return APIAuthentication.of(userId, token, roles);
    }
}
