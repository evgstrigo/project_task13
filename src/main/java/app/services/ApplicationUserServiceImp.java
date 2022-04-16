package app.services;


import app.entities.*;
import app.entities.AbstractApplicationUser;
import app.entities.User;
import app.repositories.AbstractApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Реализация интерфейса ApplicationUserService<br>
 * Несколько методов этого класса имеют свои особенности (см. ниже)
 */

@Service
@Transactional
public class ApplicationUserServiceImp implements ApplicationUserService {

    private final AbstractApplicationUserRepository abstractApplicationUserRepository;
    private final ApplicationUserRoleService applicationUserRoleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserServiceImp(AbstractApplicationUserRepository abstractApplicationUserRepository,
                                     ApplicationUserRoleService applicationUserRoleService,
                                     PasswordEncoder passwordEncoder) {
        this.abstractApplicationUserRepository = abstractApplicationUserRepository;
        this.applicationUserRoleService = applicationUserRoleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<AbstractApplicationUser> findAll() {
        return abstractApplicationUserRepository.findAll();
    }

    @Override
    public AbstractApplicationUser findUserById(Long id) throws Exception {
        Optional<AbstractApplicationUser> userFromDb = abstractApplicationUserRepository.findById(id);
        return userFromDb.orElseThrow(() -> new Exception("User with id : " + id + " not found"));
    }


    @Override
    public AbstractApplicationUser findUserByEmail(String email) {
        return abstractApplicationUserRepository.findByEmail(email);
    }

    /**
     * В этом методе перед сохранением в БД нового юзера мы добавляем ему роль.<br>
     * Роль зависит от типа ApplicationUser (Admin, AirlineManager, User).<br>
     * Также здесь происходит шифрование "сырого" пароля
     * @param abstractApplicationUser
     */
    @Override
    public void addUser(AbstractApplicationUser abstractApplicationUser) {
        if (abstractApplicationUser instanceof Admin) {
            abstractApplicationUser
                    .setRole(applicationUserRoleService.findFirstByValue("ADMIN"));
        }

        if (abstractApplicationUser instanceof AirlineManager) {
            abstractApplicationUser
                    .setRole(applicationUserRoleService.findFirstByValue("AIRLINE_MANAGER"));
        }

        if (abstractApplicationUser instanceof User) {
            abstractApplicationUser
                    .setRole(applicationUserRoleService.findFirstByValue("USER"));
        }

        String rawPassword = abstractApplicationUser.getPassword();
        abstractApplicationUser.setPassword(passwordEncoder.encode(rawPassword));

        abstractApplicationUserRepository.save(abstractApplicationUser);
    }

    /**
     * В этом методе перед сохранением в БД обновлённого юзера происходит шифрование "сырого" пароля. <br>
     * Если в форме на фронте пароль изменялся (значение поля не осталось пустым), <br>
     * обновлённый ("сырой") пароль будет зашифрован и сохранён в БД.
     * @param abstractApplicationUser
     */

    @Override
    public void updateUser(AbstractApplicationUser abstractApplicationUser) {
        if (!abstractApplicationUser.getPassword().equals("")) {
            String rawPassword = abstractApplicationUser.getPassword();
            abstractApplicationUser.setPassword(passwordEncoder.encode(rawPassword));
        }
        abstractApplicationUserRepository.save(abstractApplicationUser);
    }

    @Override
    public void deleteUser(Long id) {
        abstractApplicationUserRepository.deleteById(id);
    }
}
