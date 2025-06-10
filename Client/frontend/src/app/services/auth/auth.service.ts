import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable, of} from "rxjs";


const httpOptions= {
  headers: new HttpHeaders({'Content-Type': 'application/json' })
}
const AUTH_API= environment.apiUrl;
@Injectable({
  providedIn: 'root'
})
export class AuthService {

 isLoggedIn(): Observable<boolean> {
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

  constructor(private readonly http:HttpClient) { }

  login(data:any):Observable<any>{
    return this.http.post<any>(
      AUTH_API+ '/auth/authenticate',data,httpOptions
    );

  }

  register(data:any):Observable<any>{
    return this.http.post(
      AUTH_API + '/auth/register',data,httpOptions
    );
  }

  logout():Observable<any> {
    return this.http.post(
      AUTH_API + '/auth/logout', {},httpOptions
    )
  }

  activateCode(token:string):Observable<any>{
    return this.http.get(AUTH_API+'/auth/activate-account/'+token,
    );
  }
  ForgotPassword(data:any){
    return this.http.post(AUTH_API+'/user/forgot-password',data,httpOptions);
  }
  changePassword(data:any){
    return this.http.post(AUTH_API+'/user/reset-password',data,httpOptions);
  }

  getUserRole(): string | null {
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
