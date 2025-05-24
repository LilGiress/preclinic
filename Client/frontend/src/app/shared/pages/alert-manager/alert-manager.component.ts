import {Component, OnInit} from '@angular/core';
import {AlertManagerService} from "../../service/alert-manager.service";

@Component({
  selector: 'app-alert-manager',
  standalone: true,
  imports: [],
  templateUrl: './alert-manager.component.html',
  styleUrl: './alert-manager.component.css'
})
export class AlertManagerComponent implements OnInit{
  alerts: any[] = [];

  constructor(private alertService: AlertManagerService) { }
  ngOnInit(): void {
    this.alertService.getAlerts().subscribe(data => {
      this.alerts = data;
    });
  }

}
