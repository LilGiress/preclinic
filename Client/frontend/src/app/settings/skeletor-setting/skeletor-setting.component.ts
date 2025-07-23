import { AfterViewInit, Component, Inject, OnInit, PLATFORM_ID, Renderer2 } from '@angular/core';
import { AdminStylComponent } from "../../administration/admin-styl/admin-styl.component";
import { SettingNavbarComponent } from "../../shared/navbar/admin/setting-navbar/setting-navbar.component";
import { LeftSittingSidebarComponent } from "../../shared/navbar/admin/left-sitting-sidebar/left-sitting-sidebar.component";
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-skeletor-setting',
  standalone: true,
  imports: [AdminStylComponent, SettingNavbarComponent, LeftSittingSidebarComponent,RouterOutlet],
  templateUrl: './skeletor-setting.component.html',
  styleUrl: './skeletor-setting.component.css'
})
export class SkeletorSettingComponent implements AfterViewInit,OnInit{
  constructor(
    
  ){}
  ngOnInit(): void {
   

  }


  ngAfterViewInit(): void {
   
  }

}
