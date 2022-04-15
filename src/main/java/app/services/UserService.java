package app.services;


import app.entities.AbstractUser;

import java.util.List;

/**
 * Интерфейс описывающий основные методы над User
 */

public interface UserService {
    /**
     * Поиск всех пользователей
     */
    List<AbstractUser> findAll();

    /**
     * поиск по идентификатору
     */
    AbstractUser findUserById(Long id) throws Exception;

    /**
     * Поиск по имени пользователя
     */
    AbstractUser findUserByFirstName(String name);

    /**
     * Поиск по электронной почте пользователя
     */
    AbstractUser findUserByEmail(String email);

    /**
     * Сохранение пользователя
     */
    void addUser(AbstractUser user);

    /**
     * Обновление пользователя
     */
    void updateUser(AbstractUser user);

    /**
     * Удаление пользователя
     */
    void deleteUser(Long id);

}
