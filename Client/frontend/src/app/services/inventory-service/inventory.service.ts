import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InventoryService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(
    private http: HttpClient
  ) { }

  getPrescriptions(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/prescriptions`);
  }

  getStock(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/stock`);
  }
  getStockHistory(startDate?: string, endDate?: string, medicine?: string): Observable<any[]> {
    let params = new HttpParams();
    if (startDate) params = params.set('startDate', startDate);
    if (endDate) params = params.set('endDate', endDate);
    if (medicine) params = params.set('medicine', medicine);

    return this.http.get<any[]>(`${this.apiUrl}/history`, { params });
  }
  getAllMedicines(): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiUrl}/medicines`);
  }

}
