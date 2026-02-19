package com.project.EMS.repository;

import com.project.EMS.entity.SalaryRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryRecordRepository extends JpaRepository<SalaryRecord, Long> {
    List<SalaryRecord> findAllByEmployeeId(Long employeeId);
    SalaryRecord findByEmployeeId(Long employeeId);
   SalaryRecord  findFirstByEmployeeIdOrderByEffectiveDateDesc(Long employeeId);
    Page<SalaryRecord> findByEmployeeIdOrderByEffectiveDateDesc(Long employeeId, Pageable pageable);
}
