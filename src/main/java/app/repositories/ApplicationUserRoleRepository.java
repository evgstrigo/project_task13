package app.repositories;

import app.entities.ApplicationUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRoleRepository extends JpaRepository<ApplicationUserRole, Long> {

    public ApplicationUserRole findApplicationUserRoleByValue(String value);

}
