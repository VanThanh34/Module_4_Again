package com.example.baitap.repository;


import com.example.baitap.entity.AppUser;
import com.example.baitap.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleResposiotry extends JpaRepository<UserRole,Long> {
    List<UserRole> findByAppUser(AppUser appUser);
}
