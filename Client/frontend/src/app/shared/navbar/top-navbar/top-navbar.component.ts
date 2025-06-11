import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../services/auth/auth.service';
import { CommonModule } from '@angular/common';
import { NgxSpinnerService } from 'ngx-spinner';
import { ModalService } from '../../service/modal.service';

@Component({
  selector: 'app-top-navbar',
  imports: [RouterModule, CommonModule],
  templateUrl: './top-navbar.component.html',
  styleUrl: './top-navbar.component.css'
})
export class TopNavbarComponent implements OnInit {
  userRole: string | null = null;
  submitted = false;
  message = "";

  constructor(
    private readonly authservice: AuthService,
    private readonly route: Router,
    private readonly spinner: NgxSpinnerService,
    private readonly modalService: ModalService
  ) { }
  ngOnInit(): void {
    if (this.authservice.isLoggedIn()) {
      this.userRole = this.authservice.getUserRole();
    }
  }
  IsUserLogging(): any {
    return this.authservice.isLoggedIn();
  }

  get userImage(): string {
    return 'assets/img/doctors/doctor-thumb-02.jpg'; // remplace dynamiquement si besoin
  }

  logout(): any {

    localStorage.removeItem('token');
    this.authservice.logout().subscribe(
      {
        next: (res: any) => {
          console.log('value ////////////////////', res.access_token);
          this.clearSessionAndRedirect();
          this.spinner.hide();
          this.modalService.openWarning('Voulez-vous vraiment vous déconnecter ?', '', 100000);

        },
        error: (err: any) => {
          this.spinner.hide();
          this.clearSessionAndRedirect();
          //this.message = err.error;
         /// this.modalService.openWarning(this.message, 'Echec');
        },
      }
    );
  }

  // Méthode privée pour nettoyer + rediriger
  private clearSessionAndRedirect(): void {
    localStorage.removeItem('token'); // Nettoyage du token
    // Ajoute ici tout autre nettoyage si nécessaire (ex: user, rôles...)
    this.route.navigate(['/login'], {
      queryParams: { loggedOut: true }
    });
  }

}


