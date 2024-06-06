package com.vakhtang.job.mapper;

import com.vakhtang.job.external.CompanyDTO;
import com.vakhtang.job.external.ReviewDTO;
import com.vakhtang.job.model.dto.JobDTO;
import com.vakhtang.job.model.entities.JobEntity;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobWithCompanyDto(
            JobEntity job,
            CompanyDTO company,
            List<ReviewDTO> reviews
    ) {
        return new JobDTO(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getMinSalary(),
                job.getMaxSalary(),
                job.getLocation(),
                company,
                reviews
        );
    }
}
