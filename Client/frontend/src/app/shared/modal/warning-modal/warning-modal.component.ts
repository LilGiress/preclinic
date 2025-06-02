import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
    selector: 'app-warning-modal',
    imports: [CommonModule],
    templateUrl: './warning-modal.component.html',
    styleUrl: './warning-modal.component.css'
})
export class WarningModalComponent {
  @Input() message: string = ''; // Message d'avertissement
  @Input() title: string = 'Attention'; // Titre du modal
  @Output() close = new EventEmitter<void>(); // Événement pour fermer le modal

  // Méthode pour fermer le modal
  onClose() {
    this.close.emit();
  }

}
