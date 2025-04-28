package com.redhat.demos.persistence;

import com.redhat.demos.domain.AddressValueObject;
import com.redhat.demos.domain.Badge;
import com.redhat.demos.domain.MealPreference;
import com.redhat.demos.domain.TShirtSize;
import jakarta.persistence.*;

@Entity @Table(name = "attendee")
public class AttendeeEntity {

    @Id @GeneratedValue
    private Long id;

    private String email;

    private String name;

    private boolean student;

    private boolean employee;

    @Enumerated(EnumType.STRING)
    private MealPreference mealPreference;

    @Enumerated(EnumType.STRING)
    private TShirtSize tShirtSize;

    @OneToOne
    private AddressEntity address;

    @OneToOne
    private BadgeEntity badge;

    protected AttendeeEntity() {

    }

    public AttendeeEntity(Badge badge, String email, String name, boolean student, boolean employee, MealPreference mealPreference, TShirtSize tShirtSize, AddressValueObject address) {
        this.badge = new BadgeEntity(badge.badgeNumber(), badge.email());
        this.email = email;
        this.name = name;
        this.student = student;
        this.employee = employee;
        this.mealPreference = mealPreference;
        this.tShirtSize = tShirtSize;
        this.address = new AddressEntity(
                address.street(),
                address.street2(),
                address.city(),
                address.stateOrProvince(),
                address.postCode(),
                address.country());
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public boolean isStudent() {
        return student;
    }

    public boolean isEmployee() {
        return employee;
    }

    public MealPreference getMealPreference() {
        return mealPreference;
    }

    public TShirtSize gettShirtSize() {
        return tShirtSize;
    }

    public AddressEntity getAddress() {
        return address;
    }
}
