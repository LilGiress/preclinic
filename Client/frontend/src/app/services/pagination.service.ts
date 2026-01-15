import { Injectable } from '@angular/core';
import { PaginationConfig } from '../models/pagination/pagination-config';
import { PaginationState } from '../models/pagination/pagination-state';

@Injectable({
  providedIn: 'root'
})
export class PaginationService {

  constructor() { }
    private defaultConfig: PaginationConfig = {
    currentPage: 1,
    itemsPerPage: 10,
    totalItems: 0,
    pageSizeOptions: [10, 25, 50, 100]
  };

  /**
   * Calculer l'état de pagination
   */
  calculatePaginationState(config: PaginationConfig): PaginationState {
    const totalPages = Math.ceil(config.totalItems / config.itemsPerPage);
    const currentPage = Math.min(config.currentPage, totalPages) || 1;
    
    const startIndex = (currentPage - 1) * config.itemsPerPage;
    const endIndex = Math.min(startIndex + config.itemsPerPage, config.totalItems);
    
    const startItem = config.totalItems > 0 ? startIndex + 1 : 0;
    const endItem = endIndex;
    
    const pages = this.generatePageNumbers(currentPage, totalPages);


 return {
      currentPage,
      itemsPerPage: config.itemsPerPage,
      totalItems: config.totalItems,
      totalPages,
      startIndex,
      endIndex,
      startItem,
      endItem,
      pages
    };
}

 /**
   * Générer les numéros de pages à afficher
   */
  private generatePageNumbers(currentPage: number, totalPages: number, maxPages: number = 5): number[] {
    if (totalPages <= maxPages) {
      return Array.from({ length: totalPages }, (_, i) => i + 1);
    }

    const pages: number[] = [];
    const halfMax = Math.floor(maxPages / 2);
    
    let startPage = Math.max(1, currentPage - halfMax);
    let endPage = Math.min(totalPages, currentPage + halfMax);

    if (currentPage <= halfMax) {
      endPage = maxPages;
    }

    if (currentPage + halfMax >= totalPages) {
      startPage = totalPages - maxPages + 1;
    }

    for (let i = startPage; i <= endPage; i++) {
      pages.push(i);
    }

    return pages;
  }

  /**
   * Paginer un tableau de données
   */
  paginateArray<T>(items: T[], page: number, itemsPerPage: number): T[] {
    const startIndex = (page - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    return items.slice(startIndex, endIndex);
  }

  /**
   * Obtenir la configuration par défaut
   */
  getDefaultConfig(): PaginationConfig {
    return { ...this.defaultConfig };
  }

}