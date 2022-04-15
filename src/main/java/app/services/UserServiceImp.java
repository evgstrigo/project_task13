package app.services;


import app.entities.AbstractUser;
import app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Конкретная реализация сервиса User
 */

@Service
@Transactional
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<AbstractUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public AbstractUser findUserById(Long id) throws Exception {
        Optional<AbstractUser> userFromDb = userRepository.findById(id);
        return userFromDb.orElseThrow(() -> new Exception("User with id :" + id + " not found"));
    }

    @Override
    public AbstractUser findUserByFirstName(String name) {
        return userRepository.findByFirstName(name);
    }

    @Override
    public AbstractUser findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void addUser(AbstractUser user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(AbstractUser user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
