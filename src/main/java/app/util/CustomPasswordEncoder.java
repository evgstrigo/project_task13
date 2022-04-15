package app.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Класс-обёртка для создания бина PasswordEncoder.<br>
 * Содержит внутри себя BCryptPasswordEncoder(10) <br>
 * Если хотим изменить тип энкодера на другой, меняем здесь в конструкторе
 */
@Component
public class CustomPasswordEncoder implements PasswordEncoder {

    private final PasswordEncoder passwordEncoder;


    protected CustomPasswordEncoder() {
        this.passwordEncoder = new BCryptPasswordEncoder(10);
    }

    @Override
    public String encode(CharSequence charSequence) {
        return passwordEncoder.encode(charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return passwordEncoder.matches(charSequence, s);
    }
}