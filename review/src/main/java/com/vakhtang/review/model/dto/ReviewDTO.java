package com.vakhtang.review.model.dto;

public record ReviewDTO(
        Long id,
        String title,
        String description,
        double rating,
        Long companyId
) {
}
