import {Component, OnInit} from '@angular/core';
import {NotificationService,Notification} from "../../../services/notification.service";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-notification',
  standalone: true,
  imports: [
    DatePipe
  ],
  templateUrl: './notification.component.html',
  styleUrl: './notification.component.css'
})
export class NotificationComponent implements OnInit{
  notifications: Notification[] = [];
  recipient: string = 'user123'; // Utilisateur concerné

  constructor(private notificationService: NotificationService) {}

  ngOnInit(): void {
    this.loadNotifications();
  }

  // Charger les notifications depuis le backend
  loadNotifications(): void {
    this.notificationService.getNotifications(this.recipient).subscribe(
      (notifications) => {
        this.notifications = notifications;
      },
      (error) => {
        console.error('Erreur lors du chargement des notifications', error);
      }
    );
  }

  // Marquer une notification comme lue
  markAsRead(notification: Notification): void {
    this.notificationService.markAsRead(notification.id).subscribe(() => {
      notification.read = true;
    });
  }
}
