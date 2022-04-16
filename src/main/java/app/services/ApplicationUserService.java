package app.services;


import app.entities.AbstractApplicationUser;

import java.util.List;

/**
 * Интерфейс, описывающий основные методы над ApplicationUser
 */

public interface ApplicationUserService {
    /**
     * Поиск всех пользователей
     */
    List<AbstractApplicationUser> findAll();

    /**
     * поиск по идентификатору
     */
    AbstractApplicationUser findUserById(Long id) throws Exception;


    /**
     * Поиск по электронной почте пользователя
     */
    AbstractApplicationUser findUserByEmail(String email);

    /**
     * Сохранение пользователя
     */
    void addUser(AbstractApplicationUser user);

    /**
     * Обновление пользователя
     */
    void updateUser(AbstractApplicationUser user);

    /**
     * Удаление пользователя
     */
    void deleteUser(Long id);

}
