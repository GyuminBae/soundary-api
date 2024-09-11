package io.github.eappezo.soundary.core.persistence.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserEntity extends BaseAuditingEntity {
    @Id
    @Column(name = "id")
    private String id;
}
