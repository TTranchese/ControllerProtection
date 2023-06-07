package com.example.ControllerProtection.repositories;

import com.example.ControllerProtection.entities.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISalaryRepository extends JpaRepository<Salary, Long> {
}
