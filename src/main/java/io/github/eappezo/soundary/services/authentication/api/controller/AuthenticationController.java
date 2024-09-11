package io.github.eappezo.soundary.services.authentication.api.controller;

import io.github.eappezo.soundary.services.authentication.api.dto.AuthenticationResponse;
import io.github.eappezo.soundary.services.authentication.api.dto.AuthentificationRequest;
import io.github.eappezo.soundary.services.authentication.application.AuthenticatedUserDto;
import io.github.eappezo.soundary.services.authentication.application.AuthenticationRequestDto;
import io.github.eappezo.soundary.services.authentication.application.service.OAuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final OAuthService oAuthService;

    public AuthenticationController(OAuthService oAuthService) {
        this.oAuthService = oAuthService;
    }

    @GetMapping("/oauth/login")
    public AuthenticationResponse login(@RequestBody AuthentificationRequest request){
        AuthenticationRequestDto dto = request.to();
        AuthenticatedUserDto userDto = oAuthService.login(dto);
        return new AuthenticationResponse(userDto.accessToken(), userDto.refreshToken(), userDto.expiresIn());
    }
}
