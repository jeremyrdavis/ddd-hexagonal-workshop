package dddhexagonalworkshop.conference.attendees.infrastructure;

import dddhexagonalworkshop.conference.attendees.api.AttendeeDTO;
import dddhexagonalworkshop.conference.attendees.domain.services.AttendeeService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

@QuarkusTest
public class AttendeeEndpointTest {

    @InjectMock
    AttendeeService attendeeService;

    String postBody = """
            {
              "email": "bilbo@theshire.uk",
              "name": "Bilbo Baggins",
              "isStudent": false,
              "isEmployee": false,
              "mealPreference": "NONE",
              "tShirtSize": "MEDIUM",
              "isStudent": false,
              "isEmployee": false,
              "addressValueObject": {
                "street": "Bag End",
                "street2": null,
                "city": "Hobbiton",
                "stateOrProvince": "The Shire",
                "postCode": "12345",
                "country": "Middle-earth"
              }
            }
            """;


    @Test
    public void testRegisterAttendeeEndpoint() {
        // Stub the attendeeService to return a predefined AttendeeValueObject
        AttendeeDTO stubbedAttendee = new AttendeeDTO("Bilbo Baggins", "bilbo@theshire.uk");
        when(attendeeService.registerAttendee(Mockito.any()))
                .thenReturn(stubbedAttendee);

        with()
                .body(postBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .request("post", "/attendees")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .body("name", is("Bilbo Baggins"),
                        "email", is("bilbo@theshire.uk"));
    }

    @Test
    public void testGetAttendeeEndpoint() {
        // Stub the attendeeService to return a predefined AttendeeValueObject when the email is "john.doe@example.com"
        AttendeeDTO stubbedAttendee = new AttendeeDTO("John Doe", "john.doe@example.com");
        when(attendeeService.lookupAttendee("john.doe@example.com"))
                .thenReturn(Optional.of(stubbedAttendee));

        given()
                .when().get("/attendees/john.doe@example.com")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("name", is("John Doe"),
                        "email", is("john.doe@example.com"));
    }

    @Test
    public void testGetAttendeeEndpoint_NotFound() {
        // Stub the attendeeService to return an empty Optional when the email is not found
        when(attendeeService.lookupAttendee("nonexistent@example.com"))
                .thenReturn(Optional.empty());

        given()
                .when().get("/attendees/nonexistent@example.com")
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }
}