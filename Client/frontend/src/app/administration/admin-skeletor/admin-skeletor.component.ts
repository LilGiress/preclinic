import { Component } from '@angular/core';
import { AdminStylComponent } from "../admin-styl/admin-styl.component";
import { SettingNavbarComponent } from "../../shared/navbar/admin/setting-navbar/setting-navbar.component";
import { LeftSidebarAdminComponent } from "../../shared/navbar/admin/left-sidebar-admin/left-sidebar-admin.component";
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-admin-skeletor',
  standalone: true,
  imports: [AdminStylComponent, SettingNavbarComponent, LeftSidebarAdminComponent,RouterOutlet],
  templateUrl: './admin-skeletor.component.html',
  styleUrl: './admin-skeletor.component.css'
})
export class AdminSkeletorComponent {

}
