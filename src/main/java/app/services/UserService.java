package app.services;


import app.entities.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findUserById(Long id) throws Exception;
    User findUserByFirstName(String name);
    User findUserByEmail(String email);
    void addUser(User user);
    void updateUser(Long id, User user);
    void deleteUser(Long id);

}
