import {Component, inject, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, ValidationErrors, Validators} from "@angular/forms";
import {AuthService} from "../../../services/auth/auth.service";
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NgxSpinnerService } from 'ngx-spinner';
import { ModalService } from '../../service/modal.service';
import { IDepartement } from '../../../models/departments';
import { RolesService } from '../../../services/roles.service';
import { DepartementService } from '../../../services/departement.service';
import { IRole } from '../../../models/role';
import { RegistrationRequest } from '../../../models/playload/RegistrationRequest';

@Component({
    selector: 'app-register',
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
    private readonly toastr:ModalService,
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
  roles: [this.rolePatientId] 
};
   console.log('--------------------------------this.registration',registration);
      this.spinner.show();
      this.authservice.register(registration).subscribe({
        next: (value: any) =>{

            console.log(value);
          this.toastr.openSuccessModal('Opéretion effectuer');
          this.route.navigate(['/activation-code'])
          this.spinner.hide();

        },
        error:(err) =>{
          this.spinner.hide();
          this.message= err.error
          this.toastr.openWarning('Opération echouer ','Echec');

        }
      })
    }
  }

 loadDepartements(): void {
    this.departementService.getAll().subscribe({
      next: (value:any) =>{
         this.departements = value;
          console.log('--------------------------------this.departements',this.departements);
          //  console.log(value);
         // this.toastr.openSuccessModal('Opéretion effectuer');
         // this.route.navigate(['/activation-code'])

        },
        error:(err) =>{
          this.spinner.hide();
          this.message= err.error
          this.toastr.openWarning('Opération echouer ','Echec');

        }
    })
      
   
  }

   getAllRoles(): void {
    this.roleService.getRoles().subscribe({
      next:(value:any) => {
         this.roles = value
        // this.rolePatient= this.roles.
          if (this.roles) {
          this.roles?.forEach(role => {
          if (role.name === 'PATIENT') {
            this.rolePatientId = role.id!;
          this.rolePatient = role.name;
           console.log('Role Patient trouvé :', this.rolePatientId);
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


   passwordMatchValidator(form: AbstractControl): ValidationErrors | null {
  const password = form.get('password');
  const confirmPassword = form.get('confirmPassword');
  if (password && confirmPassword && password.value !== confirmPassword.value) {
    confirmPassword.setErrors({ mismatch: true });
    return { mismatch: true };
  } else {
    return null;
  }
}
}
