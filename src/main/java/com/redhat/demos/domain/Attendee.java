package com.redhat.demos.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Attendee {

    @Id
    private Long id;

    String email;

    String name;

    boolean student;

    boolean employee;

    @Enumerated(EnumType.STRING)
    MealPreference mealPreference;

    @Enumerated(EnumType.STRING)
    TShirtSize tShirtSize;

    @OneToOne
    Address address;

    protected static AttendeeRegistrationResult registerAttendee(RegisterAttendeeCommand registerAttendeeCommand) {
        //create the attendee objct
        Attendee attendee = new Attendee();
        attendee.email = registerAttendeeCommand.email();
        attendee.name = registerAttendeeCommand.name();
        attendee.student = registerAttendeeCommand.isStudent();
        attendee.employee = registerAttendeeCommand.isEmployee();

        Double calculatedPrice = attendee.calculatePrice(100.0);
        CateringEvent cateringEvent = new CateringEvent(registerAttendeeCommand.mealPreference());
        SwagEvent swagEvent = new SwagEvent(registerAttendeeCommand.tShirtSize());
        AttendeeRegistrationEvent attendeeRegistrationEvent = new AttendeeRegistrationEvent(attendee.email, calculatedPrice);
        return new AttendeeRegistrationResult(attendee, attendeeRegistrationEvent, swagEvent,cateringEvent);
    }

    public Double calculatePrice(Double basePrice) {
        Double price = basePrice;
        if (student) {
            price = price * 0.7;
        }
        if (employee) {
            price = 0.0;
        }
        return price;
    }

    public Badge generateBadge() {
        UUID uuid = UUID.randomUUID();
        String badgeId = uuid.toString() + " " + name;
        return new Badge(UUID.randomUUID(), name);
    }

    protected void updateAddress(Address address) {
        this.address = address;
    }

    void setId(Long id) {
        this.id = id;
    }

    Long getId() {
        return id;
    }
}
