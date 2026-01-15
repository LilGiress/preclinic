import { Injectable, OnInit } from "@angular/core";
import { PaginationService } from "../services/pagination.service";


@Injectable({
    providedIn: 'root' // Si vous voulez que ce service soit accessible globalement
  })
export abstract class BasePagination<T>  {
    data: T[] = []; // Données brutes
    paginatedData: T[] = []; // Données paginées
  
    constructor(public paginationService: PaginationService) {}
  
  
    // Mettre à jour les données paginées
    // updatePaginatedData(): void {
    //   this.paginatedData = this.paginationService.getPaginatedData(this.data);
    // }
  
    // // Gérer le changement de page
    // onPageChange(newPage: number): void {
    //   this.paginationService.currentPage = newPage;
    //   this.updatePaginatedData();
    // }
  }