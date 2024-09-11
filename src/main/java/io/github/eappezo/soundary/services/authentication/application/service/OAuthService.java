package io.github.eappezo.soundary.services.authentication.application.service;

import io.github.eappezo.soundary.core.exception.common.UserNotFoundException;
import io.github.eappezo.soundary.core.user.User;
import io.github.eappezo.soundary.core.user.UserRepository;
import io.github.eappezo.soundary.services.authentication.application.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthService {
    private final TokenProvider tokenProvider;
    private final OAuthGatewayProvider oAuthGatewayProvider;
    private final SocialUserCreationSupport socialUserCreationSupport;
    private final SocialAccountRepository socialAccountRepository;
    private final UserRepository userRepository;

    public AuthenticatedUserDto login(AuthenticationRequestDto request) {
        OAuthGateway oauthGateway = oAuthGatewayProvider.getOAuthGateway(request.platform());
        OAuthResult result = oauthGateway.authenticate(request.token());
        User user = socialAccountRepository
                .findUserIdBySocialInfo(result.platform(), result.socialId())
                .map((it) -> userRepository.findById(it).orElseThrow(UserNotFoundException::new))
                .orElseGet(() -> socialUserCreationSupport.createAndRegisterSocialUser(
                        result.platform(),
                        result.socialId()
                ));
        return createAuthentication(user);
    }

    private AuthenticatedUserDto createAuthentication(User user) {
        String accessToken = tokenProvider.generateAccessTokenFrom(user);
        String refreshToken = tokenProvider.generateRefreshTokenFrom(user);
        Long expirationTime = tokenProvider.getAccessTokenExpirationTime();

        return new AuthenticatedUserDto(accessToken, refreshToken, expirationTime);
    }
}
