package app.services;


import app.entities.User;

import java.util.List;

/**
 * Интерфейс описывающий основные методы над User
 */

public interface UserService {
    /**
     * Поиск всех пользователей
     */
    List<User> findAll();

    /**
     * поиск по идентификатору
     */
    User findUserById(Long id) throws Exception;

    /**
     * Поиск по имени пользователя
     */
    User findUserByFirstName(String name);

    /**
     * Поиск по электронной почте пользователя
     */
    User findUserByEmail(String email);

    /**
     * Сохранение пользователя
     */
    void addUser(User user);

    /**
     * Обновление пользователя
     */
    void updateUser(User user);

    /**
     * Удаление пользователя
     */
    void deleteUser(Long id);

}
