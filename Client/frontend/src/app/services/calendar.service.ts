import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.prod';
import { Observable } from 'rxjs';
import { ICalendar } from '../models/calendar';
import { IEvent } from '../models/event';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
}
const Url = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class CalendarService {

  constructor(private http: HttpClient) { }

  getCalendars(): Observable<ICalendar[]> {
    return this.http.get<ICalendar[]>(Url);
  }

  getCalendar(id: number): Observable<ICalendar> {
    return this.http.get<ICalendar>(`${Url}/${id}`);
  }

  createCalendar(calendar: ICalendar): Observable<ICalendar> {
    return this.http.post<ICalendar>(Url, calendar);
  }

  updateCalendar(id: number, calendar: ICalendar): Observable<ICalendar> {
    return this.http.put<ICalendar>(`${Url}/${id}`, calendar);
  }

  deleteCalendar(id: number): Observable<void> {
    return this.http.delete<void>(`${Url}/${id}`);
  }

   // ---- Events CRUD ----
   getAllEvents(): Observable<IEvent[]> {
    return this.http.get<IEvent[]>(`${Url}/events`);
  }

   addEvent(event: IEvent): Observable<IEvent> {
    return this.http.post<IEvent>(`${Url  }/events`, event);
  }
  
  updateEvent(id: number, event: IEvent): Observable<IEvent> {
    return this.http.put<IEvent>(`${Url}/events/${id}`, event);
  }
  
  deleteEvent(id: number): Observable<void> {
    return this.http.delete<void>(`${Url}/events/${id}`);
  }

}
