package io.github.eappezo.soundary.services.authentication.application;

public record AuthenticatedUserDto(
        String accessToken,
        String refreshToken,
        Long expiresIn
) {
}
