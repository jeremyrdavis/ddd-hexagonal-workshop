package com.redhat.demos.domain;

public record AttendeeRegistrationResult(Attendee attendee,
                                         AttendeeRegistrationEvent attendeeRegistrationEvent,
                                         SwagEvent swagEvent,
                                         CateringEvent cateringEvent) {
}
