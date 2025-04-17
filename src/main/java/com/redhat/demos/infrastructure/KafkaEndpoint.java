package com.redhat.demos.infrastructure;

import com.redhat.demos.domain.AttendeeService;
import com.redhat.demos.domain.RegisterAttendeeCommand;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class KafkaEndpoint {

    @Inject
    AttendeeService attendeeService;

    @Incoming("attendee")
    @Transactional
    public void registerAttendee(RegisterAttendeeCommand registerAttendeeCommand) {
        attendeeService.registerAttendee(registerAttendeeCommand);
    }
}
