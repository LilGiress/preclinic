import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Holiday } from '../models/holidays';
import { Observable } from 'rxjs/internal/Observable';
import { environment } from '../../environments/environment';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
const Url = environment.apiUrl;
@Injectable({
  providedIn: 'root'
})
export class HolidayService {

  constructor(private http: HttpClient) { }

   // READ - Get all holidays
  getAll(): Observable<Holiday[]> {
    return this.http.get<Holiday[]>(Url+`/holidays`, httpOptions);
  }

  // READ - Get single holiday
  getById(id: number): Observable<Holiday> {
    return this.http.get<Holiday>(Url+`/holidays/${id}`, httpOptions);
  }

  // CREATE - Add new holiday
  create(holiday: Holiday): Observable<Holiday> {
    return this.http.post<Holiday>(Url+`/holidays`, holiday, httpOptions);
  }

  // UPDATE - Edit holiday
  update(id: number, holiday: Holiday): Observable<Holiday> {
    return this.http.put<Holiday>(Url+`holidays/${id}`, holiday, httpOptions);
  }

  // DELETE - Remove holiday
  delete(id: number): Observable<void> {
    return this.http.delete<void>(Url+`holidays/${id}`, httpOptions);
  }

  getHolidaysByDateRange(startDate: Date, endDate: Date): Observable<Holiday[]> {
    const start = startDate.toISOString();
    const end = endDate.toISOString();
    return this.http.get<Holiday[]>(Url+`/holidays/date/?start=${start}`, httpOptions);
  }

  VerifyHolidayDate(date: Date): Observable<{ isHoliday: boolean }> {
    const dateString = date.toISOString();
    return this.http.get<{ isHoliday: boolean }>(`${Url}/holidays/isHoliday/date=${dateString}`, httpOptions);
  }


  // NEW METHODS

  /**
   * Get holidays from external API (Calendarific or similar)
   * Requires API key: https://calendarific.com/
   */
  getHolidaysByYear(year: number, countryCode: string): Observable<any[]> {
    const apiKey = 'YOUR_API_KEY'; // Get from https://calendarific.com/
    const url = `https://calendarific.com/api/v2/holidays?api_key=${apiKey}&country=${countryCode}&year=${year}`;
    return this.http.get<any[]>(url);
  }

  /**
   * Bulk create multiple holidays
   */
  bulkCreate(holidays: Holiday[]): Observable<Holiday[]> {
    return this.http.post<Holiday[]>(`${Url}/bulk`, holidays, httpOptions);
  }

  /**
   * Get holidays for a specific year from your database
   */
  getHolidaysByYearFromDB(year: number): Observable<Holiday[]> {
    return this.http.get<Holiday[]>(`${Url}/year/${year}`, httpOptions);
  }
}
