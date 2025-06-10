import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../services/auth/auth.service';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-top-navbar',
    imports: [RouterModule,CommonModule],
    templateUrl: './top-navbar.component.html',
    styleUrl: './top-navbar.component.css'
})
export class TopNavbarComponent implements OnInit {
    userRole: string | null = null;

    constructor(
         private readonly authservice:AuthService,
         private readonly route:Router
    ){}
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

  logout():any{
 
     localStorage.removeItem('token');
    this.authservice.logout().subscribe(() => {
   this.route.navigate(['/login']);
});
  }

    }
    

