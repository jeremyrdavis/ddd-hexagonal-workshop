package com.redhat.demos.persistence;


import com.redhat.demos.domain.AttendeeDomainObject;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class AttendeeRepository implements PanacheRepository<AttendeeEntity> {

    public void findByName(String name) {
    }

    public void updateAttendee(AttendeeDomainObject attendee) {
    }
}
