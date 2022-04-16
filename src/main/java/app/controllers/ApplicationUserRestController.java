package app.controllers;

import app.entities.AbstractApplicationUser;
import app.services.ApplicationUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  API для создания, получения, обновления и удаления данных пользователя<br>
 *  При необходимости будет также сделан контроллер для ролей.
 *  Шифрование пароля осуществляется в сервисе.
 */

@RestController
@RequestMapping("api/users")
@Api(tags = "User Controller")
@Log4j2
public class ApplicationUserRestController {

    private final ApplicationUserService applicationUserService;

    @Autowired
    public ApplicationUserRestController(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @ApiOperation("Get all Users")
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        log.info("getAllUsers started");
        List<AbstractApplicationUser> allUsersFromDb = applicationUserService.findAll();
        return allUsersFromDb != null
                ? new ResponseEntity<>(allUsersFromDb, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation("Get User by id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) throws Exception {
        log.info("getUserById started");
        AbstractApplicationUser user = applicationUserService.findUserById(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation("Create User")
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody AbstractApplicationUser user) {
        log.info("createUser started");
        applicationUserService.addUser(user);
        AbstractApplicationUser userFromDb = applicationUserService.findUserByEmail(user.getEmail());
        return userFromDb != null
                ? new ResponseEntity<>(userFromDb, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation("Update User")
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody AbstractApplicationUser user) throws Exception {
        log.info("updateUser started");
        applicationUserService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("Delete User by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) throws Exception {
        log.info("deleteUser started");
        applicationUserService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
