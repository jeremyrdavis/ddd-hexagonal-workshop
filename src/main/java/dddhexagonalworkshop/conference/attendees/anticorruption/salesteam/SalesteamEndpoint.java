package dddhexagonalworkshop.conference.attendees.anticorruption.salesteam;

import dddhexagonalworkshop.conference.attendees.api.AddressDTO;
import dddhexagonalworkshop.conference.attendees.api.MealPreference;
import dddhexagonalworkshop.conference.attendees.api.RegisterAttendeeCommand;
import dddhexagonalworkshop.conference.attendees.domain.services.AttendeeService;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/salesteam")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SalesteamEndpoint {

    @Inject
    AttendeeService attendeeService;

    @POST
    public Response registerAttendees(SalesteamRegistrationRequest salesteamRegistrationRequest) {
        Log.debugf("Registering attendees for %s", salesteamRegistrationRequest);

        List<RegisterAttendeeCommand> commands = salesteamRegistrationRequest.customers().stream()
                .map(customer -> new RegisterAttendeeCommand(
                        customer.email(),
                        customer.firstName() + " " + customer.lastName(),
                        customer.customerDetails().student(),
                        false,
                        mapDietaryRequirements(customer.customerDetails().dietaryRequirements()),
                        customer.customerDetails().tShirtSize(),
                        new AddressDTO(
                        customer.address(),
                        customer.address2(),
                        customer.city(),
                        customer.state(),
                        customer.zip(),
                        "USA")
                ))
                .toList();
        commands.forEach(attendeeService::registerAttendee);
        return Response.accepted().build();
    }

    private MealPreference mapDietaryRequirements(DietaryRequirements dietaryRequirements) {
        if (dietaryRequirements == null) {
            return MealPreference.NONE;
        }
        return switch (dietaryRequirements) {
            case VEGETARIAN -> MealPreference.VEGETARIAN;
            case GLUTEN_FREE -> MealPreference.GLUTEN_FREE;
            case NONE -> MealPreference.NONE;
        };
    }}
