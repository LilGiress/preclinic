import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.prod';
import { Observable } from 'rxjs';
import { IEvent } from '../models/event';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
}
const Url = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class EventsService {

  constructor(private http: HttpClient) { }

  

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
