package com.vakhtang.company.messaging;

import com.vakhtang.company.model.dto.ReviewMessageDTO;
import com.vakhtang.company.services.CompanyService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReviewMessageConsumer {

    private final CompanyService companyService;

    public ReviewMessageConsumer(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessageDTO reviewMessageDTO) {
        companyService.updateCompanyRating(reviewMessageDTO);
    }
}
