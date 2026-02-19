package com.project.EMS.dto.ResponseDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryHistoryPageResponse {
    private List<SalaryRecordResponse> content;
    private int page;
    private int size;
    private  long totalElements;
    private int totalPages;
    private boolean last;

}
