package com.example.demo_security_17.respository;


import com.example.demo_security_17.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUserName(String username);
}
