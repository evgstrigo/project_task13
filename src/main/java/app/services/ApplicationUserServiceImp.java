package app.services;


import app.entities.AbstractUser;
import app.entities.Admin;
import app.entities.AirlineManager;
import app.entities.User;
import app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;



@Service
@Transactional
public class ApplicationUserServiceImp implements ApplicationUserService {

    private final UserRepository userRepository;
    private final ApplicationUserRoleService applicationUserRoleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserServiceImp(UserRepository userRepository,
                                     ApplicationUserRoleService applicationUserRoleService,
                                     PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.applicationUserRoleService = applicationUserRoleService;
        this.passwordEncoder = passwordEncoder;
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
    public void addUser(AbstractUser abstractUser) {
        if (abstractUser instanceof Admin) {
            abstractUser
                    .setRole(applicationUserRoleService.findApplicationUserRoleByValue("ADMIN"));
        }

        if (abstractUser instanceof AirlineManager) {
            abstractUser
                    .setRole(applicationUserRoleService.findApplicationUserRoleByValue("AIRLINE_MANAGER"));
        }

        if (abstractUser instanceof User) {
            abstractUser
                    .setRole(applicationUserRoleService.findApplicationUserRoleByValue("USER"));
        }

        String rawPassword = abstractUser.getPassword();
        abstractUser.setPassword(passwordEncoder.encode(rawPassword));

        userRepository.save(abstractUser);
    }

    @Override
    public void updateUser(AbstractUser abstractUser) {
        if (!abstractUser.getPassword().equals("")) {
            String rawPassword = abstractUser.getPassword();
            abstractUser.setPassword(passwordEncoder.encode(rawPassword));
        }
        userRepository.save(abstractUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
