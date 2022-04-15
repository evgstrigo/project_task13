package app.repositories;


import app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Repository для CRUD опираций над сущностью User
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Поиск User по имени пользователя
     */
    User findByFirstName(String name);

    /**
     * Поиск User по электронной почте пользователя
     */
    User findByEmail(String email);
}
