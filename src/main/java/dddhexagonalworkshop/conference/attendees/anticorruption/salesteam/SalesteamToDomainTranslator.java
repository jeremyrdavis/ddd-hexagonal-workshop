package dddhexagonalworkshop.conference.attendees.anticorruption.salesteam;

import dddhexagonalworkshop.conference.attendees.api.AddressDTO;
import dddhexagonalworkshop.conference.attendees.api.MealPreference;
import dddhexagonalworkshop.conference.attendees.api.RegisterAttendeeCommand;

import java.util.List;

public class SalesteamToDomainTranslator {

    public static List<RegisterAttendeeCommand> translate(List<Customer> customers) {
        return customers.stream()
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
    }

    private static MealPreference mapDietaryRequirements(DietaryRequirements dietaryRequirements) {
        if (dietaryRequirements == null) {
            return MealPreference.NONE;
        }
        return switch (dietaryRequirements) {
            case VEGETARIAN -> MealPreference.VEGETARIAN;
            case GLUTEN_FREE -> MealPreference.GLUTEN_FREE;
            case NONE -> MealPreference.NONE;
        };
    }
}