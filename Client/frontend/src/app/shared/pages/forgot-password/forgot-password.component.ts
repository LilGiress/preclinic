import {Component, inject, OnInit} from '@angular/core';
import { Validators, FormBuilder, AbstractControl, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { ModalService } from '../../service/modal.service';
import { AuthService } from '../../../services/auth/auth.service';
import { CommonModule } from '@angular/common';
import { ForgotPasswordRequest } from '../../../models/playload/forgotPasswordRequest';


@Component({
    selector: 'app-forgot-password',
    imports: [CommonModule,ReactiveFormsModule,RouterModule],
    templateUrl: './forgot-password.component.html',
    styleUrl: './forgot-password.component.css'
})
export class ForgotPasswordComponent implements OnInit{
  private readonly fb = inject(FormBuilder)
  submitted = false;
  message='';
  forgotform = this.fb.group(
    {
      email: ['', [Validators.required, Validators.email,Validators.pattern(/^[^\s@]+@[^\s@]+\.[^\s@]+$/)]],

    },
  );

  constructor(
    private readonly router:Router,
    private readonly modalService:ModalService,
    private readonly spinner:NgxSpinnerService,
    private readonly authservice:AuthService

  ){}
  ngOnInit(): void {
  }

  get f(): { [key: string]: AbstractControl } {
    return this.forgotform.controls;
  }

  onSubmit() {
    this.submitted = true;
    if (this.forgotform.valid) {
      let req:ForgotPasswordRequest={
        email:this.forgotform.get('email')?.value ?? '',
      }
      this.spinner.show();
      this.authservice.ForgotPassword(req).subscribe({
        next:(value:any)=> {
          this.onReset();
          this.spinner.hide();
          this.modalService.openSuccessModal('Operation effectuer')
          this.router.navigate(['/reset-password'],{queryParams:req});
        },
        error:(err:any)=> {
          this.submitted=false;
          this.message=err.error.error;
          this.spinner.hide();
          this.modalService.openWarning(this.message,'Echec')
        },
      })

    }
  }


  onReset(): void {
    this.submitted = false;
    this.forgotform.reset();
  }

}
