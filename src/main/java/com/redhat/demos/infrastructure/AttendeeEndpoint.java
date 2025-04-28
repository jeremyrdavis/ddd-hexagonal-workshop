package com.redhat.demos.infrastructure;

import com.redhat.demos.domain.AttendeeService;
import com.redhat.demos.domain.RegisterAttendeeCommand;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/attendees")
public class AttendeeEndpoint {

    @Inject
    AttendeeService attendeeService;

    @POST
    public void createAttendee(RegisterAttendeeCommand registerAttendeeCommand) {

        attendeeService.registerAttendee(registerAttendeeCommand);
    }
}
