package app.repositories;


import app.entities.AbstractUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Repository для CRUD опираций над сущностью User
 */

@Repository
public interface UserRepository extends JpaRepository<AbstractUser, Long> {
    /**
     * Поиск User по имени пользователя
     */
    AbstractUser findByFirstName(String name);

    /**
     * Поиск User по электронной почте пользователя
     */
    AbstractUser findByEmail(String email);
}
