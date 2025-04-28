package com.redhat.demos.domain;

import java.util.UUID;

public record Badge(UUID badgeNumber, String email) {
}
