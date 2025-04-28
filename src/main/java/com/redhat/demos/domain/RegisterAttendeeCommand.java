package com.redhat.demos.domain;

public record RegisterAttendeeCommand(String email, String name, boolean isStudent, boolean isEmployee, MealPreference mealPreference, TShirtSize tShirtSize, AddressValueObject addressValueObject) {
}
