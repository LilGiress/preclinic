import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { IUser } from '../../models/user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../environments/environment';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
const Url = environment.apiUrl;
@Injectable({
  providedIn: 'root'
})
export class EmployeService {

  constructor(private http:HttpClient) { }

  // Add these methods to your auth.service.ts

// CRUD Methods for User Management

getAllUsers(): Observable<IUser[]> {
  return this.http.get<IUser[]>(Url+`/users`, httpOptions);
}

getUserById(id: number): Observable<IUser> {
  return this.http.get<IUser>(Url+`/users/${id}`, httpOptions);
}

createUser(user: IUser): Observable<IUser> {
  return this.http.post<IUser>(Url+`/users`, user, httpOptions);
}

updateUser(id: number, user: IUser): Observable<IUser> {
  return this.http.put<IUser>(Url+`/users/${id}`, user, httpOptions);
}

deleteUser(id: number): Observable<void> {
  return this.http.delete<void>(Url+`/users/${id}`, httpOptions);
}
}
