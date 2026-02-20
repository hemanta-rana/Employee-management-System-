package com.project.EMS.dto.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentPageResponse {
    private List<DepartmentResponse> departmentResponses;
    private long page;
    private long size;
    private  long totalElements;
    private long totalPage;
    private boolean isLast;
}
