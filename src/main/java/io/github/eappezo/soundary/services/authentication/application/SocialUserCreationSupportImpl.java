package io.github.eappezo.soundary.services.authentication.application;

import io.github.eappezo.soundary.core.identification.Identifier;
import io.github.eappezo.soundary.core.identification.IdentifierGenerator;
import io.github.eappezo.soundary.core.user.User;
import io.github.eappezo.soundary.core.user.UserRepository;
import io.github.eappezo.soundary.services.authentication.domain.SocialAccount;
import io.github.eappezo.soundary.services.authentication.domain.SocialPlatform;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SocialUserCreationSupportImpl implements SocialUserCreationSupport {
    @Value("${app.user.default-profile-image-url}")
    private String defaultProfileImageUrl;
    private final IdentifierGenerator identifierGenerator;
    private final UserRepository userRepository;
    private final SocialAccountRepository socialAccountRepository;

    @Override
    public User createAndRegisterSocialUser(SocialPlatform platform, String socialId) {
        Identifier userId = identifierGenerator.generate();
        Identifier displayId = identifierGenerator.generate();
        SocialAccount socialAccount = new SocialAccount(platform, socialId, userId);
        socialAccountRepository.save(socialAccount);
        User newUser = User.newUser(
                userId,
                displayId.toString(),
                displayId.toString(),
                "",
                defaultProfileImageUrl
        );
        return userRepository.save(newUser);
    }
}
