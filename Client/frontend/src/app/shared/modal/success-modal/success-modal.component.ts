import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-success-modal',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './success-modal.component.html',
  styleUrl: './success-modal.component.css'
})
export class SuccessModalComponent {
  @Input() message: string = 'Opération réussie !';
  @Output() closeSuccessModal = new EventEmitter<void>();

  closeModal() {
    this.closeSuccessModal.emit();
  }

}
