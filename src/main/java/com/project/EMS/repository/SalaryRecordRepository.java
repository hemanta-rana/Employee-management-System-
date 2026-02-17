package com.project.EMS.repository;

import com.project.EMS.entity.SalaryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRecordRepository extends JpaRepository<SalaryRecord, Long> {
}
