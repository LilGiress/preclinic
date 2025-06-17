import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from "../../../environments/environment";
import { BehaviorSubject, catchError, map, Observable, of, switchMap, tap, throwError } from "rxjs";
import { User } from '../../models/user';
import { isPlatformBrowser } from '@angular/common';
import { Router } from '@angular/router';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
}
const AUTH_API = environment.apiUrl;
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly currentUserSubject = new BehaviorSubject<any>(null);
  public currentUser$ = this.currentUserSubject.asObservable();
  constructor(private readonly http: HttpClient,
    @Inject(PLATFORM_ID) private readonly platformId: Object,
    private readonly route: Router
  ) {
    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('token');
      if (token) {
        try {
          const payload = JSON.parse(atob(token.split('.')[1]));
         // this.currentUserSubject.next(payload); // préremplir l’utilisateur courant
        } catch { }
      }
    }
  }

  

  isLoggedIn(): Observable<boolean> {
    if (!isPlatformBrowser(this.platformId)) {
      return of(false); // Server-side: treat as not logged in
    }
    const token = localStorage.getItem('token'); // get token from local storage
    if (!token) {
      return of(false);  // Return Observable with false if no token
    }

    try {
      const payload = atob(token.split('.')[1]); // decode payload of token
      const parsedPayload = JSON.parse(payload); // convert payload into an Object
      const isTokenValid = parsedPayload.exp > Date.now() / 1000; // check if token is expired
      return of(isTokenValid);
    } catch (error) {
      return of(false);
    }
  }

  login(data: any): Observable<any> {
  return this.http.post<{ access_token: string }>(AUTH_API + '/auth/authenticate', data, httpOptions).pipe(
    switchMap((response) => {
      if (!isPlatformBrowser(this.platformId)) {
        return throwError(() => new Error('Exécution côté serveur : localStorage indisponible'));
      }

      const token = response?.access_token;
      if (!token) {
        return throwError(() => new Error('Token manquant dans la réponse'));
      }

      // Stockage du token
      localStorage.setItem('token', token);

      // Décodage simple du JWT (juste pour lecture immédiate)
      //const payload = JSON.parse(atob(token.split('.')[1]));
      //this.currentUserSubject.next(payload); // émet un utilisateur minimal (id, email, role...)

      // Requête pour récupérer l'utilisateur complet
      return this.curentUser(token).pipe(
        map((userList: User[]) => {
          const user = userList[0]; // ou adapter si l’API retourne un seul objet
          this.currentUserSubject.next(user); // mettre à jour avec l'utilisateur complet
          return user;
        })
      );
    }),
    catchError(error => {
      console.error('Erreur lors de la connexion', error);
      return throwError(() => error);
    })
  );
}




 /* login(data: any): Observable<any> {
    return this.http.post<any>(AUTH_API + '/auth/authenticate', data, httpOptions).pipe(
      tap((response: { access_token: string; }) => {
        if (isPlatformBrowser(this.platformId)) {
          const token = response?.access_token;
          if (!token) {
            console.error('Token non trouvé dans la réponse du serveur');
            return;
          }

          localStorage.setItem('token', response.access_token); // stocker le token
          const payload = JSON.parse(atob(response.access_token.split('.')[1]));
          this.currentUserSubject.next(payload);// notifier tous les abonnés (navbar, etc.)
        }
      }),
      catchError(error => {
        // Optionnel : tu peux logger ici
        console.error('Erreur lors de la connexion', error);
        return throwError(() => error); // renvoyer l’erreur au composant
      })
    );
  }*/

  register(data: any): Observable<any> {
    return this.http.post(
      AUTH_API + '/auth/register', data, httpOptions
    );
  }

  logout(token: string): void {
  const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
  this.http.post(AUTH_API + '/auth/logout', {}, { headers }).subscribe({
    next: () => {
      if (isPlatformBrowser(this.platformId)) {
        localStorage.removeItem('token');
      }
      this.currentUserSubject.next(null); // Réinitialiser l'utilisateur

      this.route.navigate(['/login'], {
        queryParams: { loggedOut: true }
      });
    },
    error: (err) => {
      console.error('Erreur lors de la déconnexion', err);
      // Optionnel : tu peux gérer une redirection même en cas d'erreur
      this.currentUserSubject.next(null);
      if (isPlatformBrowser(this.platformId)) {
        localStorage.removeItem('token');
      }
      this.route.navigate(['/login'], {
        queryParams: { loggedOut: true, error: true }
      });
    }
  });
}



  activateCode(token: string): Observable<any> {
    return this.http.get(AUTH_API + '/auth/activate-account/' + token,
    );
  }
  ForgotPassword(data: any) {
    return this.http.post(AUTH_API + '/user/forgot-password', data, httpOptions);
  }
  changePassword(data: any) {
    return this.http.post(AUTH_API + '/user/reset-password', data, httpOptions);
  }

  curentUser(data: any): Observable<User[]> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${data}`);
    return this.http.get<User[]>(AUTH_API + '/user/current-user',{ headers });
  }

  getUserRole(): string | null {
    if (!isPlatformBrowser(this.platformId)) {
      return null; // Server-side: treat as not logged in
    }
    const token = localStorage.getItem('token');
    if (!token) return null;

    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload.role ?? null;
    } catch (e) {
      return null;
    }
  }

}
