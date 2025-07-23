import { Component } from '@angular/core';
import {TopNavbarComponent} from "../../navbar/top-navbar/top-navbar.component";
import {RouterOutlet} from "@angular/router";
import {FooterComponent} from "../../navbar/footer/footer.component";
import { DoccureStyleComponent } from '../doccure-style/doccure-style.component';

@Component({
    selector: 'app-skeletor',
    imports: [DoccureStyleComponent,
        TopNavbarComponent,
        RouterOutlet,
        FooterComponent,
        
    ],
    templateUrl: './skeletor.component.html',
    styleUrl: './skeletor.component.css',
    

})
export class SkeletorComponent   {
     constructor(
     
  ){}

}
