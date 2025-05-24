import { CanActivateFn } from '@angular/router';
import {AuthService} from "../services/auth/auth.service";
import {inject} from "@angular/core";
import {Router} from "express";
import {catchError, map, of} from "rxjs";

export const authGuard: CanActivateFn = (route, state) => {
  const authservice = inject(AuthService);
  const router = inject(Router);

  return authservice.isLoggedIn().pipe(
    map(loggedIn => loggedIn ? true : router.createUrlTree([router.parseUrl('/login')], {
      queryParams: { loggedOut: true, origUrl: state.url }
    } )),
    catchError((err) => {
      router.navigate(['/login'], {
        queryParams: { loggedOut: true, origUrl: state.url }
      });
      return of(false);
    })
  );
};
