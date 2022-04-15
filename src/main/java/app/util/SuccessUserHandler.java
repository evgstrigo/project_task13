package app.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_USER")) {
            System.out.println("Сработал редирект на /user");
            httpServletResponse.sendRedirect("/user");
        } else if (roles.contains("ROLE_ADMIN")) {
            System.out.println("Сработал редирект на /admin");
            httpServletResponse.sendRedirect("/admin");
        } else if (roles.contains("ROLE_AIRLINE_MANAGER")) {
            System.out.println("Сработал редирект на /manager");
            httpServletResponse.sendRedirect("/manager");
        }
        else  {
            httpServletResponse.sendRedirect("/");
        }
    }
}
