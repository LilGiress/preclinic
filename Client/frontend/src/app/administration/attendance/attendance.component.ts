import { CommonModule, NgFor } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import * as XLSX from 'xlsx';
import { saveAs } from 'file-saver';
import jsPDF from 'jspdf';
import 'jspdf-autotable';
import { HttpClient } from '@angular/common/http';
import { BasePagination } from '../../utils/Base-pagination';
import { PaginationService } from '../../shared/service/pagination.service';
import { DateService } from '../../utils/date.service';

@Component({
  selector: 'app-attendance',
  standalone: true,
  imports: [NgFor,FormsModule,CommonModule],
  templateUrl: './attendance.component.html',
  styleUrl: './attendance.component.css'
})
export class AttendanceComponent extends BasePagination<any> implements OnInit{
 
  days: number[] = [];
 


  // Filtres
  searchName: string = '';
  currentYear: number;
  selectedYear: number;
  selectedMonth: number;


  
 

  // Données fictives des employés
  employees = [
    { name: 'Albina Simonis', attendance: [true, true, true, true, true, false, true, true, true, false, false, true, true, true, true, true, true, true, false, true, true, true, true, true, true, true, false, true, true, true] },
    { name: 'Cristina Groves', attendance: [true, true, true, true, true, true, true, true, false, true, true, true, true, true, true, true, true, false, true, true, true, true, true, true, true, false, true, true, true, true] },
    // Ajoutez d'autres employés ici
  ];

  filteredEmployees = [...this.employees]; // Copie des employés pour filtrage

constructor(
  paginationService: PaginationService,
  private http:HttpClient,
  private dateService: DateService
){
  super(paginationService);
  this.currentYear = new Date().getFullYear(); // Année actuelle
  this.selectedYear = this.currentYear; // Par défaut, l'année actuelle
  this.selectedMonth = new Date().getMonth() + 1; // Par défaut, mois actuel
  this.updatePaginatedData();
  this.updateDays();
}

  ngOnInit(): void {
  //this.initializeAttendance();
}

  filterData() {
    this.filteredEmployees = this.employees.filter(employee => {
      const matchesName = employee.name.toLowerCase().includes(this.searchName.toLowerCase());
      // Ajoutez d'autres filtres si nécessaire (mois, année, etc.)
      return matchesName;
    });
  }


  toggleAttendance(employee: any, dayIndex: number): void {
    employee.attendance[dayIndex] = !employee.attendance[dayIndex];

    // Envoyer les modifications au backend
 /* this.http.post('https://api.example.com/attendance/update', {
    employeeId: employee.id,
    day: this.days[dayIndex],
    status: employee.attendance[dayIndex]
  }).subscribe(response => {
    console.log('Attendance updated successfully', response);
  });*/
  }


  exportToExcel(): void {
    // Transformer les données en format compatible avec Excel
    const data: any[] = this.filteredEmployees.map(employee => {
      const attendanceRecord: any = { Employee: employee.name };
      this.days.forEach((day, index) => {
        attendanceRecord[`Day ${day}`] = employee.attendance[index] ? '✔️ Present' : '❌ Absent';
      });
      return attendanceRecord;
    });
  
    // Créer un workbook et une feuille
    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(data);
    const workbook: XLSX.WorkBook = {
      Sheets: { Attendance: worksheet },
      SheetNames: ['Attendance']
    };
  
    // Générer le fichier Excel
    const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
    const dataBlob: Blob = new Blob([excelBuffer], { type: 'application/octet-stream' });
  
    // Télécharger le fichier
    saveAs(dataBlob, 'AttendanceSheet.xlsx');
  }


  exportToPDF(): void {
    const doc = new jsPDF();
  
    // Titre du document
    doc.text('Attendance Sheet', 14, 10);
  
    // Colonnes du tableau
    const columns = ['Employee', ...this.days.map(day => `Day ${day}`)];
  
    // Données du tableau
    const rows = this.filteredEmployees.map(employee => [
      employee.name,
      ...employee.attendance.map(attendance => (attendance ? '✔️ Present' : '❌ Absent'))
    ]);
  
    // Ajouter le tableau avec autoTable
    (doc as any).autoTable({
      head: [columns],
      body: rows,
      startY: 20,
      theme: 'striped'
    });
  
    // Télécharger le PDF
    doc.save('AttendanceSheet.pdf');
  }


    // Initialiser les états de présence avec des valeurs par défaut
    initializeAttendance(): void {
      for (const employee of this.employees) {
        employee.attendance = Array(this.days.length).fill(false); // Par défaut : absent
      }
    }



 

   // Met à jour les jours en fonction de l'année et du mois
   updateDays(): void {
    this.days = this.dateService.getDaysInMonth(this.selectedYear, this.selectedMonth);
    console.log(`Mise à jour des jours pour ${this.selectedMonth}/${this.selectedYear}`);
    this.initializeAttendance();
  }

  // Gestion de la sélection de l'année ou du mois
  onYearChange(year: number): void {
    this.selectedYear = year;
    this.updateDays();
  }

  onMonthChange(month: number): void {
    this.selectedMonth = month;
    this.updateDays();
  }

  // Vérifie si l'année est différente de l'année actuelle
  isPreviousYear(): boolean {
    return !this.dateService.isCurrentYear(this.selectedYear);
  }
}
