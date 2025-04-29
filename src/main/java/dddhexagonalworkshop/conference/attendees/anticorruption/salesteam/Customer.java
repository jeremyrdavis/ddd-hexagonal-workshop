package dddhexagonalworkshop.conference.attendees.anticorruption.salesteam;

public record Customer(String firstName,
                       String lastName,
                       String email,
                       String address,
                       String address2,
                       String city,
                       String state,
                       String zip,
                       CustomerDetails customerDetails) {
}
