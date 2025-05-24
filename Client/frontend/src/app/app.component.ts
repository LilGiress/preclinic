import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ModalContainerComponent } from "./shared/modal/modal-container/modal-container.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ModalContainerComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
}
