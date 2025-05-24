import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, Validators} from "@angular/forms";

import {AuthService} from "../../../services/auth/auth.service";
import {TokenService} from "../../../services/token/token.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
  roles: string[] = [];
  
  submitted = false;
  errorMessage="";
  user:any;
  constructor(
    private router:Router,
    private fb:FormBuilder,
    private authservice:AuthService,
    private tokenservie : TokenService,
    //private spinner:NgxSpinnerService
  ){

  }

 /* loginForm = this.fb.group(
    {

      email: ['', [Validators.required, Validators.email]],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(40)
        ]
      ],
    },

  );*/
  ngOnInit(): void {
   /* if (this.tokenservie.getToken()) {
      this.roles = this.tokenservie.getUser().roles;
    }*/


  }

  /*get f(): { [key: string]: AbstractControl } {
    return this.loginForm.controls;
  }*/

  /*onSubmit():void{
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
  }*/


  /*onReset(): void {
    this.submitted = false;
    this.loginForm.reset();
  }*/
}
