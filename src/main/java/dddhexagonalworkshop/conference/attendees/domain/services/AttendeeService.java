package dddhexagonalworkshop.conference.attendees.domain.services;

import dddhexagonalworkshop.conference.attendees.api.AttendeeDTO;
import dddhexagonalworkshop.conference.attendees.api.AttendeeRegistrationEvent;
import dddhexagonalworkshop.conference.attendees.api.RegisterAttendeeCommand;
import dddhexagonalworkshop.conference.attendees.domain.aggregates.Attendee;
import dddhexagonalworkshop.conference.attendees.domain.AttendeeRegistrationResult;
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
    public AttendeeDTO registerAttendee(RegisterAttendeeCommand registerAttendeeCommand) {

        //create an attendee
        AttendeeRegistrationResult result = Attendee.registerAttendee(registerAttendeeCommand);

        //persist the attendee
        QuarkusTransaction.requiringNew().run(() -> {
            attendeeRepository.persist(result.attendee());
        });

        //notify the system that a new attendee has been registered
        attendeesTopic.send(result.attendeeRegistrationEvent());

        AttendeeDTO attendeeDTO = new AttendeeDTO(result.attendee().getFullName(), result.attendee().getEmail());
        Log.debugf("Returning attendee %s", attendeeDTO);
        return attendeeDTO;
    }

    public Optional<AttendeeDTO> lookupAttendee(String email) {
        Log.debugf("Looking up attendee with email: %s", email);
        if(attendeeRepository.findByEmail(email).isPresent()) {
            AttendeeEntity attendeeEntity = attendeeRepository.findByEmail(email).get();
            return Optional.of(new AttendeeDTO(attendeeEntity.getFullName(), attendeeEntity.getEmail()));
        }else {
            Log.debugf("Attendee with email %s not found", email);
            return Optional.empty();
        }
    }
}
