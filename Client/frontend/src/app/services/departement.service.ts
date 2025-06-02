import { Injectable } from '@angular/core';
import { Departement } from '../models/departments';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';

const baseUrl= environment.apiUrl;
@Injectable({
  providedIn: 'root'
})
export class DepartementService {

 constructor(private  readonly http: HttpClient) {}

  // 🔹 GET : récupérer tous les départements
  getAll(): Observable<Departement[]> {
    return this.http.get<Departement[]>(baseUrl+`/departement`);
  }

  // 🔹 POST : créer un nouveau département
  create(departement: Departement): Observable<Departement> {
    return this.http.post<Departement>(baseUrl, departement);
  }

  // 🔹 PUT : modifier un département
  update(id: number, departement: Departement): Observable<Departement> {
    return this.http.put<Departement>(baseUrl+`/departement/${id}`, departement);
  }

  // 🔹 DELETE : supprimer un département
  delete(id: number): Observable<void> {
    return this.http.delete<void>(baseUrl+`/departement/${id}`);
  }
}
