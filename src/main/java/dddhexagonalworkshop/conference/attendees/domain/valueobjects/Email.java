package dddhexagonalworkshop.conference.attendees.domain.valueobjects;

import jakarta.persistence.Embeddable;
import java.util.regex.Pattern;

/**
 * Email value object that encapsulates email validation and behavior.
 * This is an example of a Domain-Driven Design value object implemented as a record.
 */
public record Email(String value) {
    // Regular expression for basic email validation
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
    );

    /**
     * Compact constructor for validation
     */
    public Email {
        validate(value);
    }

    /**
     * Validates that the email is properly formatted.
     *
     * @throws IllegalArgumentException if the email is invalid
     */
    private void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        if (value.length() > 254) {
            throw new IllegalArgumentException("Email is too long (max 254 characters)");
        }

        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid email format: " + value);
        }
    }

    /**
     * Returns the email domain (part after '@').
     *
     * @return the domain part of the email
     */
    public String getDomain() {
        int atIndex = value.indexOf('@');
        return value.substring(atIndex + 1);
    }

    /**
     * Returns the local part (part before '@').
     *
     * @return the local part of the email
     */
    public String getLocalPart() {
        int atIndex = value.indexOf('@');
        return value.substring(0, atIndex);
    }

    /**
     * Returns a normalized version of the email (lowercase).
     *
     * @return normalized email address
     */
    public String getNormalizedEmail() {
        return value.toLowerCase();
    }

    @Override
    public String toString() {
        return value;
    }
}