package com.redhat.demos.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class BadgeEntity {

    @Id @GeneratedValue
    private Long id;

    private UUID badgeId;

    private String email;

    public BadgeEntity() {

    }

    BadgeEntity(UUID badgeId, String email) {
        this.email = email;
        this.badgeId = badgeId;
    }
}
