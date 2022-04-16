package app.controllers;

import app.entities.AbstractUser;
import app.services.ApplicationUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @RestController это API для создания, получения, обновления и удаления данных пользователя
 */

@RestController
@RequestMapping("api/users")
@Api(tags = "User Controller")
public class UserController {

    private final ApplicationUserService applicationUserService;

    @Autowired
    public UserController(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @ApiOperation("Get all Users")
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<AbstractUser> allUsersFromDb = applicationUserService.findAll();
        return allUsersFromDb != null
                ? new ResponseEntity<>(allUsersFromDb, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation("Get User by id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) throws Exception {
        AbstractUser user = applicationUserService.findUserById(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation("Create User")
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody AbstractUser user) {
        applicationUserService.addUser(user);
        AbstractUser userFromDb = applicationUserService.findUserByFirstName(user.getFirstName());
        return userFromDb != null
                ? new ResponseEntity<>(userFromDb, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation("Update User")
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody AbstractUser user) throws Exception {
        applicationUserService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("Delete User by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) throws Exception {
        applicationUserService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
