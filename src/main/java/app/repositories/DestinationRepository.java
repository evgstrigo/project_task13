package app.repositories;

import app.entities.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * репозиторий пункта вылета/прилёта
 */

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
    Destination findBySity(String sity);
}
