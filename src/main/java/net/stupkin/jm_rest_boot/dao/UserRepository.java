package net.stupkin.jm_rest_boot.dao;

import net.stupkin.jm_rest_boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u join fetch u.roles where u.email = :email")
    User findUserByEmail(@Param("email")String userName);

    @Query("select distinct u from User u join fetch u.roles")
    List<User> findAll();

    @Query("select u from User u join fetch u.roles where u.id = :id")
    User getById(@Param("id") Long id);
}
