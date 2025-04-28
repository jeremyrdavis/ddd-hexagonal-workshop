package com.redhat.demos.domain;

import com.redhat.demos.persistence.AttendeeEntity;

public record AttendeeRegistrationResult(AttendeeEntity attendee,
                                         AttendeeRegistrationEvent attendeeRegistrationEvent,
                                         SwagEvent swagEvent,
                                         CateringEvent cateringEvent) {
}
