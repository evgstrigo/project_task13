package app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Используется для Spring Security.<br>
 * Здесь мы настраиваем отображение определённых view в зависимости от ссылки,
 * по которой переходит пользователь<br>
 * "Стандартные" редиректы прописаны в классе Util.SuccessUserHandler
 */

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user").setViewName("AfterLoginUserRedirect");
        registry.addViewController("/admin").setViewName("AfterLoginAdminRedirect");
        registry.addViewController("/manager").setViewName("AfterLoginManagerRedirect");
        registry.addViewController("/").setViewName("index");
    }
}
