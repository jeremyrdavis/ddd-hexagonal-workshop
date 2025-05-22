package dddhexagonalworkshop.conference.attendees.anticorruption.salesteam;

import dddhexagonalworkshop.conference.attendees.api.AddressDTO;
import dddhexagonalworkshop.conference.attendees.api.MealPreference;
import dddhexagonalworkshop.conference.attendees.api.RegisterAttendeeCommand;
import dddhexagonalworkshop.conference.attendees.domain.services.AttendeeService;
import dddhexagonalworkshop.conference.attendees.domain.valueobjects.TShirtSize;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@QuarkusTest
public class SalesteamEndpointTest {

    @Inject
    SalesteamEndpoint salesteamEndpoint;

    @Inject
    AttendeeService attendeeService;

    @BeforeEach
    void setUp() {
        attendeeService = mock(AttendeeService.class);
        salesteamEndpoint.attendeeService = attendeeService;
    }

    @Test
    void registerAttendees_shouldRegisterAttendeesFromSalesteamRequest() {
        // Arrange
        Customer customer1 = new Customer(
             "Frodo",
             "Baggins",
                "frodo.baggins@theshire.uk",
             "Bag End, Hobbiton",
             "",
             "The Shire",
             "Shire",
             "12345",
             new CustomerDetails(false, DietaryRequirements.VEGETARIAN, TShirtSize.SMALL));
        Customer customer2 = new Customer(
                                "Samwise",
                                "Gamgee",
                "samwise.gamgee@theshire.uk",
                                "Bag End, Hobbiton",
                                "",
                                "The Shire",
                                "Shire",
                                "12345",
                                new CustomerDetails(false, DietaryRequirements.NONE, TShirtSize.LARGE));

        SalesteamRegistrationRequest salesteamRegistrationRequest = new SalesteamRegistrationRequest(List.of(customer1, customer2));

        ArgumentCaptor<RegisterAttendeeCommand> commandCaptor = ArgumentCaptor.forClass(RegisterAttendeeCommand.class);

        // Act
        Response response = salesteamEndpoint.registerAttendees(salesteamRegistrationRequest);

        // Assert
        assertEquals(Response.Status.ACCEPTED.getStatusCode(), response.getStatus());
        verify(attendeeService, times(2)).registerAttendee(commandCaptor.capture());

        List<RegisterAttendeeCommand> capturedCommands = commandCaptor.getAllValues();

        assertEquals("samwise.gamgee@theshire.uk", capturedCommands.get(1).email());
        assertEquals("Samwise Gamgee", capturedCommands.get(1).name().getFullName());
        assertEquals(false, capturedCommands.get(1).isStudent());
        assertEquals(MealPreference.NONE, capturedCommands.get(1).mealPreference());
        assertEquals(TShirtSize.LARGE, capturedCommands.get(1).tShirtSize());
        assertEquals(new AddressDTO("Bag End, Hobbiton", "", "The Shire", "Shire", "12345", "USA"), capturedCommands.get(1).addressDTO());
    }
}