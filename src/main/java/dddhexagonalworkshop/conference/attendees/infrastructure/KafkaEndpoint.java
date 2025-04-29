package dddhexagonalworkshop.conference.attendees.infrastructure;

import dddhexagonalworkshop.conference.attendees.api.AttendeeValueObject;
import dddhexagonalworkshop.conference.attendees.domain.AttendeeService;
import dddhexagonalworkshop.conference.attendees.api.RegisterAttendeeCommand;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class KafkaEndpoint {

    @Inject
    AttendeeService attendeeService;

    @Incoming("attendee")
    @Transactional
    public void registerAttendee(RegisterAttendeeCommand registerAttendeeCommand) {
        Log.debugf("Registering attendee from Kafka: %s", registerAttendeeCommand);
        AttendeeValueObject result = attendeeService.registerAttendee(registerAttendeeCommand);
        Log.debugf("Registered attendee: %s", result);
    }
}
