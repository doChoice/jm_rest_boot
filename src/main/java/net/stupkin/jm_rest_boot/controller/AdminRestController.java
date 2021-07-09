package net.stupkin.jm_rest_boot.controller;


import net.stupkin.jm_rest_boot.entity.Role;
import net.stupkin.jm_rest_boot.entity.User;
import net.stupkin.jm_rest_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User showUser(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        List<Role> roles = userService.getAllRoles();
        System.out.println(roles);
        return roles;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User addNewUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @PutMapping()
    public User updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

}
