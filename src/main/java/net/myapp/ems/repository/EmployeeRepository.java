package net.myapp.ems.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.myapp.ems.entity.Employee;


@Repository
//@Transactional(readOnly = true)
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
