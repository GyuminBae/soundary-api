package io.github.eappezo.soundary.services.authentication.application;

import io.github.eappezo.soundary.core.user.User;

public interface TokenProvider {

    String generateAccessTokenFrom(User user);

    String generateRefreshTokenFrom(User user);

    Long getAccessTokenExpirationTime();

}
