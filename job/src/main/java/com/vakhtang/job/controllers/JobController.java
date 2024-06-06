package com.vakhtang.job.controllers;

import com.vakhtang.job.model.dto.JobDTO;
import com.vakhtang.job.model.entities.JobEntity;
import com.vakhtang.job.services.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobDTO>> findAll() {
        return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody JobEntity job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(
            @PathVariable("id") Long id
    ) {
        JobDTO job = jobService.getJobById(id);
        if (job != null)
            return new ResponseEntity<>(job, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(
            @PathVariable("id") Long id
    ) {
        boolean jobDeleted = jobService.deleteJobById(id);
        if (jobDeleted)
            return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(
            @PathVariable("id") Long id,
            @RequestBody JobEntity updatedJob
    ) {
        boolean jobUpdated = jobService.updateById(id, updatedJob);
        if (jobUpdated)
            return new ResponseEntity<>("Job successfully updated", HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
