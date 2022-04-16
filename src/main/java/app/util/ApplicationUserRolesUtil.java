package app.util;

import app.entities.ApplicationUserRole;
import app.services.ApplicationUserRoleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Утилитный класс для работы с ролями пользователя для Spring Security.
 */


@Component
public class ApplicationUserRolesUtil {

    private ApplicationUserRoleService applicationUserRoleService;

    public ApplicationUserRolesUtil(ApplicationUserRoleService applicationUserRoleService) {
        this.applicationUserRoleService = applicationUserRoleService;
    }


    /**
     * Метод создаёт 3 роли (админ, менеджер и юзер) и сохраняет их в отдельной таблице в БД
     * @param applicationUserRoleService
     */
    public static void createAllRolesAndSaveInDB(ApplicationUserRoleService applicationUserRoleService) {

        ApplicationUserRole admin = new ApplicationUserRole("ADMIN");

        ApplicationUserRole airline_manager = new ApplicationUserRole("AIRLINE_MANAGER");

        ApplicationUserRole user = new ApplicationUserRole("USER");


        applicationUserRoleService.save(admin);
        applicationUserRoleService.save(airline_manager);
        applicationUserRoleService.save(user);

        System.out.println("Роли пользователей добавлены в БД");
    }


}
