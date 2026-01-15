import { CommonModule } from '@angular/common';
import {Component, inject, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../services/auth/auth.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { ModalService } from '../../../services/modal.service';
import { ResetPasswordRequest } from '../../../models/playload/resetPasswordRequest';


@Component({
    selector: 'app-reset-password',
    imports: [CommonModule,ReactiveFormsModule,RouterModule],
    templateUrl: './reset-password.component.html',
    styleUrl: './reset-password.component.css'
})
export class ResetPasswordComponent implements OnInit{
private readonly fb = inject(FormBuilder);
  submitted = false;
  data:any
  message='';
  email: string = '';
  constructor(
    private readonly router:Router,
    private readonly modalService:ModalService,
    private readonly spinner:NgxSpinnerService,
    private readonly AuthService:AuthService,
    private readonly route:ActivatedRoute
  ){}

  resetForm = this.fb.group(
    {
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(6),
          Validators.maxLength(40)
        ]
      ],
      confirmPassword: ['',
        [
          Validators.required,
          Validators.minLength(6),
          Validators.maxLength(40)
        ]
      ],

    },
    { validators:  this.matchPasswords('password', 'confirmPassword') }
  );
  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
    const email = params['email'];
    if (email) {
      console.log('Email reçu dans reset-password :', email);
      // Tu peux maintenant préremplir un champ ou faire une requête
      this.email = email;
    }
  });
  }

  get f(): { [key: string]: AbstractControl } {
    return this.resetForm.controls;
  }
  onSubmit() {
    this.submitted = true;
    if (this.resetForm.valid) {
       let req:ResetPasswordRequest={
              email:this.email,
              newPassword:this.resetForm.get('password')?.value ?? '',
              confirmationPassword:this.resetForm.get('confirmPassword')?.value ?? '',
            }
      this.spinner.show();
      
      this.AuthService.changePassword(req).subscribe({
        next:(value:any)=> {
           console.log(' reset-password :', value);
          this.spinner.hide();
          this.onReset();
          this.modalService.openSuccessModal('Mot de passe réinitialisé avec succès !');
          this.router.navigate(['/login']);
        },
        error:(err:any) => {
          this.submitted=false
          this.spinner.hide();
          this.modalService.openWarning(err.error.error);
        },
      })

    }
  }


  onReset(): void {
    this.submitted = false;
    this.resetForm.reset();
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
