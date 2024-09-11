package io.github.eappezo.soundary.services.authentication.infrastructure.persistence;

import io.github.eappezo.soundary.services.authentication.domain.SocialPlatform;

import java.io.Serializable;

public record SocialAccountEntityKey(
    SocialPlatform platform = SocialPlatform.KAKAO,
    String socialId = ""
) implements Serializable {

}