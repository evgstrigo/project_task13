package app.repositories;


import app.entities.AbstractApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с ролями пользователей
 *//**
 * @Repository для CRUD опираций над сущностью User
 */

@Repository
public interface AbstractApplicationUserRepository extends JpaRepository<AbstractApplicationUser, Long> {


    /**
     * Поиск User по электронной почте пользователя.<br>
     * Будет использоваться в имплементации UserDetailsService для реализации метода loadUserByUsername()
     */
    AbstractApplicationUser findByEmail(String email);
}
