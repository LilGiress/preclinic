import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DateService {

  constructor() { }
    // Retourne les jours du mois en fonction de l'année et du mois
    getDaysInMonth(year: number, month: number): number[] {
      const days = new Date(year, month, 0).getDate();
      return Array.from({ length: days }, (_, i) => i + 1);
    }
  
    // Vérifie si l'année sélectionnée est différente de l'année actuelle
    isCurrentYear(year: number): boolean {
      const currentYear = new Date().getFullYear();
      return year === currentYear;
    }
}
