import { Component } from '@angular/core';
import {TopNavbarComponent} from "../../navbar/top-navbar/top-navbar.component";
import {RouterOutlet} from "@angular/router";
import {FooterComponent} from "../../navbar/footer/footer.component";

@Component({
  selector: 'app-skeletor',
  standalone: true,
  imports: [
    TopNavbarComponent,
    RouterOutlet,
    FooterComponent
  ],
  templateUrl: './skeletor.component.html',
  styleUrl: './skeletor.component.css'
})
export class SkeletorComponent {

}
