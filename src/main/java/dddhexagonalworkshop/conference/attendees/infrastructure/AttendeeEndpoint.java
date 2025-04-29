package dddhexagonalworkshop.conference.attendees.infrastructure;

import dddhexagonalworkshop.conference.attendees.api.AttendeeEmail;
import dddhexagonalworkshop.conference.attendees.api.AttendeeValueObject;
import dddhexagonalworkshop.conference.attendees.domain.AttendeeService;
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
        AttendeeValueObject attendeeValueObject = attendeeService.registerAttendee(registerAttendeeCommand);
        Log.debugf("Created attendee %s", attendeeValueObject);
        return Response.created(URI.create("/" + attendeeValueObject.email())).entity(attendeeValueObject).build();
    }

    @GET
    @Path("/{email}")
    public Response getAttendee(@PathParam("email") String email) {
        Log.debugf("Looking up attendee with email: %s", email);
        if(attendeeService.lookupAttendee(email).isEmpty()) {
            Log.debugf("Attendee with email %s not found", email);
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            AttendeeValueObject attendeeValueObject = attendeeService.lookupAttendee(email).get();
            Log.debugf("Attendee with email %s found", attendeeValueObject);
            return Response.ok(attendeeValueObject).build();
        }
    }
}
