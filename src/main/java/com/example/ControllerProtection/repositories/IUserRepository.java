package com.example.ControllerProtection.repositories;

import com.example.ControllerProtection.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
