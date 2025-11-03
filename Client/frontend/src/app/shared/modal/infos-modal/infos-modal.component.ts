import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-infos-modal',
  standalone: true,
  imports: [],
  templateUrl: './infos-modal.component.html',
  styleUrl: './infos-modal.component.css'
})
export class InfosModalComponent {
  @Input() message: string = 'Opération réussie !';
  @Output() closeSuccessModal = new EventEmitter<void>();
  // Action personnalisée sur fermeture
  @Output() afterClose = new EventEmitter<void>();


  closeModal() {
    this.closeSuccessModal.emit();
    this.afterClose.emit();        // action personnalisée (ex: redirection)
  }

}
