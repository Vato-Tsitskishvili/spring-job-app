package com.vakhtang.job.clients;

import com.vakhtang.job.external.ReviewDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "review", url = "${review.url}")
public interface ReviewClient {

    @GetMapping("/reviews")
    List<ReviewDTO> getReviews(@RequestParam("companyId") Long id);
}
