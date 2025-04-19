package net.myapp.ems.service;

import java.util.List;

import net.myapp.ems.dto.EmployeeDto;

public interface EmployeeService {

	EmployeeDto createEmployee(EmployeeDto employeeDto);
	EmployeeDto getEmployeeById(Long employeeId);

	List<EmployeeDto> getAllEmployees();
	
	EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmploye);
	
	void deleteEmployee(Long employeeId); 




}
