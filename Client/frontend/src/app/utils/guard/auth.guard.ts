import { CanActivateFn } from '@angular/router';
import {inject} from "@angular/core";
import {Router} from "express";
import {catchError, map, of} from "rxjs";
import { AuthService } from '../../services/auth/auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authservice = inject(AuthService);
  const router = inject(Router);
  
  return authservice.isLoggedIn().pipe(
    map(loggedIn => {
      if (loggedIn) {
        return true;
      } else {
        return router.createUrlTree(['/login'], {
          queryParams: {
            loggedOut: true,
            origUrl: state.url
          }
        });
      }
    }),
    catchError(() => {
      return of(router.createUrlTree(['/login'], {
        queryParams: {
          loggedOut: true,
          origUrl: state.url
        }
      }));
    })
  );
};
