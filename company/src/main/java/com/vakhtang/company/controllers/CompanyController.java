package com.vakhtang.company.controllers;

import com.vakhtang.company.model.entities.CompanyEntity;
import com.vakhtang.company.services.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<CompanyEntity>> getAllCompanies() {
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyEntity> getCompanyById(@PathVariable("id") Long id) {
        CompanyEntity company = companyService.getCompanyById(id);
        if (company != null)
            return new ResponseEntity<>(company, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody CompanyEntity company) {
        companyService.createCompany(company);
        return new ResponseEntity<>("Company added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(
        @PathVariable("id") Long id,
        @RequestBody CompanyEntity company
    ) {
        boolean companyUpdated = companyService.updateCompany(id, company);
        if (companyUpdated)
            return new ResponseEntity<>("Company updated successfully", HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable("id") Long id) {
        boolean companyDeleted = companyService.deleteCompany(id);

        if (companyDeleted)
            return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);

        return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
    }
}
