package io.github.eappezo.soundary.services.authentication.api.dto;

import io.github.eappezo.soundary.services.authentication.application.AuthenticatedUserDto;

public record AuthenticationResponse(
        String accessToken,
        String refreshToken,
        Long expiresIn
) {
    public static AuthenticationResponse from(AuthenticatedUserDto userDto) {
        return new AuthenticationResponse(userDto.accessToken(), userDto.refreshToken(), userDto.expiresIn());
    }
}