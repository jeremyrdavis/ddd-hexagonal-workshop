package dddhexagonalworkshop.conference.attendees.domain;

import dddhexagonalworkshop.conference.attendees.api.AttendeeRegistrationEvent;
import dddhexagonalworkshop.conference.sharedkernel.events.CateringEvent;
import dddhexagonalworkshop.conference.attendees.api.SwagEvent;
import dddhexagonalworkshop.conference.attendees.persistence.AttendeeEntity;

public record AttendeeRegistrationResult(AttendeeEntity attendee,
                                         AttendeeRegistrationEvent attendeeRegistrationEvent,
                                         SwagEvent swagEvent,
                                         CateringEvent cateringEvent) {
}
