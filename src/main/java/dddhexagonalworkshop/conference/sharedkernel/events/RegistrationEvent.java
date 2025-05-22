package dddhexagonalworkshop.conference.sharedkernel.events;

import dddhexagonalworkshop.conference.attendees.domain.valueobjects.Badge;
import dddhexagonalworkshop.conference.attendees.domain.valueobjects.Email;

public record RegistrationEvent(Badge badge, Email email) {

}