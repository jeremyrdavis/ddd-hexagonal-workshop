package dddhexagonalworkshop.conference.attendees.persistence;

import dddhexagonalworkshop.conference.attendees.api.AddressDTO;
import dddhexagonalworkshop.conference.attendees.domain.valueobjects.Badge;
import jakarta.persistence.*;

@Entity @Table(name = "attendee")
public class AttendeeEntity {

    @Id @GeneratedValue
    private Long id;

    private String email;

    private String firstName;

    private  String lastName;

    private boolean student;

    private boolean employee;

    @OneToOne
    private AddressEntity address;

    @OneToOne
    private BadgeEntity badge;

    protected AttendeeEntity() {

    }

    public AttendeeEntity(Badge badge, String email, String firstName, String lastName, boolean student, boolean employee, AddressDTO address) {
        this.badge = new BadgeEntity(badge.badgeNumber(), badge.email());
        this.email = email;
        this.firstName = firstName;
        this.student = student;
        this.employee = employee;
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

    public String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public boolean isStudent() {
        return student;
    }

    public boolean isEmployee() {
        return employee;
    }

    public AddressEntity getAddress() {
        return address;
    }
}
