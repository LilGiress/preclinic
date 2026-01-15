import { Component } from '@angular/core';
import {AuthService} from "../../../services/auth/auth.service";

import {Router, RouterModule} from "@angular/router";
import {CodeInputModule} from "angular-code-input";
import { CommonModule } from '@angular/common';
import { ModalService } from '../../../services/modal.service';

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
    private readonly authservice:AuthService,
    private readonly modalService:ModalService

  ){}
  private confirmAccount(token: string) {
      this.tokenRestore=token;
    this.authservice.activateCode(token).subscribe({
      next: (data) => {
        this.modalService.openSuccessModal('Your account has been successfully activated.\nNow you can proceed to login');
        this.submitted = true;
      },
       error:( err )=> {
         if (err.status === 401) { 
          this.message="Activation token has expired. A new token has been sent to the same email address." // Token expiré
        this.modalService.openWarning(
          "Activation token has expired. A new token has been sent to the same email address.",
          "Activation Failed!"
        );
      } else if (err.status === 400) { // Token invalide
        this.modalService.openWarning(
          "Invalid or missing activation token.",
          "Activation Failed!"
        );
      } 
        this.submitted = true;
         this.isOkay = false;
         console.log("****************Error",err);
        
       }
    });
  }

  redirectToLogin() {
    this.router.navigate(['/login']);
  }

  onCodeCompleted(token: string) {
    this.confirmAccount(token);
  }
  
}
