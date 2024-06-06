package com.vakhtang.job.model.dto;

import com.vakhtang.job.external.CompanyDTO;
import com.vakhtang.job.external.ReviewDTO;

import java.util.List;

public record JobDTO(
        Long id,
        String title,
        String description,
        String minSalary,
        String maxSalary,
        String location,
        CompanyDTO company,
        List<ReviewDTO> reviews
) {
}
