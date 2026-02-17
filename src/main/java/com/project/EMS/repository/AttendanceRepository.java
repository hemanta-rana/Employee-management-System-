package com.project.EMS.repository;

import com.project.EMS.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    boolean existsByEmployeeIdAndAttendanceDate(Long EmployeeId, LocalDate attendanceDate);

}
