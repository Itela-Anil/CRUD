package com.spring.datajpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.datajpa.model.Employee;
import com.spring.datajpa.repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	/*@GetMapping("/")
	public List<Employee> getAllEmployee(){
		List<Employee> employee=  employeeRepository.findAll();
		return employee;
	}*/
	
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@PostMapping("/employee/add")
	public void addEmployee(@RequestBody Employee employee) {
		 employeeRepository.save(employee);
	}
	//localhost:8080/employee
	@GetMapping("/employee")
	public List<Employee>getAllEmployee(){
		List<Employee> employee=  employeeRepository.findAll();
		return employee;
	}
	
	@GetMapping("/employee/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable int  id){
		return employeeRepository.findById(id);
	}
	
	@PutMapping("/employee/update/{id}")
	public Employee updateEmployeeById(@RequestBody Employee inputEmployee){
		Optional<Employee> employee = employeeRepository.findById(inputEmployee.getId());
		Employee updatedEmployee = employee.get();
		updatedEmployee.setEmployeeName(inputEmployee.getEmployeeName());
		updatedEmployee.setDesignation(inputEmployee.getDesignation());
		updatedEmployee.setExperience(inputEmployee.getExperience());
		return employeeRepository.save(updatedEmployee);
	}
	
	@DeleteMapping("/employee/{id}")
	public void deleteEmployeeById(@PathVariable int id){
		Employee employee=employeeRepository.findById(id).get();
		employeeRepository.delete(employee);
	}
	
}
