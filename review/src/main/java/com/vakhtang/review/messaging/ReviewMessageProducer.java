package com.vakhtang.review.messaging;

import com.vakhtang.review.model.dto.ReviewMessageDTO;
import com.vakhtang.review.model.entities.ReviewEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(ReviewEntity review) {
        ReviewMessageDTO reviewMessageDTO = new ReviewMessageDTO(
                review.getId(),
                review.getTitle(),
                review.getDescription(),
                review.getRating(),
                review.getCompanyId()
        );

        rabbitTemplate.convertAndSend("companyRatingQueue", reviewMessageDTO);
    }
}
