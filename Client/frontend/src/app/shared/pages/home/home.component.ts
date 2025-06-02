import {Component, Inject, OnInit, Renderer2} from '@angular/core';
import {CommonModule, DOCUMENT} from "@angular/common";
import { Router, RouterModule } from '@angular/router';
import { SearchService } from '../../../services/search.service';
import { FormsModule } from '@angular/forms';

@Component({
    selector: 'app-home',
    imports: [RouterModule, FormsModule,CommonModule],
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
    private readonly renderer2:Renderer2,
    private readonly route:Router,
    @Inject(DOCUMENT) private readonly _document:Document,
    private readonly searchService: SearchService,
  //  @Inject(PLATFORM_ID) private  readonly platformId: Object
    

  ){}
 /* ngAfterViewInit() {
  if (isPlatformBrowser(this.platformId)) {
    // Initialiser slick-carousel ici
    //this.loadScript('assets/js/slick.js');
      this.loadScript('assets/js/script.js');
  }
}*/
/* private loadScript(src: string): void {
    const script = this.renderer2.createElement('script');
    script.type = 'text/javascript';
    script.src = src;
    script.defer = true;
    this.renderer2.appendChild(this._document.body, script);
  }*/

 
  specialities = [
    { label: 'Urology', image: 'assets/img/specialities/specialities-01.png' },
    { label: 'Neurology', image: 'assets/img/specialities/specialities-02.png' },
    { label: 'Orthopedic', image: 'assets/img/specialities/specialities-03.png' },
    { label: 'Cardiologist', image: 'assets/img/specialities/specialities-04.png' },
    { label: 'Dentist', image: 'assets/img/specialities/specialities-05.png' },
  ];
  



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
