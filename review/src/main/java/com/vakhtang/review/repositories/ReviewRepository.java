package com.vakhtang.review.repositories;

import com.vakhtang.review.model.entities.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    @Query("SELECT r FROM ReviewEntity r WHERE r.companyId = ?1")
    List<ReviewEntity> findByCompanyId(Long companyId);
}
