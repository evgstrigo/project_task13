package app.util;

import app.entities.ApplicationUserRole;
import app.services.ApplicationUserRoleService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ApplicationUserRolesCreator {

    private final ApplicationUserRoleService applicationUserRoleService;

    public ApplicationUserRolesCreator(ApplicationUserRoleService applicationUserRoleService) {
        this.applicationUserRoleService = applicationUserRoleService;
    }


    public static void createAllRolesAndSaveInDB(ApplicationUserRoleService applicationUserRoleService) {

        ApplicationUserRole admin = new ApplicationUserRole("ADMIN");

        ApplicationUserRole airline_manager = new ApplicationUserRole("AIRLINE_MANAGER");

        ApplicationUserRole user = new ApplicationUserRole("USER");


        applicationUserRoleService.save(admin);
        applicationUserRoleService.save(airline_manager);
        applicationUserRoleService.save(user);
        System.out.println("Роли пользователей добавлены в БД");
    }

    @Bean("adminRole")
    public ApplicationUserRole getAdminRole() {
       return applicationUserRoleService.findApplicationUserRoleByValue("ADMIN");
    }

    @Bean("airlineManagerRole")
    public ApplicationUserRole getAirlineManagerRole() {
        return applicationUserRoleService.findApplicationUserRoleByValue("AIRLINE_MANAGER");
    }

    @Bean("userRole")
    public ApplicationUserRole getUserRole() {
        return applicationUserRoleService.findApplicationUserRoleByValue("USER");
    }

}
