import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiConfiguration } from '../api-configuration';
import { DashboardStatsResponse } from '../models/dashboard-stats-response';

@Injectable({ providedIn: 'root' })
export class DashboardService {

  constructor(
    private readonly http: HttpClient,
    private readonly config: ApiConfiguration,
  ) {}

  getStats(): Observable<DashboardStatsResponse> {
    return this.http.get<DashboardStatsResponse>(`${this.config.rootUrl}/api/v1/dashboard/stats`);
  }
}
