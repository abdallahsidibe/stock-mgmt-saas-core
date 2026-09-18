package com.tech.saas.services.impl;

import com.tech.saas.repositories.CategoryRepository;
import com.tech.saas.repositories.ProductRepository;
import com.tech.saas.repositories.StockMvtRepository;
import com.tech.saas.responses.DashboardStatsResponse;
import com.tech.saas.responses.MonthlyTrendEntry;
import com.tech.saas.services.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final StockMvtRepository stockMvtRepository;

    @Override
    public DashboardStatsResponse getStats() {
        final long totalProducts = this.productRepository.count();
        final long totalCategories = this.categoryRepository.count();
        final long totalStockMvts = this.stockMvtRepository.count();
        final long lowStockAlerts = this.productRepository.countLowStockProducts();

        final LocalDateTime monthStart = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        final LocalDateTime now = LocalDateTime.now();
        final long newProductsThisMonth = this.productRepository.countByCreatedAtBetween(monthStart, now);
        final long newCategoriesThisMonth = this.categoryRepository.countByCreatedAtBetween(monthStart, now);
        final long newStockMvtsThisMonth = this.stockMvtRepository.countByCreatedAtBetween(monthStart, now);

        final LocalDate sixMonthsAgo = LocalDate.now().minusMonths(5).withDayOfMonth(1);
        final List<Object[]> rows = this.stockMvtRepository.findMonthlyTrendSince(sixMonthsAgo);

        final List<MonthlyTrendEntry> monthlyTrend = buildTrend(sixMonthsAgo, rows);

        return DashboardStatsResponse.builder()
                                     .totalProducts(totalProducts)
                                     .totalCategories(totalCategories)
                                     .totalStockMvts(totalStockMvts)
                                     .lowStockAlerts(lowStockAlerts)
                                     .newProductsThisMonth(newProductsThisMonth)
                                     .newCategoriesThisMonth(newCategoriesThisMonth)
                                     .newStockMvtsThisMonth(newStockMvtsThisMonth)
                                     .monthlyTrend(monthlyTrend)
                                     .build();
    }

    private List<MonthlyTrendEntry> buildTrend(final LocalDate from, final List<Object[]> rows) {
        // Initialize the last 6 months with zero counts
        final Map<String, MonthlyTrendEntry> trendMap = new LinkedHashMap<>();
        for (int i = 0; i < 6; i++) {
            final LocalDate month = from.plusMonths(i);
            final String key = month.getYear() + "-" + month.getMonthValue();
            trendMap.put(key, MonthlyTrendEntry.builder()
                                               .year(month.getYear())
                                               .month(month.getMonthValue())
                                               .monthLabel(Month.of(month.getMonthValue())
                                                                .getDisplayName(TextStyle.SHORT, Locale.ENGLISH))
                                               .inCount(0)
                                               .outCount(0)
                                               .build());
        }

        // Fill with actual data
        for (final Object[] row : rows) {
            final int year = ((Number) row[0]).intValue();
            final int month = ((Number) row[1]).intValue();
            final String typeMvt = (String) row[2];
            final long count = ((Number) row[3]).longValue();
            final String key = year + "-" + month;

            if (trendMap.containsKey(key)) {
                final MonthlyTrendEntry entry = trendMap.get(key);
                if ("IN".equals(typeMvt)) {
                    entry.setInCount(count);
                } else {
                    entry.setOutCount(count);
                }
            }
        }

        return new ArrayList<>(trendMap.values());
    }
}
