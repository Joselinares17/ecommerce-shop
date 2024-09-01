package org.linaresworks.dream_shops.domain.repository;

import org.linaresworks.dream_shops.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
