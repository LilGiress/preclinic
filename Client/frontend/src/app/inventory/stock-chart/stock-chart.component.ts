import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Chart, ChartConfiguration, ChartData, ChartOptions, ChartType } from 'chart.js';
import { InventoryService } from '../../services/inventory-service/inventory.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import * as XLSX from 'xlsx';
import { saveAs } from 'file-saver';
import jsPDF from 'jspdf';
import 'jspdf-autotable';
import autoTable from 'jspdf-autotable';
@Component({
  selector: 'app-stock-chart',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './stock-chart.component.html',
  styleUrl: './stock-chart.component.css'
})
export class StockChartComponent implements OnInit,AfterViewInit {
  medicines: string[] = [];
  selectedMedicine: string = '';
  startDate: string = '';
  endDate: string = '';
  stockData: any[] = [];
  @ViewChild('chartCanvas') chartCanvas!: ElementRef<HTMLCanvasElement>;
  //chartData = [{ data: [100, 120, 130, 150], label: 'Stock Price' }];
//chartLabels = ['Jan', 'Feb', 'Mar', 'Apr'];
chartData = {
  labels: ['Jan', 'Feb', 'Mar', 'Apr'],
  datasets: [
    {
      label: 'Stock Price',
      data: [100, 120, 130, 150],
      borderColor: 'blue',
     // borderWidth: 2,
      fill: false,
    },
  ],
};

chartOptions: ChartOptions = {
  responsive: true,
  plugins: {
    legend: {
      display: true,
    },
  },
};
 /* public lineChartData!: ChartData<'line'>;
  public lineChartOptions: ChartOptions = {
    responsive: true,
    plugins: {
      legend: {
        position: 'top',
      }
    }
  };*/
  public lineChartType: ChartType = 'line';
  constructor(private pharmacyService: InventoryService) {}
  ngAfterViewInit(): void {
   /* new Chart(this.chartCanvas.nativeElement, {
      type: 'line',
      data: {
        labels: ['Jan', 'Feb', 'Mar', 'Apr'],
        datasets: [
          {
            label: 'Stock Price',
            data: [100, 120, 130, 150],
            borderColor: 'blue',
            borderWidth: 2,
            fill: false,
          },
        ],
      },
    });*/

    new Chart(this.chartCanvas.nativeElement, {
      type: 'line',
      data: {
        labels: ['Jan', 'Feb', 'Mar', 'Apr'],
        datasets: [
          {
            label: 'Stock Price',
            data: [100, 120, 130, 150],
            borderColor: 'blue',
            borderWidth: 2,
            fill: false,
          },
        ],
      },
    });
  
  }
  

  ngOnInit(): void {
    this.loadMedicines();
    this.loadStockData();
  }

  loadMedicines() {
    this.pharmacyService.getAllMedicines().subscribe(data => {
      this.medicines = data;
    });
  }

  loadStockData() {
    this.pharmacyService.getStockHistory().subscribe(data => {
      this.stockData = data; // Stocker les données pour l'export
      this.updateChart(data);
    });
  }


  updateChart(data: any[]) {
    const labels = [...new Set(data.map(item => item.date))]; // Dates uniques
      const medicineNames = [...new Set(data.map(item => item.name))]; // Médicaments uniques

      const datasets = medicineNames.map(name => {
        return {
          label: name,
          data: labels.map(date => {
            const stock = data.find(item => item.name === name && item.date === date);
            return stock ? stock.quantity : 0;
          }),
          borderColor: this.getRandomColor(),
          fill: false
        };
      });

      this.chartData = {
        labels: labels,
        datasets: datasets
      };
  }


  getRandomColor(): string {
    return `#${Math.floor(Math.random()*16777215).toString(16)}`;
  }

  exportToExcel() {
    const worksheet = XLSX.utils.json_to_sheet(this.stockData);
    const workbook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(workbook, worksheet, 'Stock');

    const excelBuffer = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
    const blob = new Blob([excelBuffer], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8' });

    saveAs(blob, `Stock_${new Date().toISOString()}.xlsx`);
  }

  exportToPDF() {
    const doc = new jsPDF();
    doc.text('📋 Rapport de Stock Médical', 14, 10);

    const columns = ['Date', 'Médicament', 'Quantité'];
    const rows = this.stockData.map(item => [item.date, item.name, item.quantity]);
  
    autoTable(doc,{
      head: [columns],
      body: rows,
      startY: 20
    });

    doc.save(`Stock_${new Date().toISOString()}.pdf`);
  }

}
