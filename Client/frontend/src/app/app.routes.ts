import {RouterModule, Routes} from '@angular/router';
import {NgModule} from "@angular/core";
import {PageNotFoundComponent} from "./shared/pages/page-not-found/page-not-found.component";
import {ActivationCodeComponent} from "./shared/pages/activation-code/activation-code.component";
import {ResetPasswordComponent} from "./shared/pages/reset-password/reset-password.component";
import {ProfilComponent} from "./shared/pages/profil/profil.component";
import {ForgotPasswordComponent} from "./shared/pages/forgot-password/forgot-password.component";
import {RegisterComponent} from "./shared/pages/register/register.component";
import {LoginComponent} from "./shared/pages/login/login.component";
import {HomeComponent} from "./shared/pages/home/home.component";
import {SkeletorComponent} from "./shared/pages/skeletor/skeletor.component";
import { SearchComponent } from './shared/pages/search/search.component';
import { DoctorProfilComponent } from './shared/pages/profil/doctor-profil/doctor-profil.component';
import { PatientProfilComponent } from './shared/pages/profil/patient-profil/patient-profil.component';
import { DoctorDashboardComponent } from './administration/doctors/doctor-dashboard/doctor-dashboard.component';
import { PatientDashboardComponent } from './administration/patients/patient-dashboard/patient-dashboard.component';
import { MyPatientsComponent } from './administration/doctors/my-patients/my-patients.component';
import { MyAppointmentsComponent } from './administration/doctors/my-appointments/my-appointments.component';

export const routes: Routes = [
  {path : '',redirectTo :'/home',pathMatch: 'full'},
  {
    path:'',
    component:SkeletorComponent,
    children:[
      { path :"home", component : HomeComponent},
      { path :"login", component : LoginComponent},
      { path :"register", component : RegisterComponent},
      { path :"forgot-password", component : ForgotPasswordComponent},
      { path :"profil", component : ProfilComponent},
      { path :"reset-password", component : ResetPasswordComponent},
      { path :"activation-code", component : ActivationCodeComponent},
      { path :"search", component : SearchComponent},
      { path :"doctor-profil", component : DoctorProfilComponent},
      { path :"patient-profil", component : PatientProfilComponent},
      {path : "doctor-dashboard", component : DoctorDashboardComponent},
      {path : "patient-dashboard", component : PatientDashboardComponent},
      {path : "my-patients", component : MyPatientsComponent},
      {path : "my-appointments", component : MyAppointmentsComponent},

    ]
  },

  {
    path: 'administration',
   // canActivate: [roleGuard(['Admin'])],
    loadChildren: () =>
      import('./administration/administration.module').then(m => m.AdministrationModule),
  },
 /* ,
  {
    path: 'doctor-dashboard',
    canActivate: [roleGuard(['Doctor'])],
    loadChildren: () => import('./doctor/doctor.module').then(m => m.DoctorModule),
  },
  {
    path: 'employee-dashboard',
    canActivate: [roleGuard(['Employee'])],
    loadChildren: () => import('./employee/employee.module').then(m => m.EmployeeModule),
  },
  {
    path: 'finance-dashboard',
    canActivate: [roleGuard(['Finance'])],
    loadChildren: () => import('./finance/finance.module').then(m => m.FinanceModule),
  },
  {
    path: 'patient-dashboard',
    canActivate: [roleGuard(['Patient'])],
    loadChildren: () => import('./patient/patient.module').then(m => m.PatientModule),
  },
  */
  {path: '**', component: PageNotFoundComponent},
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
