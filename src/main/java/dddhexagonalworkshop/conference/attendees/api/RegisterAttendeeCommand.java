package dddhexagonalworkshop.conference.attendees.api;

import dddhexagonalworkshop.conference.attendees.domain.valueobjects.Name;
import dddhexagonalworkshop.conference.attendees.domain.valueobjects.TShirtSize;

public record RegisterAttendeeCommand(String email, Name name, boolean isStudent, boolean isEmployee, MealPreference mealPreference, TShirtSize tShirtSize, AddressDTO addressDTO) {
}
