import {Component, Inject, OnInit, Renderer2} from '@angular/core';
import {DOCUMENT} from "@angular/common";
import { Router, RouterModule } from '@angular/router';
import { SearchService } from '../../../services/search.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterModule,FormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{
  region: string = '';
  city: string = '';
  service: string = '';
  doctors: any[] = [];
  errorMessage: string = '';
  constructor(
    private renderer2:Renderer2,
    private route:Router,
    @Inject(DOCUMENT) private _document:Document,
    private searchService: SearchService,
    

  ){}


  ngOnInit(): void {
    const s = this.renderer2.createElement('script');
    s.type = 'text/javascript';
    s.src = 'assets/js/slick.js';
    s.src = 'assets/js/script.js';
    this.renderer2.appendChild(this._document.body, s);
  }
  onSearch() {
    this.route.navigate(['/search'])
    }


  
  

  search() {
    this.searchService.search(this.region, this.city, this.service).subscribe(data => {
      if (data.error) {
        this.errorMessage = data.error;
        this.doctors = [];
        this.route.navigate(['/search'])
      } else {
        this.errorMessage = '';
        this.doctors = data.doctors;
      }
    });
  }
}
