package io.github.eappezo.soundary.services.authentication.api.dto;

import io.github.eappezo.soundary.services.authentication.application.AuthenticationRequestDto;
import io.github.eappezo.soundary.services.authentication.domain.SocialPlatform;

public record AuthentificationRequest(
        SocialPlatform platform,
        String token
) {
    public AuthenticationRequestDto to(){
        return new AuthenticationRequestDto(platform, token);
    }
}
