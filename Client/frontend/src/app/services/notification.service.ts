import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
export interface Notification {
  id: number;
  recipient: string;
  type: string;
  message: string;
  read: boolean;
  createdAt: string;
}
@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private apiUrl = 'http://localhost:8080/api/notifications'; // URL de l'API

  constructor(private http: HttpClient) {}

  // Récupérer les notifications d'un utilisateur
  getNotifications(recipient: string): Observable<Notification[]> {
    return this.http.get<Notification[]>(`${this.apiUrl}/${recipient}`);
  }

  // Marquer une notification comme lue
  markAsRead(notificationId: number): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${notificationId}/mark-as-read`, {});
  }
}
