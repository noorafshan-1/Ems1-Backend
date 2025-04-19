package net.myapp.ems.service.impl;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.myapp.ems.dto.EmployeeDto;
import net.myapp.ems.entity.Employee;
import net.myapp.ems.exception.ResourceNotFoundException;
import net.myapp.ems.mapper.EmployeeMapper;
import net.myapp.ems.repository.EmployeeRepository;
import net.myapp.ems.service.EmployeeService;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		Employee savedEmployee = employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}
	
	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
	    Employee employee = employeeRepository.findById(employeeId)
	        .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
	    return EmployeeMapper.mapToEmployeeDto(employee); // Map to DTO and return

	  
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
	List<Employee> employees = employeeRepository.findAll();
		return employees.stream().map((employee)-> EmployeeMapper.mapToEmployeeDto(employee))
	.collect(Collectors.toList());
	
	}

	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
		
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(
			()-> new ResourceNotFoundException("Employee is not exuists with given id: "  + employeeId)
			);
	employee.setFirstName(updatedEmployee.getFirstName());
	employee.setLastName(updatedEmployee.getLastName());
	employee.setEmail(updatedEmployee.getEmail());
	
	Employee updatedEmployeeObj = employeeRepository.save(employee);
	
	return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
	}

	
	public void deleteEmployee(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(
				()-> new ResourceNotFoundException("Employee is not exuists with given id: "  + employeeId)
				);
		employeeRepository.deleteById(employeeId);
		
		
	}
	

	
	
	
	
	
	
//
//	@Override
//	public EmployeeDto getEmployeeById(Long employeeId) {
//		Employee employee = employeeRepository.findById(employeeId)
//		.orElseThrow(() -> 
//		new ResourceNotFoundException("Employee is not exists with given id:" + employeeId));
//		return EmployeeMapper.mapToEmployeeDto(employee);
//	}

//	@Override
//	public EmployeeDto getEmployeeById(Long employeeId) {
//		employeeRepository.findById(employeeId)
//		.orElseThrow(()-> new ResourceNotFoundException("Employee not found with id: " + employeeId));
//		return null;
//	}
	
//	@Override
//	public EmployeeDto getEmployeeById(Long employeeId) {
//	    Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
//	    if (employeeOptional.isPresent()) {
//	        return EmployeeMapper.mapToEmployeeDto(employeeOptional.get());
//	    } else {
//	        // Handle the case when employee is not found
//	        // You can throw an exception or return a default value
//	        throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
//	    }
//	}
//	
	
}
