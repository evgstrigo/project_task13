package app.repositories;

import app.entities.ApplicationUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с ролями пользователей
 */

@Repository
public interface ApplicationUserRoleRepository extends JpaRepository<ApplicationUserRole, Long> {

    /**
     * Метод используется далее сервисом для присваивания каждому новому юзеру уже существующей в БД роли без создания дубликатов ролей<br>
     * @param value ищем роль в БД по её названию ("ADMIN", "AIRLINE_MANAGER", "USER")
     */
    public ApplicationUserRole findFirstByValue(String value);

}
