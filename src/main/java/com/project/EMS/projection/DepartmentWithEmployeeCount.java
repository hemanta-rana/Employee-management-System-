package com.project.EMS.projection;

import java.time.LocalDateTime;

public interface DepartmentWithEmployeeCount {
    Long getId();
    String getDepartmentName();
    Boolean getIsActive();
    LocalDateTime getCreatedAt();
    Long getEmployeeCount();
}
