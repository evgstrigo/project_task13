package app.services;

import app.entities.ApplicationUserRole;

/**
 * Интерфейс для работы с ApplicationUserRole
 */

import java.util.List;

public interface ApplicationUserRoleService {

    List<ApplicationUserRole> findAll();

    ApplicationUserRole findById(Long id);

    /**
     * Используется для присваивания каждому новому юзеру уже существующей в БД роли без создания дубликатов ролей<br>
     * @param value ищем роль в БД по её названию ("ADMIN", "AIRLINE_MANAGER", "USER")
     */
    ApplicationUserRole findFirstByValue(String value);

    ApplicationUserRole save(ApplicationUserRole role);

    void delete(Long id);

}
