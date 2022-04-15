package app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
