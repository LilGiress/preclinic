import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InventoryRoutingModule } from './inventory-routing.module';
import { FormsModule } from '@angular/forms';
import { SharedModule } from '../shared/shared.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { StockChartComponent } from './stock-chart/stock-chart.component';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    InventoryRoutingModule,
    DashboardComponent,
    StockChartComponent
  ]
})
export class InventoryModule { }
