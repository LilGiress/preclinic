import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { inject } from '@angular/core';
import { of } from 'rxjs';

export const roleGuard = (expectedRoles: string[]) : CanActivateFn  => {
 return () => {
    const authService = inject(AuthService);
    const router = inject(Router);

    const userRole = authService.getUserRole();

    if (userRole && expectedRoles.includes(userRole)) {
      return of(true);
    } else {
      return of(router.createUrlTree(['/unauthorized']));
    }
  };
};
