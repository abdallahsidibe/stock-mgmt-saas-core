package com.tech.saas.repositories;

import com.tech.saas.entities.StockMvt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface StockMvtRepository extends JpaRepository<StockMvt, String> {

    Page<StockMvt> findAllByProductId(String productId, Pageable pageable);

    @Query(value = """
            SELECT EXTRACT(YEAR FROM date_mvt)::int  AS year,
                   EXTRACT(MONTH FROM date_mvt)::int AS month,
                   type_mvt,
                   COUNT(*)                          AS cnt
            FROM stock_mvts
            WHERE date_mvt >= :startDate
            GROUP BY EXTRACT(YEAR FROM date_mvt), EXTRACT(MONTH FROM date_mvt), type_mvt
            ORDER BY year, month
            """, nativeQuery = true)
    List<Object[]> findMonthlyTrendSince(@Param("startDate") LocalDate startDate);

    long countByCreatedAtBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
