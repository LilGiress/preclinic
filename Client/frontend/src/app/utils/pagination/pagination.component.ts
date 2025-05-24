import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-pagination',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pagination.component.html',
  styleUrl: './pagination.component.css'
})
export class PaginationComponent {
  @Input() totalItems: number = 0; // Nombre total d'éléments
  @Input() pageSize: number = 10;  // Nombre d'éléments par page
  @Input() currentPage: number = 1; // Page actuelle
  @Output() pageChange = new EventEmitter<number>(); // Événement pour changer la page

  // Obtenir le nombre total de pages
  get totalPages(): number {
    return Math.ceil(this.totalItems / this.pageSize);
  }

  // Aller à la page suivante
  nextPage(): void {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
      this.pageChange.emit(this.currentPage);
    }
  }

  // Aller à la page précédente
  previousPage(): void {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.pageChange.emit(this.currentPage);
    }
  }

  // Changer directement de page
  goToPage(page: number): void {
    if (page >= 1 && page <= this.totalPages) {
      this.currentPage = page;
      this.pageChange.emit(this.currentPage);
    }
  }

}
