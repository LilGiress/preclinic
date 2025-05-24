import { Injectable } from '@angular/core';
import { Socket } from 'ngx-socket-io';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(
    private socket:Socket
  ) { }
  getNotifications() {
    return this.socket.fromEvent<string, any>('notifications');
  }
}
