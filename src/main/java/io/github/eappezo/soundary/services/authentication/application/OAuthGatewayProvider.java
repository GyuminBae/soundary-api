package io.github.eappezo.soundary.services.authentication.application;

import io.github.eappezo.soundary.services.authentication.domain.SocialPlatform;

public interface OAuthGatewayProvider {

    OAuthGateway getOAuthGateway(SocialPlatform platform);

}
