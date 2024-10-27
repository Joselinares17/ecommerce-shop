package org.linaresworks.dream_shops.domain.repository;

import org.linaresworks.dream_shops.domain.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
