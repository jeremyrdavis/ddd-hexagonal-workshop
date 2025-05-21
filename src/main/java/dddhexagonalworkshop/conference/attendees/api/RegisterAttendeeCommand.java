package dddhexagonalworkshop.conference.attendees.api;

public record RegisterAttendeeCommand(String email, String name, boolean isStudent, boolean isEmployee, MealPreference mealPreference, TShirtSize tShirtSize, AddressDTO addressDTO) {
}
