package dddhexagonalworkshop.conference.attendees.domain.services;

import dddhexagonalworkshop.conference.attendees.api.MealPreference;
import dddhexagonalworkshop.conference.attendees.domain.valueobjects.Address;
import dddhexagonalworkshop.conference.attendees.domain.valueobjects.Name;
import dddhexagonalworkshop.conference.attendees.domain.valueobjects.TShirtSize;

public record RegisterAttendeeCommand(String email, Name name, boolean isStudent, boolean isEmployee, MealPreference mealPreference, TShirtSize tShirtSize, Address address) {
}
