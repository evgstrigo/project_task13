package app.services;

import app.entities.ApplicationUserRole;
import app.entities.Passenger;

import java.util.List;

public interface ApplicationUserRoleService {


    List<ApplicationUserRole> findAll();


    ApplicationUserRole findById(Long id);

    ApplicationUserRole findApplicationUserRoleByValue(String value);


    ApplicationUserRole save(ApplicationUserRole role);


    void delete(Long id);

}
