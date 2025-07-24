import {Component, inject, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, ReactiveFormsModule, ValidationErrors, Validators} from "@angular/forms";
import {AuthService} from "../../../services/auth/auth.service";
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NgxSpinnerService } from 'ngx-spinner';
import { ModalService } from '../../service/modal.service';
import { IDepartement } from '../../../models/departments';
import { RolesService } from '../../../services/roles.service';
import { DepartementService } from '../../../services/department/departement.service';
import { IRole } from '../../../models/role';
import { RegistrationRequest } from '../../../models/playload/RegistrationRequest';

@Component({
    selector: 'app-register',
    standalone: true,
    imports: [CommonModule,RouterModule,ReactiveFormsModule],
    templateUrl: './register.component.html',
    styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit{
  private readonly fb = inject(FormBuilder)
 
  submitted = false;
  message='';
  isOkay=true;
  playload=''
  loading=false
  roles:IRole[]=[];
  departements:IDepartement[]=[];
  rolePatient!:string;
  rolePatientId!:number;

  registrationForm = this.fb.group({
      firstname: ['', Validators.required],
      lastname: ['',Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['',[Validators.required,Validators.minLength(6),Validators.maxLength(40)]],
      confirmPassword: ['',[ Validators.required,]],
      //birthday: ['', Validators.required]
    },{ validators:  this.matchPasswords('password', 'confirmPassword') });
 

  constructor(
    private readonly authservice:AuthService,
    private readonly route:Router,
    private readonly modalService:ModalService,
    private readonly spinner:NgxSpinnerService,
    private readonly roleService:RolesService,
    private readonly departementService:DepartementService
  ){}
  ngOnInit(): void {
   // this.loadDepartements();
    this.getAllRoles();

  }

  get f(): { [key: string]: AbstractControl } {
    return this.registrationForm.controls;
  }

  Submit():void{
   this.submitted=true;

    if (this.registrationForm.valid) {
    let registration: RegistrationRequest = {
  firstname: this.registrationForm.get('firstname')?.value ?? '',
  lastname: this.registrationForm.get('lastname')?.value ?? '',
  email: this.registrationForm.get('email')?.value ?? '',
  password: this.registrationForm.get('password')?.value ?? '',
  roles: this.roles! 
};
   
      this.spinner.show();
      this.authservice.register(registration).subscribe({
        next: (value: any) =>{ 
          this.spinner.hide();
          this.modalService.openSuccessModal(
            ' Parfait ! Votre compte a été ajouté et est maintenant prêt à être utilisé. Veuillez verifier votre boite mail',
          () => this.route.navigate(['/home'])
          );
          this.onReset();
        
        },
        error:(err:any) =>{
          this.spinner.hide();
          this.message= err.error.error;
          this.modalService.openWarning(this.message ,'Echec');

        }
      })
    }
  }
 
   getAllRoles(): void {
    this.roleService.getRoles().subscribe({
      next:(value:any) => {
         this.roles = value
          if (this.roles) {
          this.roles?.forEach(role => {
          if (role.name === 'PATIENT') {
          this.rolePatient = role.name;
             }
          });
        }
      },
      error:(err) =>{

          
      },
     
   });
   
  }



  /**+
   * Pour reset le formulaire
   */

  onReset(): void {
    this.submitted = false;
    this.registrationForm.reset();
  }
  // Custom validator function
 matchPasswords(passwordKey: string, confirmKey: string) {
    return (group: AbstractControl) => {
      const password = group.get(passwordKey);
      const confirm = group.get(confirmKey);
      if (password?.value !== confirm?.value) {
        confirm?.setErrors({ mismatch: true });
      } else {
        confirm?.setErrors(null);
      }
      return null;
    };
  }

}
