package com.project.EMS.repository;

import com.project.EMS.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Long countByDepartmentId(Long departmentId);
}
