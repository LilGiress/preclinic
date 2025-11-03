import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {LeaveType} from "../models/leaveType";
import {LeaveTypeRequest} from "../models/playload/LeaveTypeRequest";


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
}
const Url = environment.apiUrl;
@Injectable({
  providedIn: 'root'
})
export class LeavetypeService {

  constructor(
    private  readonly http: HttpClient
  ) { }

  create(leavetype: LeaveTypeRequest): Observable<LeaveType> {
    return this.http.post<LeaveType>(Url+`/leavetype/create`, leavetype,httpOptions);
  }

  // Récupérer la liste des rôles
  getLeaves(): Observable<LeaveType[]> {
    return this.http.get<LeaveType[]>(Url+`/leavetype`);
  }
  // 🔹 DELETE: supprimer un rôle
  deleteLeave(id: number): Observable<void> {
    return this.http.delete<void>(Url+`/leavetype/${id}`);
  }
  // 🔹 PUT: modifier un rôle existant
  updateLeave(id: number, leavetype: LeaveType): Observable<LeaveType> {
    return this.http.put<LeaveType>(Url+`/leavetype/${id}`, leavetype);
  }
}
