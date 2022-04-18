package app.config;

import app.util.SuccessUserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


/**
 * Главный конфигурационный класс Spring Security<br>
 * Мы используем formLogin.<br>
 * Информация о пользователях хранится в БД (daoAuthentificationProvider)<br>
 * Пароли шифруются<br>
 * Ссылки и списки antMatcher'ов могут корректироваться
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final SuccessUserHandler successUserHandler;
    private final UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(PasswordEncoder passwordEncoder, SuccessUserHandler successUserHandler, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.successUserHandler = successUserHandler;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/").permitAll()
                /**
                 * TODO см. ниже
                 * Сюда в паттерны потом нужно будет дописать ссылки на доступ к соответствущим контроллерам
                 */
                .antMatchers("/admin/**", "api/users/**").hasRole("ADMIN")
                .antMatchers("/manager/**").hasRole("AIRLINE_MANAGER")
                .antMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(successUserHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider =
                new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    /**
     * Можем удалить префикс "ROLE_" из названия каждой роли. Пока что не активен.
     */

//        @Bean
//        GrantedAuthorityDefaults grantedAuthorityDefaults() {
//        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
//    }

}
