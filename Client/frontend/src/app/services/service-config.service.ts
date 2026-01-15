import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Services } from '../models/services';
import { Observable } from 'rxjs';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};
const Url = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class ServiceConfigService {


  constructor(private readonly http: HttpClient) { }

  getAll(filters:any): Observable<Services[]> {
     let params=new HttpParams();
    params = params.set('page', filters.page);
    params = params.set('size', filters.size);
    return this.http.get<Services[]>(Url+`/services/all`,{params:params});
  }

  getByDepartmentId(id: number): Observable<Services[]> {
    return this.http.get<Services[]>(Url+`/services/service-departement/${id}`);
  }

  getById(id: number): Observable<Services> {
    return this.http.get<Services>(Url+`/services/${id}`);
  }

  create(service: Services): Observable<Services> {
    return this.http.post<Services>( Url+`/services`, service, httpOptions);
  }

  update(id: number, service: Services): Observable<Services> {
    return this.http.put<Services>(Url+`/services/update/${id}`, service, httpOptions);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(Url+`/services/${id}`, httpOptions);
  }
}
