package net.stupkin.jm_rest_boot.controller;


import net.stupkin.jm_rest_boot.entity.User;
import net.stupkin.jm_rest_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping()
public class UserRestController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/authorizedUser")
    public User showUserById(Principal principal) {
        User authorizedUser = userService.getUserByUsername(principal.getName());
        return authorizedUser;
    }
}
