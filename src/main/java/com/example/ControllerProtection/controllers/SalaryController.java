package com.example.ControllerProtection.controllers;

import com.example.ControllerProtection.entities.Salary;
import com.example.ControllerProtection.repositories.ISalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/salaries")
public class SalaryController {
	@Autowired
	private ISalaryRepository salaryRepository;
	
	@Secured("ROLE_ADMIN")
	@GetMapping
	public List<Salary> getAllSalaries() {
		return salaryRepository.findAll();
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/{id}")
	public Salary getSalaryById(@PathVariable Long id) {
		return salaryRepository.findById(id).get();
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping
	public Salary postSalary(@RequestBody Salary salary) {
		return salaryRepository.save(salary);
	}
}
