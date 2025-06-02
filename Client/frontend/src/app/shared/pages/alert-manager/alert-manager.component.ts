import {Component, OnInit} from '@angular/core';
import {AlertManagerService} from "../../service/alert-manager.service";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-alert-manager',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './alert-manager.component.html',
  styleUrl: './alert-manager.component.css'
})
export class AlertManagerComponent implements OnInit{
  alerts: any[] = [];

  constructor(private  readonly alertService: AlertManagerService) { }
  ngOnInit(): void {
    this.alertService.getAlerts().subscribe(data => {
      this.alerts = data;
    });
  }

}
