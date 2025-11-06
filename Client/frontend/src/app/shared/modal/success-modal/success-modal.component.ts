import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';


@Component({
    selector: 'app-success-modal',
    imports: [CommonModule],
    templateUrl: './success-modal.component.html',
    styleUrl: './success-modal.component.css'
})
export class SuccessModalComponent {
  @Input() message: string = 'Opération réussie !';
  @Output() closeSuccessModal = new EventEmitter<void>();
    // Action personnalisée sur fermeture
  @Output() afterClose = new EventEmitter<void>();
  image='';


  closeModal() {
    this.closeSuccessModal.emit();
    this.afterClose.emit();        // action personnalisée (ex: redirection)
  }

}
