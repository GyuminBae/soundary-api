package io.github.eappezo.soundary.services.authentication.application;

import io.github.eappezo.soundary.core.identification.Identifier;
import io.github.eappezo.soundary.services.authentication.domain.SocialPlatform;
import io.github.eappezo.soundary.services.authentication.domain.SocialAccount;

import java.util.Optional;

public interface SocialAccountRepository {

    Optional<Identifier> findUserIdBySocialInfo(SocialPlatform platform, String socialId);

    void save(SocialAccount account);

}
