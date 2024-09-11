package org.linaresworks.dream_shops.domain.repository;

import org.linaresworks.dream_shops.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByProductId(Long id);
}
