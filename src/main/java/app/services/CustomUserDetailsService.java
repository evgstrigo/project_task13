package app.services;

import app.services.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Класс для использования в Spring Security<br>
 * Позволяет искать Юзера в БД по введённому username<br>
 * В нашем проекте usernamе'ом является поле email в классе AbstractApplicationUser
 */

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final ApplicationUserService applicationUserService;

    @Autowired
    public CustomUserDetailsService(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return applicationUserService.findUserByEmail(s);
    }
}
