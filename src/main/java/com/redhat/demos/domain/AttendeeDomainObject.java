package com.redhat.demos.domain;

import com.redhat.demos.persistence.AttendeeEntity;

import java.util.UUID;

public class AttendeeDomainObject {

    protected static AttendeeRegistrationResult registerAttendee(RegisterAttendeeCommand registerAttendeeCommand) {

        AttendeeEntity attendee = new AttendeeEntity(
                generateBadgeId(registerAttendeeCommand.email()),
                registerAttendeeCommand.email(),
                registerAttendeeCommand.name(),
                registerAttendeeCommand.isStudent(),
                registerAttendeeCommand.isEmployee(),
                registerAttendeeCommand.mealPreference(),
                registerAttendeeCommand.tShirtSize(),
                registerAttendeeCommand.addressValueObject());

        Double calculatedPrice = calculatePrice(100.0, registerAttendeeCommand);
        CateringEvent cateringEvent = new CateringEvent(registerAttendeeCommand.mealPreference());
        SwagEvent swagEvent = new SwagEvent(registerAttendeeCommand.tShirtSize());
        AttendeeRegistrationEvent attendeeRegistrationEvent = new AttendeeRegistrationEvent(attendee.getEmail(), calculatedPrice);
        return new AttendeeRegistrationResult(attendee, attendeeRegistrationEvent, swagEvent,cateringEvent);
    }

    /*
        1/2 price for students
        No charge for employees
     */
    private static Double calculatePrice(Double basePrice, RegisterAttendeeCommand registerAttendeeCommand) {
        if(registerAttendeeCommand.isStudent()) {
            return basePrice / 2;
        }else if(registerAttendeeCommand.isEmployee()) {
            return 0.0;
        }else {
            return Double.valueOf(basePrice);
        }

    }

    private static Badge generateBadgeId(String email) {
        UUID uuid = UUID.randomUUID();
        String badgeId = uuid.toString() + " " + email;
        return new Badge(UUID.randomUUID(), email);
    }

}
