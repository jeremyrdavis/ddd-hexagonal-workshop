package dddhexagonalworkshop.conference.attendees.domain.valueobjects;

import jakarta.persistence.Embeddable;
import java.util.UUID;

/**
 * Badge value object that encapsulates badge validation and behavior.
 * This is an example of a Domain-Driven Design value object implemented as a record.
 */
public record Badge(UUID badgeNumber, String email) {

    /**
     * Compact constructor for validation
     */
    public Badge {
        validate(badgeNumber, email);
    }

    private void validate(UUID badgeNumber, String email) {
        if (badgeNumber == null) {
            throw new IllegalArgumentException("Badge number cannot be null");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        // You can add more sophisticated email validation here if needed
        if (email.length() > 254) {
            throw new IllegalArgumentException("Email is too long (max 254 characters)");
        }
    }

    @Override
    public String toString() {
        return "Badge{" +
                "badgeNumber=" + badgeNumber +
                ", email='" + email + '\'' +
                '}';
    }
}