package io.github.eappezo.soundary.services.authentication.infrastructure;

import io.github.eappezo.soundary.services.authentication.application.OAuthGateway;
import io.github.eappezo.soundary.services.authentication.domain.SocialPlatform;
import io.github.eappezo.soundary.services.authentication.application.OAuthResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoOAuthGateway implements OAuthGateway {
    @Override
    public OAuthResult authenticate(String token) {
        return new OAuthResult(SocialPlatform.KAKAO, "");
    }

    @Override
    public SocialPlatform getPlatform(){
        return SocialPlatform.KAKAO;
    }
}
