import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { TokenService } from '../../token/token-service';

export const tenantAdminGuard: CanActivateFn = async (route, state) => {
  const tokenService = inject(TokenService);
  const router = inject(Router);
  if (!tokenService.isCompanyAdmin && !tokenService.isAdministrator) {
    await router.navigate(['app']);
    return false;
  }
  return true;
};
