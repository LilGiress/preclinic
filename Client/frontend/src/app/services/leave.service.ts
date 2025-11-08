import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {LeaveType} from "../models/leaveType";
import {Leave} from "../models/leaves";
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
}
const Url = environment.apiUrl;
@Injectable({
  providedIn: 'root'
})
export class LeaveService {

  constructor(
    private  readonly http: HttpClient
  ) { }

  createLeave(leave: any): Observable<Leave> {
    return this.http.post<Leave>(Url+`/leaves/create`, leave,httpOptions);
  }

  // Récupérer la liste des leaves
  getLeaves(): Observable<Leave[]> {
    return this.http.get<Leave[]>(Url+`/leaves`);
  }
  // 🔹 DELETE: supprimer un leave
  deleteLeaves(id: number): Observable<void> {
    return this.http.delete<void>(Url+`/leaves/${id}`);
  }
  // 🔹 PUT: modifier un leave existant
  updateLeaves(id: number, leave: Leave): Observable<Leave> {
    return this.http.put<Leave>(Url+`/leaves/${id}`, leave);
  }

}
