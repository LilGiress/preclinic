import { Component } from '@angular/core';
import {AuthService} from "../../../services/auth/auth.service";

import {Router, RouterModule} from "@angular/router";
import {CodeInputModule} from "angular-code-input";
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-activation-code',
    imports: [RouterModule, CodeInputModule,CommonModule],
    templateUrl: './activation-code.component.html',
    styleUrl: './activation-code.component.css'
})
export class ActivationCodeComponent {
  message='';
  isOkay=true;
  submitted=false;
  tokenRestore!:string;

  constructor(
    private readonly router:Router,
    private readonly authservice:AuthService

  ){}
  private confirmAccount(token: string) {
      this.tokenRestore=token;
    this.authservice.activateCode(token).subscribe({
      next: data => {
        this.message = 'Your account has been successfully activated.\nNow you can proceed to login';
        this.submitted = true;
      },
      error: err => {
        this.message= "Token has been expired or invalid";
        this.submitted = true;
        this.isOkay = false;
      }
    });
  }

  redirectToLogin() {
    this.router.navigate(['/login']);
  }

  onCodeCompleted(token: string) {
    this.confirmAccount(token);
  }
  onResentActivationCode(){
     this.confirmAccount(this.tokenRestore);
  }
}
