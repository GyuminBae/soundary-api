package io.github.eappezo.soundary.services.authentication.application;

import io.github.eappezo.soundary.core.user.User;
import io.github.eappezo.soundary.services.authentication.domain.SocialPlatform;

public interface SocialUserCreationSupport {

    User createAndRegisterSocialUser(SocialPlatform platform, String socialId);

}
