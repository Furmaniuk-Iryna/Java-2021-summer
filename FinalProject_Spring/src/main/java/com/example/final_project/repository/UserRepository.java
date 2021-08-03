package com.example.final_project.repository;

import com.example.final_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
