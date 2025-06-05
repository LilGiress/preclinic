import {Component, inject, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, ReactiveFormsModule, Validators} from "@angular/forms";

import {AuthService} from "../../../services/auth/auth.service";
import {TokenService} from "../../../services/token/token.service";
import { Router, RouterModule } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { CommonModule } from '@angular/common';



@Component({
    selector: 'app-login',
    imports: [ReactiveFormsModule,CommonModule,RouterModule],
    templateUrl: './login.component.html',
    styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
  private readonly fb = inject(FormBuilder);
  
  roles: string[] = [];
  
  submitted = false;
  errorMessage="";
  user:any;
  constructor(
    private readonly router: Router,
    private readonly authservice:AuthService,
    private readonly tokenservie : TokenService,
    private readonly spinner:NgxSpinnerService,
  ){

  }

  loginForm = this.fb.group(
    {

      email: ['', [Validators.required, Validators.email]],
      password: ['',[
          Validators.required,
          Validators.minLength(6),
          Validators.maxLength(40)
        ]
      ],
    },

  );
  ngOnInit(): void {
    if (this.tokenservie.getToken() !== null) {
      this.roles = this.tokenservie.getUser().roles;
    }


  }

  get f(): { [key: string]: AbstractControl } {
    return this.loginForm.controls;
  }

  onSubmit():void{
    this.submitted = true;
    if (this.loginForm.valid) {
      this.spinner.show();
      this.authservice.login(this.loginForm.value).subscribe(
        {
          next:(res:any) =>{
            console.log('value ////////////////////',res.access_token);
            this.tokenservie.setToken(res.access_token);

            console.log('value+++++++++++++++this.tokenservie.getToken()',this.tokenservie.getToken())
            if (res.access_token === this.tokenservie.getToken()) {
              this.spinner.hide();
              this.router.navigateByUrl("/home");
              this.onReset();
            }
          },
          error:(err)=> {
            this.errorMessage= err;
          },
        }
      )

    }
  }


  onReset(): void {
    this.submitted = false;
    this.loginForm.reset();
  }
}
