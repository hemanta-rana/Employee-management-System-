package com.project.EMS.repository;

import com.project.EMS.entity.Department;
import com.project.EMS.projection.DepartmentWithEmployeeCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Page<Department> findAll(Pageable pageable);
    @Query("""
    SELECT d.id AS id,
           d.departmentName AS departmentName,
           d.isActive AS isActive,
           d.createdAt AS createdAt,
           COUNT(e.id) AS employeeCount
    FROM Department d
    LEFT JOIN Employee e ON e.department.id = d.id
    GROUP BY d.id, d.departmentName, d.isActive, d.createdAt
""")
    Page<DepartmentWithEmployeeCount> findAllWithEmployeeCount(Pageable pageable);

}
