package com.example.demo_security_17.respository;



import com.example.demo_security_17.model.AppUser;
import com.example.demo_security_17.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleResposiotry extends JpaRepository<UserRole,Long> {
    List<UserRole> findByAppUser(AppUser appUser);
}
