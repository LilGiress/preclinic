import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
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
    console.log('Leave object :', leave);
    return this.http.post<Leave>(Url+`/leaves/create`, leave,httpOptions);
  }

  // Récupérer la liste des leaves
  getLeaves(filters:any): Observable<Leave[]> {
    let params=new HttpParams();
    params = params.set('page', filters.page);
    params = params.set('size', filters.size);
    if (filters.employeeName) {
    params = params.set('employeeName', filters.employeeName);
  }
  if (filters.leaveType) {
    params = params.set('leaveType', filters.leaveType);
  }
  if (filters.leaveStatus) {
    params = params.set('leaveStatus', filters.leaveStatus);
  }
  if (filters.startDate) {
    params = params.set('startDate', filters.startDate);
  }
  if (filters.endDate) {
    params = params.set('endDate', filters.endDate);
  }
    return this.http.get<Leave[]>(Url+`/leaves/search`,{params:params});
  }
  // 🔹 DELETE: supprimer un leave
  deleteLeaves(id: number): Observable<void> {
    return this.http.delete<void>(Url+`/leaves/${id}`);
  }
  // 🔹 PUT: modifier un leave existant
  updateLeaves(id: number, leave: Leave): Observable<Leave> {
    return this.http.put<Leave>(Url+`/leaves/${id}`, leave);
  }

  changeLeaveStatus(id: number, status: string): Observable<Leave> {
    return this.http.put<Leave>(Url+`/leaves/changeStatus/${id}`,  status,httpOptions);
  }
}
