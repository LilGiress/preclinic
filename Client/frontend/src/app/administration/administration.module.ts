import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdministrationRoutingModule } from './administration-routing.module';
import {SharedModule} from "../shared/shared.module";
import {HolidaysComponent} from "../settings/holidays/holidays.component";
import {CompanySettingComponent} from "../settings/company-setting/company-setting.component";
import {LeavesComponent} from "../settings/leaves/leaves.component";
import {NotificationsComponent} from "../settings/notifications/notifications.component";
import {RolePermissionComponent} from "../settings/role-permission/role-permission.component";
import {SalarySettingComponent} from "../settings/salary-setting/salary-setting.component";
import {EmailSettingComponent} from "../settings/email-setting/email-setting.component";
import { PatientsComponent } from './patients/patients.component';
import { MedecinesComponent } from './medecines/medecines.component';
import { AttendanceComponent } from './attendance/attendance.component';
import { EmployeesComponent } from './employees/employees.component';
import { DepartmentsComponent } from './departments/departments.component';
import { AdminSkeletorComponent } from './admin-skeletor/admin-skeletor.component';
import { CreatePatientComponent } from './patients/create-patient/create-patient.component';
import { EditPatientComponent } from './patients/edit-patient/edit-patient.component';
import { EditAppointmentComponent } from './appointments/edit-appointment/edit-appointment.component';
import { EditDepartmentComponent } from './departments/edit-department/edit-department.component';
import { EditDocdorScheduleComponent } from './doctor-schedule/edit-docdor-schedule/edit-docdor-schedule.component';
import { EditDoctorComponent } from './doctors/edit-doctor/edit-doctor.component';
import { EditEmployeeComponent } from './employees/edit-employee/edit-employee.component';
import { CreateAppointmentComponent } from './appointments/create-appointment/create-appointment.component';
import { CreateDepartmentComponent } from './departments/create-department/create-department.component';
import { CreateDocdorScheduleComponent } from './doctor-schedule/create-docdor-schedule/create-docdor-schedule.component';
import { CreateDoctorComponent } from './doctors/create-doctor/create-doctor.component';
import { CreateEmployeeComponent } from './employees/create-employee/create-employee.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LeaveTypeComponent } from '../settings/leave-type/leave-type.component';
import { CalendarComponent } from './calendar/calendar.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [


  ],
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    AdministrationRoutingModule,
    HolidaysComponent,
    CompanySettingComponent,
    LeavesComponent,
    NotificationsComponent,
    RolePermissionComponent,
    SalarySettingComponent,
    EmailSettingComponent,
    PatientsComponent,
    MedecinesComponent,
    AttendanceComponent,
    EmployeesComponent,
    DepartmentsComponent,
    AdminSkeletorComponent,
    CreatePatientComponent,
    EditPatientComponent,
    EditAppointmentComponent,
    EditDepartmentComponent,
    EditDocdorScheduleComponent,
    EditDoctorComponent,
    EditEmployeeComponent,
    CreateAppointmentComponent,
    CreateDepartmentComponent,
    CreateDocdorScheduleComponent,
    CreateDoctorComponent,
    CreatePatientComponent,
    CreateEmployeeComponent,
    DashboardComponent,
    LeaveTypeComponent,
    CalendarComponent
    
  ]
})
export class AdministrationModule { }
