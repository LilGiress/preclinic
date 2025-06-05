import { Injectable } from '@angular/core';
import { Socket } from 'ngx-socket-io';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const baseUrl=environment.apiUrl;
const httpOptions= {
  headers: new HttpHeaders({'Content-Type': 'application/json' })
}
@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(
    private readonly socket:Socket,
    private readonly http:HttpClient
  ) { }
  getNotifications() {
    return this.socket.fromEvent<string, any>('notifications');
  }
  getAllNotifications():Observable<any>{
    return this.http.get(baseUrl+'/notifications/search',)

  }

}
