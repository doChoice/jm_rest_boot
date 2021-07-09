package net.stupkin.jm_rest_boot.service;


import net.stupkin.jm_rest_boot.entity.Role;
import net.stupkin.jm_rest_boot.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void saveUser(User user);
    void updateUser(User user);
    User getUserById(Long id);
    void deleteUser(Long id);
    User getUserByUsername(String userName);
    Role getRoleById(Long id);
    List<Role> getAllRoles();
}
