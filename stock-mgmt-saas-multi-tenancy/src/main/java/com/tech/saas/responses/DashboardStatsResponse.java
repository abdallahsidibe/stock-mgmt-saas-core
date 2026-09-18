package com.tech.saas.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStatsResponse {

    private long totalProducts;
    private long totalCategories;
    private long totalStockMvts;
    private long lowStockAlerts;
    private long newProductsThisMonth;
    private long newCategoriesThisMonth;
    private long newStockMvtsThisMonth;
    private List<MonthlyTrendEntry> monthlyTrend;
}
