package net.stupkin.jm_rest_boot.dao;

import net.stupkin.jm_rest_boot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {

}
