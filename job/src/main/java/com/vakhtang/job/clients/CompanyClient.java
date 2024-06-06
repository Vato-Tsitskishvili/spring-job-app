package com.vakhtang.job.clients;

import com.vakhtang.job.external.CompanyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "company", url = "${company.url}")
public interface CompanyClient {

    @GetMapping("/companies/{id}")
    CompanyDTO getCompany(@PathVariable("id") Long id);
}
