package dddhexagonalworkshop.conference.attendees.domain;

import dddhexagonalworkshop.conference.attendees.api.AttendeeRegistrationEvent;
import dddhexagonalworkshop.conference.attendees.api.AttendeeValueObject;
import dddhexagonalworkshop.conference.attendees.api.RegisterAttendeeCommand;
import dddhexagonalworkshop.conference.attendees.persistence.AttendeeEntity;
import dddhexagonalworkshop.conference.attendees.persistence.AttendeeRepository;
import io.quarkus.logging.Log;
import io.quarkus.narayana.jta.QuarkusTransaction;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.Optional;

@ApplicationScoped
public class AttendeeService {

    @Inject
    AttendeeRepository attendeeRepository;

    @Channel("attendees")
    public Emitter<AttendeeRegistrationEvent> attendeesTopic;

    @Transactional
    public AttendeeValueObject registerAttendee(RegisterAttendeeCommand registerAttendeeCommand) {

        //create an attendee
        AttendeeRegistrationResult result = AttendeeDomainObject.registerAttendee(registerAttendeeCommand);

        //persist the attendee
        QuarkusTransaction.requiringNew().run(() -> {
            attendeeRepository.persist(result.attendee());
        });

        //notify the system that a new attendee has been registered
        attendeesTopic.send(result.attendeeRegistrationEvent());

        AttendeeValueObject attendeeValueObject = new AttendeeValueObject(result.attendee().getName(), result.attendee().getEmail());
        Log.debugf("Returning attendee %s", attendeeValueObject);
        return attendeeValueObject;
    }

    public Optional<AttendeeValueObject> lookupAttendee(String email) {
        Log.debugf("Looking up attendee with email: %s", email);
        if(attendeeRepository.findByEmail(email).isPresent()) {
            AttendeeEntity attendeeEntity = attendeeRepository.findByEmail(email).get();
            return Optional.of(new AttendeeValueObject(attendeeEntity.getName(), attendeeEntity.getEmail()));
        }else {
            Log.debugf("Attendee with email %s not found", email);
            return Optional.empty();
        }
    }
}
