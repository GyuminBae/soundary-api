package io.github.eappezo.soundary.services.authentication.application;

import io.github.eappezo.soundary.services.authentication.domain.SocialPlatform;

public record OAuthResult(
        SocialPlatform platform,
        String socialId
) {
}
