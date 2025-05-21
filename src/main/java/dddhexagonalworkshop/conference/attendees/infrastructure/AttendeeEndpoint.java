package dddhexagonalworkshop.conference.attendees.infrastructure;

import dddhexagonalworkshop.conference.attendees.api.AttendeeDTO;
import dddhexagonalworkshop.conference.attendees.domain.services.AttendeeService;
import dddhexagonalworkshop.conference.attendees.api.RegisterAttendeeCommand;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("/attendees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AttendeeEndpoint {

    @Inject
    AttendeeService attendeeService;

    @POST
    public Response createAttendee(RegisterAttendeeCommand registerAttendeeCommand) {

        Log.debugf("Creating attendee %s", registerAttendeeCommand);
        AttendeeDTO attendeeDTO = attendeeService.registerAttendee(registerAttendeeCommand);
        Log.debugf("Created attendee %s", attendeeDTO);
        return Response.created(URI.create("/" + attendeeDTO.email())).entity(attendeeDTO).build();
    }

    @GET
    @Path("/{email}")
    public Response getAttendee(@PathParam("email") String email) {
        Log.debugf("Looking up attendee with email: %s", email);
        if(attendeeService.lookupAttendee(email).isEmpty()) {
            Log.debugf("Attendee with email %s not found", email);
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            AttendeeDTO attendeeDTO = attendeeService.lookupAttendee(email).get();
            Log.debugf("Attendee with email %s found", attendeeDTO);
            return Response.ok(attendeeDTO).build();
        }
    }
}
