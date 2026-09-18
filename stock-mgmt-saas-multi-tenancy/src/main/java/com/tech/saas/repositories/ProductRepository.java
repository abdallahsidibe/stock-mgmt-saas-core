package com.tech.saas.repositories;

import com.tech.saas.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {

    Optional<Product> findByReferenceIgnoreCase(String reference);

    @Query(value = """
            SELECT COUNT(*) FROM products p
            WHERE (
                SELECT COALESCE(SUM(CASE WHEN s.type_mvt = 'IN' THEN s.quantity ELSE -s.quantity END), 0)
                FROM stock_mvts s
                WHERE s.product_id = p.id
            ) <= p.alert_threshold
            """, nativeQuery = true)
    long countLowStockProducts();

    long countByCreatedAtBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
