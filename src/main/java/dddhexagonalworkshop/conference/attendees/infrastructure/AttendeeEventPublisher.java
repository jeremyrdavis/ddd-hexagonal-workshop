package dddhexagonalworkshop.conference.attendees.infrastructure;

import dddhexagonalworkshop.conference.attendees.api.AttendeeDTO;
import dddhexagonalworkshop.conference.attendees.api.AttendeeRegistrationEvent;
import dddhexagonalworkshop.conference.attendees.domain.services.AttendeeService;
import dddhexagonalworkshop.conference.attendees.domain.services.RegisterAttendeeCommand;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class AttendeeEventPublisher {

    @Inject
    AttendeeService attendeeService;

    @Channel("attendees")
    public Emitter<AttendeeRegistrationEvent> attendeesTopic;

    @Incoming("attendee")
    @Transactional
    public void registerAttendee(RegisterAttendeeCommand registerAttendeeCommand) {
        Log.debugf("Registering attendee from Kafka: %s", registerAttendeeCommand);
        AttendeeDTO result = attendeeService.registerAttendee(registerAttendeeCommand);
        Log.debugf("Registered attendee: %s", result);
    }

    public void publish(AttendeeRegistrationEvent attendeeRegistrationEvent) {
        attendeesTopic.send(attendeeRegistrationEvent);
    }
}
