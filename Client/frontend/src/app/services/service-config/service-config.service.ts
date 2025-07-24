import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Services } from '../../models/services';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
}
const baseUrl= environment.apiUrl;
@Injectable({
  providedIn: 'root'
})
export class ServiceConfigService {

  constructor(private  readonly http: HttpClient) { }

  // 🔹 GET : récupérer tous les Services
    getAll(): Observable<Services[]> {
      return this.http.get<Services[]>(baseUrl+`/services/all`);
    }

    // 🔹 GET : récupérer tous les Services
    getByID(): Observable<Services[]> {
      return this.http.get<Services[]>(baseUrl+`/services/all`);
    }
  
    // 🔹 POST : créer un nouveau Services
    create(departement: Services): Observable<Services> {
      return this.http.post<Services>(baseUrl, departement,httpOptions);
    }
  
    // 🔹 PUT : modifier un Services
    update(id: number, departement: Services): Observable<Services> {
      return this.http.put<Services>(baseUrl+`/services/update`, departement,httpOptions);
    }
  
    // 🔹 DELETE : supprimer un Services
    delete(id: number): Observable<void> {
      return this.http.delete<void>(baseUrl+`/services/delete`,httpOptions);
    }
}
