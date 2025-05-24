import { Component } from '@angular/core';
import {AuthService} from "../../../services/auth/auth.service";

import {Router, RouterModule} from "@angular/router";
import {CodeInputModule} from "angular-code-input";

@Component({
  selector: 'app-activation-code',
  standalone: true,
  imports: [RouterModule, CodeInputModule],
  templateUrl: './activation-code.component.html',
  styleUrl: './activation-code.component.css'
})
export class ActivationCodeComponent {
  message='';
  isOkay=true;
  submitted=false;

  constructor(
    private router:Router,
    private authservice:AuthService

  ){}
  private confirmAccount(token: string) {
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

 /* redirectToLogin() {
    this.router.navigate(['/login']);
  }*/

  onCodeCompleted(token: string) {
    this.confirmAccount(token);
  }
}
