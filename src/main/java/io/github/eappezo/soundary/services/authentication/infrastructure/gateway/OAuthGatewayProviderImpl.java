package io.github.eappezo.soundary.services.authentication.infrastructure.gateway;

import io.github.eappezo.soundary.services.authentication.application.OAuthGateway;
import io.github.eappezo.soundary.services.authentication.application.OAuthGatewayProvider;
import io.github.eappezo.soundary.services.authentication.domain.SocialPlatform;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthGatewayProviderImpl implements OAuthGatewayProvider {
    private final ObjectProvider<OAuthGateway> oAuthGateways;

    @Override
    public OAuthGateway getOAuthGateway(SocialPlatform platform) {
        return oAuthGateways.stream()
                .filter(gateway -> gateway.getPlatform() == platform)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not supported platform"));
    }
}
