package io.github.eappezo.soundary.services.authentication.infrastructure.persistence;

import io.github.eappezo.soundary.services.authentication.domain.SocialPlatform;
import jakarta.persistence.*;

@Entity
public class SocialAccountEntity {
    @Id
    @Enumerated(EnumType.STRING)
    private SocialPlatform platform;

    @Id
    @Column(name = "social_id")
    private String socialId;

    @Column(name = "user_id")
    private String userId;
}
