package app.services;

import app.entities.ApplicationUserRole;
import app.repositories.ApplicationUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@Transactional
public class ApplicationUserRoleServiceImpl implements ApplicationUserRoleService {

    private final ApplicationUserRoleRepository applicationUserRoleRepository;

    @Autowired
    public ApplicationUserRoleServiceImpl(ApplicationUserRoleRepository applicationUserRoleRepository) {
        this.applicationUserRoleRepository = applicationUserRoleRepository;
    }

    @Override
    public List<ApplicationUserRole> findAll() {
        return applicationUserRoleRepository.findAll();
    }

    @Override
    public ApplicationUserRole findById(Long id) {
        return applicationUserRoleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("There is no role with id = " + id));
    }

    @Override
    public ApplicationUserRole findApplicationUserRoleByValue(String value) {
        return applicationUserRoleRepository.findApplicationUserRoleByValue(value);
    }

    @Override
    public ApplicationUserRole save(ApplicationUserRole role) {
        applicationUserRoleRepository.save(role);
        return role;

    }

    @Override
    public void delete(Long id) {
        applicationUserRoleRepository.deleteById(id);
    }
}
