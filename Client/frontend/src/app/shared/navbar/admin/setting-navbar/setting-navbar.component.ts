import { Component, OnDestroy, OnInit } from '@angular/core';
import { User } from '../../../../models/user';
import { AuthService } from '../../../../services/auth/auth.service';
import { TokenService } from '../../../../services/token/token.service';
import { Subscription } from 'rxjs';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-setting-navbar',
    imports: [RouterModule, CommonModule],
    templateUrl: './setting-navbar.component.html',
    styleUrl: './setting-navbar.component.css'
})
export class SettingNavbarComponent implements OnInit,OnDestroy {
    userRole: string | null = null;
      submitted = false;
      message = "";
      currentUser: User | null = null;
       subscription!: Subscription;

        constructor(
           private readonly authservice: AuthService,
           private readonly tokenService:TokenService
         ) { }
       
         ngOnInit(): void {
           this.subscription = this.authservice.currentUser$.subscribe(user => {
             console.log('Navbar reçoit utilisateur :', user);
             this.currentUser = user!;
           });
           this.getCurentUser();
         }
       
         IsUserLogging(): any {
           return this.authservice.isLoggedIn();
         }
       
         get userImage(): string {
           return 'assets/img/doctors/doctor-thumb-02.jpg'; // remplace dynamiquement si besoin
         }
       
         getCurentUser():any{
           if (this.tokenService.getToken()!) {
                   this.authservice.curentUser(this.tokenService.getToken()!).subscribe(
             {
               next: (res: any) => {
                this.currentUser=res;
                console.log('---------Curent-user-------------- ', this.currentUser?.roles![0].name);
               },
               error: (err: any) => {
              
               },
             }
           );
             
           }
         
         }
       
         logout() {
           
             this.authservice.logout()
           }
           
           ngOnDestroy(): void {
           if (this.subscription) this.subscription.unsubscribe();
         }

}
