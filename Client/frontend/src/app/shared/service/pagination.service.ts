import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PaginationService {

  constructor() { }
    // Variables globales de pagination
    pageSize: number = 10; // Nombre d'éléments par page
    currentPage: number = 1; // Page actuelle
  
    // Calcule les éléments affichés selon la page courante
    getPaginatedData<T>(data: T[]): T[] {
      const startIndex = (this.currentPage - 1) * this.pageSize;
      const endIndex = startIndex + this.pageSize;
      return data.slice(startIndex, endIndex);
    }
  
    // Obtenir le nombre total de pages
    getTotalPages(totalItems: number): number {
      return Math.ceil(totalItems / this.pageSize);
    }
  
    // Passer à la page suivante
    nextPage(totalItems: number): void {
      const totalPages = this.getTotalPages(totalItems);
      if (this.currentPage < totalPages) {
        this.currentPage++;
      }
    }
  
    // Revenir à la page précédente
    previousPage(): void {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    }
  
    // Changer la taille de la page (optionnel)
    setPageSize(size: number): void {
      this.pageSize = size;
      this.currentPage = 1; // Réinitialiser à la première page
    }
}
