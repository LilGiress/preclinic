import { CommonModule } from '@angular/common';
import {Component, inject, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../services/auth/auth.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { ModalService } from '../../service/modal.service';


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
  
  constructor(
    private readonly router:Router,
    private readonly modalService:ModalService,
    private readonly spinner:NgxSpinnerService,
    private readonly AuthService:AuthService
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

  }

  get f(): { [key: string]: AbstractControl } {
    return this.resetForm.controls;
  }
  onSubmit() {
    this.submitted = true;
    if (this.resetForm.valid) {
      this.spinner.show();
      this.AuthService.changePassword(this.resetForm.value).subscribe({
        next:(value:any)=> {
          this.data=value;
          this.spinner.hide();
          this.onReset();
          this.modalService.openSuccessModal('Operation effectuer');
          this.router.navigate(['/login']);
        },
        error:(err:any) => {
          this.submitted=false
          this.spinner.hide();
          this.modalService.openWarning('Operation echouer');
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
