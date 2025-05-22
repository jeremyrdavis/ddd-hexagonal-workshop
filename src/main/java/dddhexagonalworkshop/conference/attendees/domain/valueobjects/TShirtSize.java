package dddhexagonalworkshop.conference.attendees.domain.valueobjects;

import jakarta.persistence.Embeddable;
import java.util.Arrays;
import java.util.List;

/**
 * TShirtSize value object that encapsulates t-shirt size validation and behavior.
 * This is an example of a Domain-Driven Design value object implemented as a record.
 */
@Embeddable
public record TShirtSize(String value) {

    private static final List<String> VALID_SIZES = Arrays.asList(
            "SMALL", "MEDIUM", "LARGE", "XLARGE", "XXLARGE", "XXXLARGE"
    );
    // Valid t-shirt size constants
    public static final TShirtSize SMALL = new TShirtSize("SMALL");
    public static final TShirtSize MEDIUM = new TShirtSize("MEDIUM");
    public static final TShirtSize LARGE = new TShirtSize("LARGE");
    public static final TShirtSize XLARGE = new TShirtSize("XLARGE");
    public static final TShirtSize XXLARGE = new TShirtSize("XXLARGE");
    public static final TShirtSize XXXLARGE = new TShirtSize("XXXLARGE");


    /**
     * Compact constructor for validation
     */
    public TShirtSize {
        validate(value);
    }

    /**
     * Validates that the size value is valid.
     *
     * @throws IllegalArgumentException if the size is invalid
     */
    private void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("T-shirt size cannot be empty");
        }

        String normalizedValue = value.toUpperCase();
        if (!VALID_SIZES.contains(normalizedValue)) {
            throw new IllegalArgumentException("Invalid t-shirt size: " + value +
                    ". Valid sizes are: " + String.join(", ", VALID_SIZES));
        }
    }

    /**
     * Creates a TShirtSize from a case-insensitive string value.
     *
     * @param value the size value
     * @return a TShirtSize instance
     */
    public static TShirtSize fromString(String value) {
        return new TShirtSize(value.toUpperCase());
    }

    /**
     * Checks if this size is larger than another size.
     *
     * @param other the size to compare with
     * @return true if this size is larger than the other
     */
    public boolean isLargerThan(TShirtSize other) {
        return VALID_SIZES.indexOf(this.value) > VALID_SIZES.indexOf(other.value);
    }

    /**
     * Returns a display-friendly representation of the size.
     *
     * @return the display name (e.g., "Small", "Medium", "XL")
     */
    public String getDisplayName() {
        return switch (value) {
            case "SMALL" -> "S";
            case "MEDIUM" -> "M";
            case "LARGE" -> "L";
            case "XLARGE" -> "XL";
            case "XXLARGE" -> "XXL";
            case "XXXLARGE" -> "XXXL";
            default -> value;
        };
    }

    @Override
    public String toString() {
        return value;
    }
}