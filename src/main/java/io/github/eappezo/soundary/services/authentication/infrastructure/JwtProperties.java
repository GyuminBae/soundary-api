package io.github.eappezo.soundary.services.authentication.infrastructure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "token")
public record JwtProperties(
        String secretKey,
        Long accessTokenExpire,
        Long refreshTokenExpire
) {
}
