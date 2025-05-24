import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../../services/notification/notification.service';
import { InventoryService } from '../../services/inventory-service/inventory.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {
  prescriptions: any[] = [];
  stock: any[] = [];
  notifications: string[] = [];

  constructor(
    private pharmacyService: InventoryService,
     private notificationService: NotificationService
  ){}

  ngOnInit(): void {
    this.loadData();
    this.listenForNotifications();
  }

  loadData() {
    this.pharmacyService.getPrescriptions().subscribe(data => this.prescriptions = data);
    this.pharmacyService.getStock().subscribe(data => this.stock = data);
  }

  listenForNotifications() {
    this.notificationService.getNotifications().subscribe((message: string) => {
      this.notifications.push(message);
    });
  }

}
