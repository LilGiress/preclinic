import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ModalContainerComponent } from "./shared/modal/modal-container/modal-container.component";
import { NgxSpinnerModule } from 'ngx-spinner';
import { AuthService } from './services/auth/auth.service';

@Component({
    selector: 'app-root',
    imports: [RouterOutlet, ModalContainerComponent,NgxSpinnerModule],
    templateUrl: './app.component.html',
    styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
  loading:string ="assets/img/Loading.gif";
}
