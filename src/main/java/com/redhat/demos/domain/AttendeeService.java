package com.redhat.demos.domain;

import io.smallrye.reactive.messaging.annotations.EmitterFactoryFor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class AttendeeService {

    @Inject
    AttendeeRepository attendeeRepository;

    @Channel("atten")
    public Emitter<AttendeeRegistrationEvent> emitter;

    @Transactional
    public void registerAttendee(RegisterAttendeeCommand registerAttendeeCommand) {

        //create an attendee
        AttendeeRegistrationResult result = Attendee.registerAttendee(registerAttendeeCommand);
        //persist the attendee
        attendeeRepository.persist(result.attendee());

        //notify the system that a new attendee has been registered
        emitter.send(result.attendeeRegistrationEvent());
    }
}
