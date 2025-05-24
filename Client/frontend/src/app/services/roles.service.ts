import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RolesService {
  private apiUrl = 'https://example.com/api/roles';
  constructor(
    private http: HttpClient
  ) { }
  private rolesSubject = new BehaviorSubject<any[]>([
    {
      name: 'Administrator',
      modules: [
        { name: 'Employee', access: true, permission: { read: true, write: true, create: true, delete: true, import: true, export: true } },
        { name: 'Holidays', access: false, permission: { read: false, write: false, create: false, delete: false, import: false, export: false } },
        { name: 'Leave Request', access: false, permission: { read: false, write: false, create: false, delete: false, import: false, export: false } },
        { name: 'Events', access: false, permission: { read: false, write: false, create: false, delete: false, import: false, export: false } },
        { name: 'Chat', access: false, permission: { read: false, write: false, create: false, delete: false, import: false, export: false } }
      ]
    },
    // Autres rôles comme 'Doctor', 'Nurse', etc.
  ]);

  roles$ = this.rolesSubject.asObservable();

  updateRole(role: any): void {
    const roles = this.rolesSubject.getValue();
    const index = roles.findIndex((r) => r.name === role.name);
    if (index !== -1) {
      roles[index] = role;
      this.rolesSubject.next(roles);
    }
  }

  // Méthode pour ajouter un nouveau rôle
  createRole(role: any): Observable<any> {
    return this.http.post(this.apiUrl, role);
  }

  // Récupérer la liste des rôles
  getRoles(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}
