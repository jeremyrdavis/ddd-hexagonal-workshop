package dddhexagonalworkshop.conference.attendees.persistence;

import dddhexagonalworkshop.conference.attendees.domain.AttendeeDomainObject;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class AttendeeRepository implements PanacheRepository<AttendeeEntity> {

    public void findByName(String name) {
    }

    public Optional<AttendeeEntity> findByEmail(String email) {
        return Optional.ofNullable(find("email", email).firstResult());
    }
}
