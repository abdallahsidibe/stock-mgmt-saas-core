import { MonthlyTrendEntry } from './monthly-trend-entry';

export interface DashboardStatsResponse {
  totalProducts?: number;
  totalCategories?: number;
  totalStockMvts?: number;
  lowStockAlerts?: number;
  newProductsThisMonth?: number;
  newCategoriesThisMonth?: number;
  newStockMvtsThisMonth?: number;
  monthlyTrend?: MonthlyTrendEntry[];
}
