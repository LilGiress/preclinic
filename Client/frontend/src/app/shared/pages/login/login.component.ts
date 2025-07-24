import { Component, inject, OnInit, Output } from '@angular/core';
import { AbstractControl, FormBuilder, ReactiveFormsModule, Validators } from "@angular/forms";

import { AuthService } from "../../../services/auth/auth.service";
import { TokenService } from "../../../services/token/token.service";
import { Router, RouterModule } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { CommonModule } from '@angular/common';
import { ModalService } from '../../service/modal.service';
import { User } from '../../../models/user';
import { AuthenticationRequest } from '../../../models/playload/authentication-request';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
  private readonly fb = inject(FormBuilder);

  roles: string[] = [];

  submitted = false;
  message = "";
  user?: User={};
  constructor(
    private readonly route: Router,
    private readonly authservice: AuthService,
    private readonly tokenservie: TokenService,
    private readonly spinner: NgxSpinnerService,
    private readonly modalService: ModalService
  ) {

  }

  loginForm = this.fb.group(
    {

      email: ['', [Validators.required, Validators.email]],
      password: ['', [
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

  onSubmit(): void {
    this.submitted = true;
    if (this.loginForm.valid) {
      let login:AuthenticationRequest={
        email:this.loginForm.get('email')?.value ?? '',
        password:this.loginForm.get('password')?.value ?? '',
        fingerprint:this.generateFingerprint(),
      };
      this.spinner.show();
      this.authservice.login(login).subscribe(
        {
          next: (user: any) => {
              this.spinner.hide();

              this.modalService.openSuccessModal(
                'Connexion réussie',
                () => this.route.navigate(['/home'])
              );

              this.onReset();
            
          },
          error: (err: any) => {
            this.spinner.hide();
            this.message = err.error.error;
            // Gérer différents types d’erreurs
            if (err.status === 0) {
              this.message = 'Erreur réseau. Veuillez vérifier votre connexion.';
            } else if (err.status === 401) {
              this.message = 'Identifiants incorrects.';
            } else if (err.error && typeof err.error === 'string') {
              this.message = err.error.error;
            } else {
              this.message = 'Une erreur est survenue.';
            }
            this.modalService.openWarning(this.message, 'Échec de la connexion');
          },
        }
      )

    }
  }


  onReset(): void {
    this.submitted = false;
    this.loginForm.reset();
  }

  generateFingerprint(): string {
  return btoa(navigator.userAgent + screen.width + screen.height);
}
}
