package com.vakhtang.job.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "job_table")
public class JobEntity {

    @Id
    @SequenceGenerator(
            name = "job_id_sequence",
            sequenceName = "job_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "job_id_sequence"
    )
    @Column(name = "job_id")
    private Long id;

    @Column(name = "job_title")
    private String title;

    @Column(name = "job_description")
    private String description;

    @Column(name = "job_min_salary")
    private String minSalary;

    @Column(name = "job_max_salary")
    private String maxSalary;

    @Column(name = "job_location")
    private String location;

    @Column(name = "job_company_id")
    private Long companyId;

    public JobEntity() {}

    public JobEntity(
            Long id,
            String title,
            String description,
            String minSalary,
            String maxSalary,
            String location,
            Long companyId
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.location = location;
        this.companyId = companyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
