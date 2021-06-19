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
        return userService.getAllRoles();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User addNewUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @PatchMapping()
    public User updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

//    @GetMapping()
//    public String showAllUsers(@ModelAttribute("newUser") User newUser, Principal principal, Model model) {
//        List<User> allUsers = userService.getAllUsers();
//        List<Role> allRoles = userService.getAllRoles();
//        User authorizedUser = userService.getUserByUsername(principal.getName());
//        model.addAttribute("allUsers",allUsers);
//        model.addAttribute("allRoles", allRoles);
//        model.addAttribute("authorizedUser", authorizedUser);
//        return "admin";
//    }
//
//    @PostMapping()
//    public String addNewUser(@ModelAttribute("user") @Valid User user,
//                             @RequestParam (value ="roles", required = false) Long[] rolesId) {
//
//        Set<Role> roles = new HashSet<>();
//        for (Long id : rolesId) {
//            roles.add(userService.getRoleById(id));
//        }
//        user.setRoles(roles);
//        userService.saveUser(user);
//        return "redirect:/admin";
//    }
//
//
//    @PatchMapping("/{id}")
//    public String updateUser(@ModelAttribute("user") @Valid User user,
//                             @RequestParam (value ="roles", required = false) Long[] rolesId){
//
//        Set<Role> roles = new HashSet<>();
//        for (Long id : rolesId) {
//            roles.add(userService.getRoleById(id));
//        }
//        user.setRoles(roles);
//        userService.updateUser(user);
//        return "redirect:/admin";
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteUser(@PathVariable("id") Long id) {
//        userService.deleteUser(id);
//        return "redirect:/admin";
//    }
}
