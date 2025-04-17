package com.redhat.demos.domain;


import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class AttendeeRepository implements PanacheRepository<Attendee> {

    public void findByName(String name) {
    }

    public void updateAttendee(Attendee attendee) {
    }
}
