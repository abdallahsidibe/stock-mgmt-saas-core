import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { TokenService } from '../../../core/token/token-service';
import { DashboardService } from '../../../api-services/services/dashboard.service';
import { MonthlyTrendEntry } from '../../../api-services/models/monthly-trend-entry';

@Component({
  selector: 'app-statistics',
  imports: [RouterOutlet],
  templateUrl: './statistics.html',
  styleUrl: './statistics.scss',
})
export class Statistics implements OnInit {

  protected totalProducts: number = 0;
  protected totalCategories: number = 0;
  protected totalStockMvts: number = 0;
  protected lowStockAlerts: number = 0;
  protected newProductsThisMonth: number = 0;
  protected newCategoriesThisMonth: number = 0;
  protected newStockMvtsThisMonth: number = 0;
  protected monthlyTrend: MonthlyTrendEntry[] = [];

  constructor(
    private readonly router: Router,
    private readonly tokenService: TokenService,
    private readonly dashboardService: DashboardService,
  ) { }

  ngOnInit(): void {
    this.dashboardService.getStats().subscribe({
      next: (stats) => {
        this.totalProducts = stats.totalProducts ?? 0;
        this.totalCategories = stats.totalCategories ?? 0;
        this.totalStockMvts = stats.totalStockMvts ?? 0;
        this.lowStockAlerts = stats.lowStockAlerts ?? 0;
        this.newProductsThisMonth = stats.newProductsThisMonth ?? 0;
        this.newCategoriesThisMonth = stats.newCategoriesThisMonth ?? 0;
        this.newStockMvtsThisMonth = stats.newStockMvtsThisMonth ?? 0;
        this.monthlyTrend = stats.monthlyTrend ?? [];
      },
    });
  }

  get maxCount(): number {
    if (!this.monthlyTrend.length) return 1;
    return Math.max(...this.monthlyTrend.map(m => Math.max(m.inCount ?? 0, m.outCount ?? 0)), 1);
  }

  barHeightIn(entry: MonthlyTrendEntry): string {
    return Math.round(((entry.inCount ?? 0) / this.maxCount) * 90) + '%';
  }

  barHeightOut(entry: MonthlyTrendEntry): string {
    return Math.round(((entry.outCount ?? 0) / this.maxCount) * 90) + '%';
  }

  get canManageProducts(): boolean {
    return this.tokenService.isCompanyAdmin || this.tokenService.isAdministrator;
  }

  get canManageCategories(): boolean {
    return this.tokenService.isCompanyAdmin || this.tokenService.isAdministrator;
  }

  get canRecordMovement(): boolean {
    return this.tokenService.isCompanyAdmin || this.tokenService.isAdministrator || this.tokenService.isSalesOperator;
  }

  get canManageUsers(): boolean {
    return this.tokenService.isCompanyAdmin || this.tokenService.isAdministrator;
  }

  protected navigateTo(c: string) {
    if (c === 'p') {
      this.router.navigate(['app', 'products']);
    } else if (c === 'c') {
      this.router.navigate(['app', 'categories']);
    } else if (c === 's') {
      this.router.navigate(['app', 'stock-mvts']);
    } else if (c === 'u') {
      this.router.navigate(['app', 'users-list']);
    }
  }
}
