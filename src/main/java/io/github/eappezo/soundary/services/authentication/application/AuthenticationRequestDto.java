package io.github.eappezo.soundary.services.authentication.application;

import io.github.eappezo.soundary.services.authentication.domain.SocialPlatform;

public record AuthenticationRequestDto(
        SocialPlatform platform,
        String token
) {
}
