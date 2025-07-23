import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { IRole,Role } from '../models/role';


  const Url = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class RolesService {

  constructor(
    private  readonly http: HttpClient
  ) { }
 

  // Méthode pour ajouter un nouveau rôle
  createRole(role: any): Observable<any> {
    return this.http.post(Url+`/roles/create`, role);
  }

  // Récupérer la liste des rôles
  getRoles(): Observable<IRole[]> {
    return this.http.get<IRole[]>(Url+`/roles/all`);
  }
   // 🔹 DELETE: supprimer un rôle
  deleteRole(id: number): Observable<void> {
    return this.http.delete<void>(Url+`/roles/${id}`);
  }
   // 🔹 PUT: modifier un rôle existant
  updateRole(id: number, role: Role): Observable<Role> {
    return this.http.put<Role>(Url+`/roles/update/${id}`, role);
  }
}
