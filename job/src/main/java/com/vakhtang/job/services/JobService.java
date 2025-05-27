package com.vakhtang.job.services;

import com.vakhtang.job.clients.CompanyClient;
import com.vakhtang.job.clients.ReviewClient;
import com.vakhtang.job.external.CompanyDTO;
import com.vakhtang.job.external.ReviewDTO;
import com.vakhtang.job.mapper.JobMapper;
import com.vakhtang.job.model.dto.JobDTO;
import com.vakhtang.job.model.entities.JobEntity;
import com.vakhtang.job.repositories.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;

    public JobService(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    public List<JobDTO> findAll() {
        List<JobEntity> jobs = jobRepository.findAll();

        return jobs.stream()
                .map(this::convertToDTO)
                .toList();
    }

    private JobDTO convertToDTO(JobEntity job) {
        CompanyDTO company = companyClient.getCompany(job.getCompanyId());
        List<ReviewDTO> reviews = reviewClient.getReviews(job.getCompanyId());

        return JobMapper.mapToJobWithCompanyDto(job, company, reviews);
    }

    public void createJob(JobEntity job) {
        jobRepository.save(job);
    }

    public JobDTO getJobById(Long id) {
        JobEntity job = jobRepository.findById(id).orElse(null);
        if (job != null)
            return convertToDTO(job);
        return null;
    }

    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateById(Long id, JobEntity updatedJob) {
        Optional<JobEntity> jobOptional = jobRepository.findById(id);

        if (jobOptional.isPresent()) {
            JobEntity job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
