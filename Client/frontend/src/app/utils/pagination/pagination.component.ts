import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { PaginationService } from '../../shared/service/pagination.service';
import { PaginationState } from '../../models/pagination/pagination-state';

@Component({
  selector: 'app-pagination',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pagination.component.html',
  styleUrl: './pagination.component.css'
})
export class PaginationComponent implements OnInit,OnChanges {
 @Input() totalItems: number = 0;
  @Input() itemsPerPage: number = 10;
  @Input() currentPage: number = 1;
  @Input() pageSizeOptions: number[] = [10, 25, 50, 100];
  @Input() showPageSizeSelector: boolean = true;
  @Input() showFirstLastButtons: boolean = true;
  @Input() maxPages: number = 5;
  
  @Output() pageChange = new EventEmitter<number>();
  @Output() pageSizeChange = new EventEmitter<number>();
  
  paginationState?: PaginationState;

  constructor(private readonly paginationService: PaginationService) {}

  ngOnInit(): void {
    this.updatePaginationState();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['totalItems'] || changes['itemsPerPage'] || changes['currentPage']) {
      this.updatePaginationState();
    }
  }

  private updatePaginationState(): void {
    this.paginationState = this.paginationService.calculatePaginationState({
      currentPage: this.currentPage,
      itemsPerPage: this.itemsPerPage,
      totalItems: this.totalItems,
      pageSizeOptions: this.pageSizeOptions
    });
  }

  goToPage(page: number): void {
    if (page >= 1 && page <= this.paginationState?.totalPages! && page !== this.currentPage) {
      this.pageChange.emit(page);
    }
  }

  goToFirstPage(): void {
    this.goToPage(1);
  }

  goToLastPage(): void {
    this.goToPage(this.paginationState!.totalPages);
  }

  goToPreviousPage(): void {
    this.goToPage(this.currentPage - 1);
  }

  goToNextPage(): void {
    this.goToPage(this.currentPage + 1);
  }

  onPageSizeChange(newSize: number): void {
    this.pageSizeChange.emit(newSize);
    // Reset to first page when changing page size
    this.pageChange.emit(1);
  }

  get hasPreviousPage(): boolean {
    return this.currentPage > 1;
  }

  get hasNextPage(): boolean {
    return this.currentPage < this.paginationState!.totalPages;
  }
 
}
