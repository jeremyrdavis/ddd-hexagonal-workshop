# Workshop Workflow

## Overview

First iteration: create the basic structure of the Attendee registration microservice using Hexagonal Architecture (Ports and Adapters) and Domain-Driven Design (DDD) principles.
Second iteration: add the Attendee's Address
Third iteration: add the Attendee's Payment information, calling the Payment microservice, illustrating the Conformist integration pattern, and the Money Value Object.
Fourth iteration: add the Anti-corruption Layer to illustrate the anti-corruption style integration pattern.

## First Iteration

- Create the AttendeeEndpoint in the attendees/infrastructure package
  - a single POST method that takes a RegisterAttendeeCommand
- create a RegisterAttendeeCommand object with a single String, "email"

```java
package dddhexagonalworkshop.conference.attendees.domain.services;

public record RegisterAttendeeCommand(String email) {
}

```

-
- Create the AttendeeService in the attendes/domain/services package
  - create one method, "registerAttendee" that takes a RegisterAttendeeCommand

```java
package dddhexagonalworkshop.conference.attendees.infrastructure;

import dddhexagonalworkshop.conference.attendees.api.AttendeeDTO;
import dddhexagonalworkshop.conference.attendees.domain.services.AttendeeService;
import dddhexagonalworkshop.conference.attendees.domain.services.RegisterAttendeeCommand;
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
  public Response registerAttendee(RegisterAttendeeCommand registerAttendeeCommand) {
    Log.debugf("Creating attendee %s", registerAttendeeCommand);

    AttendeeDTO attendeeDTO = attendeeService.registerAttendee(registerAttendeeCommand);

    Log.debugf("Created attendee %s", attendeeDTO);

    return Response.created(URI.create("/" + attendeeDTO.email())).entity(attendeeDTO).build();
  }

}

```

- Create the AttendeeEntity in the attendees/domain/entities package
    - only one field, "email"

```java
package dddhexagonalworkshop.conference.attendees.persistence;

import dddhexagonalworkshop.conference.attendees.api.AddressDTO;
import dddhexagonalworkshop.conference.attendees.domain.valueobjects.Badge;
import jakarta.persistence.*;

@Entity @Table(name = "attendee")
public class AttendeeEntity {

    @Id @GeneratedValue
    private Long id;

    private String email;

    protected AttendeeEntity() {

    }

    protected AttendeeEntity(String email) {
        this.email = email;
    }

    protected Long getId() {
        return id;
    }

    protected String getEmail() {
        return email;
    }

}
```

- Create the AttendeeRegistredEvent in the sharedkernel/events package
    - create a single field, "email"

```java
package dddhexagonalworkshop.conference.attendees.api.events;

public record AttendeeRegisteredEvent(String email) {
}
```

- Create the Attendee Aggregate in attendees/domain/aggregates
    - create a single method, "registerAttendee"
    - implement the method
      - by creating an AttendeeEntity and an AttendeeRegistredEvent
      - Create the AttendeeRegistrationResult in the attendees/domain/services package to return the AttendeeEntity and AttendeeRegisteredEvent
```java
package dddhexagonalworkshop.conference.attendees.domain.aggregates;

import dddhexagonalworkshop.conference.attendees.api.events.AttendeeRegisteredEvent;
import dddhexagonalworkshop.conference.attendees.domain.services.RegisterAttendeeCommand;
import dddhexagonalworkshop.conference.attendees.domain.valueobjects.Badge;

import java.util.UUID;

public class Attendee {

    public static AttendeeRegisteredEvent registerAttendee(RegisterAttendeeCommand registerAttendeeCommand) {

        AttendeeRegisteredEvent attendeeRegisteredEvent = new AttendeeRegisteredEvent(registerAttendeeCommand.email());
        return attendeeRegisteredEvent;
    }
}

```

- Create the AttendeeRepository using Hibernate Panache

```java
package dddhexagonalworkshop.conference.attendees.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AttendeeRepository implements PanacheRepository<AttendeeEntity> {

}
```

- Create the AttendeeEventPublisher
  - create a single method, "publish" that takes an AttendeeRegisteredEvent
  - implement the method by sending the event to Kafka

```java
package dddhexagonalworkshop.conference.attendees.infrastructure;

import dddhexagonalworkshop.conference.attendees.api.AttendeeRegistrationEvent;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class AttendeeEventPublisher {

    @Channel("attendees")
    public Emitter<AttendeeRegistrationEvent> attendeesTopic;

    public void publish(AttendeeRegistrationEvent attendeeRegistrationEvent) {
        attendeesTopic.send(attendeeRegistrationEvent);
    }
}
```

- Update the AttendeeService so that it persists the attendee and publishes the event
  - update the registerAttendee method to return an AttendeeRegistratedResult
  - update the registerAttendee method to call the AttendeeEventPublisher

```java
package dddhexagonalworkshop.conference.attendees.domain.services;

import dddhexagonalworkshop.conference.attendees.api.AttendeeDTO;
import dddhexagonalworkshop.conference.attendees.domain.aggregates.Attendee;
import dddhexagonalworkshop.conference.attendees.domain.AttendeeRegistrationResult;
import dddhexagonalworkshop.conference.attendees.infrastructure.AttendeeEventPublisher;
import dddhexagonalworkshop.conference.attendees.persistence.AttendeeEntity;
import dddhexagonalworkshop.conference.attendees.persistence.AttendeeRepository;
import io.quarkus.logging.Log;
import io.quarkus.narayana.jta.QuarkusTransaction;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
public class AttendeeService {

    @Inject
    AttendeeRepository attendeeRepository;

    @Inject
    AttendeeEventPublisher attendeeEventPublisher;

    @Transactional
    public AttendeeDTO registerAttendee(RegisterAttendeeCommand registerAttendeeCommand) {

        //create an attendee
        AttendeeRegistrationResult result = Attendee.registerAttendee(registerAttendeeCommand);

        //persist the attendee
        QuarkusTransaction.requiringNew().run(() -> {
            attendeeRepository.persist(result.attendee());
        });

        //notify the system that a new attendee has been registered
        attendeeEventPublisher.publish(result.attendeeRegistrationEvent());

        AttendeeDTO attendeeDTO = new AttendeeDTO(result.attendee().getFullName(), result.attendee().getEmail());
        Log.debugf("Returning attendee %s", attendeeDTO);
        return attendeeDTO;
    }
}

```

## Summary
In this first iteration, we have created the basic structure of the Attendee registration micorservice. 

### Key points
***Hexagonal Architecture/Ports and Adapters***: The AttendeeEndpoint is a _Port_ for the registering attendees.  In our case the _Adaper_ is the Jackson library, which is built into Quarkus, and handles converting JSON to Java objects and vice versa.  
The AttendeeEventPubliser is also an Adapter that sends events to Kafka, which is another Port in our architecture.  
The AttendeeRepository is a Port that allows us to persist the AttendeeEntity to a database.

***Aggregates*** Business logic is implemented in an Aggregate, Attendee. The Aggregate is responsible for creating the AttendeeEntity and the AttendeeRegisteredEvent.

***Commands*** we use a Command object, RegisterAttendeeCommand, to encapsulate the data needed to register an attendee.  Commands are different from Events because Commands can fail or be rejected, while Events are statements of fact that have already happened.

***Events*** we use an Event, AttendeeRegisteredEvent, to notify other parts of the system that an attendee has been registered.  Events are statements of fact that have already happened and cannot be changed.






- domain object with a registerAttendee method
-  
  - Create an AttendeeEntity
  -- Create an AttendeeRegistrationEvent
  -- Create a CateringEvent
  -- Create a SwagEvent
- Create an AttendeeRepository
- Create an AttendeeValueObject
- Create a RegisterAttendeeCommand
- Create an AttendeeService object with a registerAttendee method with a RegisterAttendeeCommand signature that calls Attendee.registerAntendee and returns an AttendeeValueObject
-- Send AttendeeRegistrationEvent, CateringEvent, and SwagEvent to Kafka
-- send AttendeeEntity to AttendeeRepository
- Create an AttendeeEndpoint with CRUD methods that call AttendeeService
-- Create an anti corruption layer for Sponsor Attendees
- Create a SponsorAttendeeAdapter that translates a SponsorAttendee
- Create a SponsorsEndpoint with CRUD methods
- Create a CurrencyDomainService to calculate the price in multiple currencies
- Integrate with the Speaker registration
-- react to an incoming SpeakerRegistrationEvent
-- logic so that the speaker is not charged for registration

