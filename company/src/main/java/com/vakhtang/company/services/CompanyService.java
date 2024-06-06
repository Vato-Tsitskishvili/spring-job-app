package com.vakhtang.company.services;

import com.vakhtang.company.clients.ReviewClient;
import com.vakhtang.company.exceptions.NotFoundException;
import com.vakhtang.company.model.dto.ReviewMessageDTO;
import com.vakhtang.company.model.entities.CompanyEntity;
import com.vakhtang.company.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ReviewClient reviewClient;

    public CompanyService(CompanyRepository companyRepository, ReviewClient reviewClient) {
        this.companyRepository = companyRepository;
        this.reviewClient = reviewClient;
    }

    public List<CompanyEntity> getAllCompanies() {
        return companyRepository.findAll();
    }

    public CompanyEntity getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    public void createCompany(CompanyEntity company) {
        companyRepository.save(company);
    }

    public boolean updateCompany(Long id, CompanyEntity updatedCompany) {
        Optional<CompanyEntity> companyOptional = companyRepository.findById(id);

        if (companyOptional.isPresent()) {
            CompanyEntity company = companyOptional.get();
            company.setName(updatedCompany.getName());
            company.setDescription(updatedCompany.getDescription());
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    public boolean deleteCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void updateCompanyRating(ReviewMessageDTO reviewMessageDTO) {
        try {
            CompanyEntity company = companyRepository.findById(reviewMessageDTO.companyId())
                    .orElseThrow(() -> new NotFoundException("Company not found " + reviewMessageDTO.companyId()));

            double averageRating = reviewClient.getAverageRatingForCompany(reviewMessageDTO.companyId());
            company.setRating(averageRating);
            companyRepository.save(company);

        } catch (NotFoundException e) {
            System.out.println("company not found");
        }
    }
}
