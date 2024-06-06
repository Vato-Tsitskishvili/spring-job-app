package com.vakhtang.job.external;

public record ReviewDTO(
        Long id,
        String title,
        String description,
        double rating
) {
}
