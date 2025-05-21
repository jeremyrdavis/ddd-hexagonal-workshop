package dddhexagonalworkshop.conference.attendees.domain.aggregates;

import dddhexagonalworkshop.conference.attendees.api.AttendeeRegistrationEvent;
import dddhexagonalworkshop.conference.attendees.api.CateringEvent;
import dddhexagonalworkshop.conference.attendees.api.RegisterAttendeeCommand;
import dddhexagonalworkshop.conference.attendees.api.SwagEvent;
import dddhexagonalworkshop.conference.attendees.domain.AttendeeRegistrationResult;
import dddhexagonalworkshop.conference.attendees.domain.Badge;
import dddhexagonalworkshop.conference.attendees.persistence.AttendeeEntity;

import java.util.UUID;

public class Attendee {

    public static AttendeeRegistrationResult registerAttendee(RegisterAttendeeCommand registerAttendeeCommand) {

        AttendeeEntity attendee = new AttendeeEntity(
                generateBadgeId(registerAttendeeCommand.email()),
                registerAttendeeCommand.email(),
                registerAttendeeCommand.name(),
                registerAttendeeCommand.isStudent(),
                registerAttendeeCommand.isEmployee(),
                registerAttendeeCommand.mealPreference(),
                registerAttendeeCommand.tShirtSize(),
                registerAttendeeCommand.addressDTO());

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
