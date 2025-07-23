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

  login(data: any): Observable<{ token: string, user: User[] }> {
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

      // Requête pour récupérer l'utilisateur complet
      return this.curentUser(token).pipe(
        map((userList: User[]) => {
          
           if (!userList || userList.length === 0) {
             throw new Error('Aucun utilisateur retourné');
           }
          const user = userList; 
          this.currentUserSubject.next(userList); // mettre à jour avec l'utilisateur complet
          return { token, user };
        })
      );
    }),
    catchError(error => {
      console.error('Erreur lors de la connexion', error);
      return throwError(() => error);
    })
  );
}

  register(data: any): Observable<any> {
    return this.http.post(
      AUTH_API + '/auth/register', data, httpOptions
    );
  }

  logout(): void {
  //const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
  this.http.post(AUTH_API + '/auth/logout', {}, { 
    //withCredentials:true,
   // responseType: 'text'
   }).subscribe({
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



  activateCode(token: string): Observable<string> {
    return this.http.get<string>(AUTH_API + '/auth/activate-account/' + token,
    );
  }
  ForgotPassword(data: any): Observable<User[]> {
    return this.http.post<User[]>(AUTH_API + '/user/forgot-password', data, httpOptions);
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

resentToken(token:string):Observable<any>{
   return this.http.post(
      AUTH_API + '/auth/resent-active-token/', token, httpOptions
    );
}


}
