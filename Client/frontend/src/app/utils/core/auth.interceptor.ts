import { isPlatformBrowser } from '@angular/common';
import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, throwError } from 'rxjs';


@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private router: Router, @Inject(PLATFORM_ID) private platformId: Object) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

     let modifiedReq = req;

    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('token');
      const fingerprint = localStorage.getItem('fingerprint'); // Si tu gères l'empreinte côté client

      if (token) {
        modifiedReq = modifiedReq.clone({
          setHeaders: {
            Authorization: `Bearer ${token}`,
            ...(fingerprint ? { Fingerprint: fingerprint } : {})
          }
        });
      }
    }

    return next.handle(modifiedReq).pipe(
      catchError((error: HttpErrorResponse) => {
        if (
          isPlatformBrowser(this.platformId) &&
          error.status === 401 &&
          typeof error.error === 'string' &&
          error.error.includes('Session désactivée')
        ) {
          localStorage.removeItem('token');
          localStorage.removeItem('fingerprint'); // Si nécessaire
          this.router.navigate(['/login'], {
            queryParams: { expired: true }
          });
        }
        return throwError(() => error);
      })
    );


    // return next.handle(req).pipe(
    //   catchError((error: HttpErrorResponse) => {
    //     if ((error.status === 401 || error.status === 403) && isPlatformBrowser(this.platformId)) {
    //       // Déconnexion côté frontend
    //       localStorage.removeItem('token'); // Si vous stockez un token
    //       // Rediriger vers la page de connexion
    //       this.router.navigate(['/login'], {
    //         queryParams: { expired: true }
    //       });
    //     }
    //     return throwError(() => error);
    //   })
    // );
  }
}
