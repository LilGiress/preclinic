import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {CommonModule } from '@angular/common';
import {HomeComponent} from "./pages/home/home.component";
import {RouterModule} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {FooterComponent} from "./navbar/footer/footer.component";
import {LoginComponent} from "./pages/login/login.component";
import {ProfilComponent} from "./pages/profil/profil.component";
import {ResetPasswordComponent} from "./pages/reset-password/reset-password.component";
import {ForgotPasswordComponent} from "./pages/forgot-password/forgot-password.component";
import {PageNotFoundComponent} from "./pages/page-not-found/page-not-found.component";
import {TopNavbarComponent} from "./navbar/top-navbar/top-navbar.component";
import {ActivationCodeComponent} from "./pages/activation-code/activation-code.component";
import {LeftSidebarAdminComponent} from "./navbar/admin/left-sidebar-admin/left-sidebar-admin.component";
import {SettingNavbarComponent} from "./navbar/admin/setting-navbar/setting-navbar.component";
import { SearchComponent } from './pages/search/search.component';
import { DoctorProfilComponent } from './pages/profil/doctor-profil/doctor-profil.component';
import { PatientProfilComponent } from './pages/profil/patient-profil/patient-profil.component';
import { DoctorDashboardComponent } from '../administration/doctors/doctor-dashboard/doctor-dashboard.component';
import { PatientDashboardComponent } from '../administration/patients/patient-dashboard/patient-dashboard.component';
import { MyPatientsComponent } from '../administration/doctors/my-patients/my-patients.component';
import { MyAppointmentsComponent } from '../administration/doctors/my-appointments/my-appointments.component';
import { ModalContainerComponent } from './modal/modal-container/modal-container.component';
import { DeleteModalComponent } from './modal/delete-modal/delete-modal.component';
import { SuccessModalComponent } from './modal/success-modal/success-modal.component';
import { SocketIoModule, SocketIoConfig } from 'ngx-socket-io';
import { BibliothequeComponent } from '../settings/bibliotheque/bibliotheque.component';
import { NgxSpinnerModule } from "ngx-spinner";
import { NgImageSliderModule } from 'ng-image-slider';




const config: SocketIoConfig = { url: 'http://localhost:8080', options: {} };

@NgModule({
  declarations: [

  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    NgxSpinnerModule,
    NgImageSliderModule,
    SocketIoModule.forRoot(config),
    FooterComponent,
    LoginComponent,
    ProfilComponent,
    ResetPasswordComponent,
    ForgotPasswordComponent,
    PageNotFoundComponent,
    TopNavbarComponent,
    ActivationCodeComponent,
    LeftSidebarAdminComponent,
    SettingNavbarComponent,
    HomeComponent,
    SearchComponent,
    DoctorProfilComponent,
    PatientProfilComponent,
    DoctorDashboardComponent,
    PatientDashboardComponent,
    MyPatientsComponent,
    MyAppointmentsComponent,
    ModalContainerComponent,
    DeleteModalComponent,
    SuccessModalComponent,
    BibliothequeComponent
    
  ],
  exports:[
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    NgxSpinnerModule,
    NgImageSliderModule,
    FooterComponent,
    LoginComponent,
    ProfilComponent,
    ResetPasswordComponent,
    ForgotPasswordComponent,
    PageNotFoundComponent,
    TopNavbarComponent,
    ActivationCodeComponent,
    LeftSidebarAdminComponent,
    SettingNavbarComponent,
    HomeComponent,
    SearchComponent,
    DoctorProfilComponent,
    PatientProfilComponent,
    DoctorDashboardComponent,
    PatientDashboardComponent,
    MyPatientsComponent,
    MyAppointmentsComponent,
    ModalContainerComponent,
    DeleteModalComponent,
    SuccessModalComponent,
    BibliothequeComponent
  ],
  schemas:[CUSTOM_ELEMENTS_SCHEMA]
})
export class SharedModule { }
