package com.redhat.demos.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Address {
    @Id
    private Long id;

    void setId(Long id) {
        this.id = id;
    }

    Long getId() {
        return id;
    }
}
