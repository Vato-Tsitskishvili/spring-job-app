package com.vakhtang.review.model.dto;

public record ReviewMessageDTO(
        Long id,
        String title,
        String description,
        double rating,
        Long companyId
) {
}
