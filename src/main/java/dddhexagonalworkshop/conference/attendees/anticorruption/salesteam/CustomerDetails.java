package dddhexagonalworkshop.conference.attendees.anticorruption.salesteam;

import dddhexagonalworkshop.conference.attendees.api.TShirtSize;

public record CustomerDetails(boolean student,
                              DietaryRequirements dietaryRequirements,
                              TShirtSize tShirtSize) {
}
