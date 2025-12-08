export interface PaginationState{
    currentPage: number;
  itemsPerPage: number;
  totalItems: number;
  totalPages: number;
  startIndex: number;
  endIndex: number;
  startItem: number;
  endItem: number;
  pages: number[];
}