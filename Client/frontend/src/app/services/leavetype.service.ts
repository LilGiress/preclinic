import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {ILeaveType, LeaveType} from "../models/leaveType";
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

  createLeaveType(type: LeaveTypeRequest): Observable<ILeaveType> {
    return this.http.post<ILeaveType>(Url+`/leave_type`, type,httpOptions);
  }

  // Récupérer la liste des rôles
  getLeaves(): Observable<ILeaveType[]> {
    return this.http.get<ILeaveType[]>(Url+`/leave_type`);
  }
  // 🔹 DELETE: supprimer un type de congé
  deleteLeave(id: number): Observable<void> {
    return this.http.delete<void>(Url+`/leave_type/${id}`);
  }
  // 🔹 PUT: modifier un type de congé existant
  updateLeave(id: number, leavetype: any): Observable<ILeaveType> {
    return this.http.put<ILeaveType>(Url+`/leave_type/${id}`, leavetype,httpOptions);
  }

  // 🔹 PUT: modifier un status existant
  ChangeLeave(id: number, status: any): Observable<ILeaveType> {
    return this.http.put<ILeaveType>(Url+`/leave_type/changeStatus/${id}`, status,httpOptions);
  }

  getLeaveById(id: number): Observable<ILeaveType> {
    return this.http.get<ILeaveType>(Url+`/leave_type/${id}`);
  }
}
