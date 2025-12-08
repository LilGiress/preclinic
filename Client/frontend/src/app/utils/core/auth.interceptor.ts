import { isPlatformBrowser } from '@angular/common';
import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, throwError } from 'rxjs';
import { AuthService } from '../../services/auth/auth.service';


@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private readonly router: Router,
     @Inject(PLATFORM_ID) private readonly platformId: Object,
    private readonly authService: AuthService) {}

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


  // intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
  //   const token = this.authService.getAccessToken();

  //   if (token) {
  //     req = this.addToken(req, token);
  //   }

  //   return next.handle(req).pipe(
  //     catchError(error => {
  //       if (error instanceof HttpErrorResponse && error.status === 401) {
  //         // Token expiré, essayer de le rafraîchir
  //         return this.authService.refreshAccessToken().pipe(
  //           switchMap(newToken => {
  //             if (newToken) {
  //               req = this.addToken(req, newToken);
  //               return next.handle(req);
  //             }
  //             return throwError(() => error);
  //           }),
  //           catchError(() => throwError(() => error))
  //         );
  //       }
  //       return throwError(() => error);
  //     })
  //   );
  // }

  private addToken(req: HttpRequest<any>, token: string): HttpRequest<any> {
    return req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }


}
