import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PatientsComponent } from './patients/patients.component';
import { AdminSkeletorComponent } from './admin-skeletor/admin-skeletor.component';
import { MedecinesComponent } from './medecines/medecines.component';
import { AppointmentsComponent } from './appointments/appointments.component';
import { DoctorScheduleComponent } from './doctor-schedule/doctor-schedule.component';
import { EmployeesComponent } from './employees/employees.component';
import { DepartmentsComponent } from './departments/departments.component';
import { RolePermissionComponent } from '../settings/role-permission/role-permission.component';
import { NotificationsComponent } from '../settings/notifications/notifications.component';
import { CompanySettingComponent } from '../settings/company-setting/company-setting.component';
import { SalarySettingComponent } from '../settings/salary-setting/salary-setting.component';
import { EmailSettingComponent } from '../settings/email-setting/email-setting.component';
import { LeaveTypeComponent } from '../settings/leave-type/leave-type.component';
import { DoctorDashboardComponent } from './doctors/doctor-dashboard/doctor-dashboard.component';
import { CalendarComponent } from './calendar/calendar.component';
import { LeavesComponent } from '../settings/leaves/leaves.component';
import { HolidaysComponent } from '../settings/holidays/holidays.component';
import { AttendanceComponent } from './attendance/attendance.component';
import { DoctorsComponent } from './doctors/doctors.component';

const routes: Routes = [
  {path : '', component : AdminSkeletorComponent,
    children:[
      {path : 'dashboard', component : DashboardComponent},  
      {path : 'patient', component : PatientsComponent},
      {path : 'medecine', component : MedecinesComponent}, 
      {path : 'appointment', component : AppointmentsComponent},
      {path : 'doctor-schedule', component : DoctorScheduleComponent},
      {path : 'employee', component : EmployeesComponent},
      {path : 'department', component : DepartmentsComponent},
      {path : 'emails', component : EmailSettingComponent},
      {path : 'role-permission', component : RolePermissionComponent},
      {path : 'notification', component : NotificationsComponent},
      {path : 'leave-type', component : LeaveTypeComponent},
      {path : 'salary', component : SalarySettingComponent},
      {path : 'company-setting', component : CompanySettingComponent},
      {path: 'calendar',component: CalendarComponent},
      {path: 'leaves',component: LeavesComponent},
      {path: 'holidays',component: HolidaysComponent},
      {path: 'attendance',component: AttendanceComponent},
      {path: 'doctors',component: DoctorsComponent}
      
      /*{path: 'cotation-start',component: CotationStartComponent},
      {path: 'details-menbre/:id',component: DetailsMenbreComponent},
      {path: 'administration',component: AdministrationComponent},
      {path: 'details-organisation/:id',component: DetailsOrganisationComponent},*/

    ]
  }, 
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdministrationRoutingModule { }
