import { isPlatformBrowser } from '@angular/common';
import { Component, Inject, OnInit, PLATFORM_ID, Renderer2, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-doccure-style',
  imports: [],
  templateUrl: './doccure-style.component.html',
  styleUrl: './doccure-style.component.css',
  encapsulation: ViewEncapsulation.Emulated
})
export class DoccureStyleComponent implements OnInit {

  constructor(private readonly renderer:Renderer2,
     @Inject(PLATFORM_ID) private readonly platformId:object){}


  ngOnInit(): void {
     Promise.all([
      this.loadStyle('assets/doccure/css/bootstrap.min.css'),
      this.loadStyle('assets/doccure/plugins/fontawesome/css/all.min.css'),
       this.loadStyle('assets/doccure/plugins/fontawesome/css/fontawesome.min.css'),
       this.loadStyle('assets/doccure/css/style.css'),
         this.loadStyle('assets/doccure/css/bootstrap-datetimepicker.min.css'),
       this.loadStyle('assets/doccure/plugins/bootstrap-tagsinput/css/bootstrap-tagsinput.css'),
       this.loadStyle('assets/doccure/plugins/fancybox/jquery.fancybox.min.css'),
       this.loadStyle('assets/doccure/plugins/fullcalendar/fullcalendar.min.css'),
       this.loadStyle('assets/doccure/plugins/select2/css/select2.min.css'),

       

     ])
     .then(() => {
       console.log('All style loaded successfully!');
     })
     .catch((err) => {
       console.error('Error loading style:', err);
     });
    

     Promise.all([
      this.loadScript('assets/doccure/js/script.js'),
      this.loadScript('assets/doccure/js/jquery.min.js'),
      this.loadScript('assets/doccure/js/slick.js'),
       this.loadScript('assets/doccure/js/profile-settings.js'),
       this.loadScript('assets/doccure/js/popper.min.js'),
       this.loadScript('assets/doccure/js/moment.min.js'),
       this.loadScript('assets/doccure/js/circle-progress.min.js'),
       this.loadScript('assets/doccure/js/bootstrap.min.js'),
       this.loadScript('assets/doccure/js/bootstrap-datetimepicker.min.js'),
        this.loadScript('assets/doccure/plugins/jquery-ui/jquery-ui.min.js'),
        this.loadScript('assets/doccure/plugins/fancybox/jquery.fancybox.min.js'),
        this.loadScript('assets/doccure/plugins/dropzone/dropzone.min.js'),
         this.loadScript('assets/doccure/plugins/fullcalendar/fullcalendar.min.js'),
         this.loadScript('assets/doccure/plugins/fullcalendar/jquery.fullcalendar.js'),
         this.loadScript('assets/doccure/plugins/select2/js/select2.min.js'),
         this.loadScript('assets/doccure/plugins/theia-sticky-sidebar/ResizeSensor.js'),
         this.loadScript('assets/doccure/plugins/theia-sticky-sidebar/theia-sticky-sidebar.js'),
    
     ])
       .then(() => {
         console.log('All scripts loaded successfully!');
       })
       .catch((err) => {
         console.error('Error loading scripts:', err);
       });

  }

   loadScript(src: string): Promise<void> {
     return new Promise<void>((resolve, reject) => {
       if (isPlatformBrowser(this.platformId)) {
         // Exécuter uniquement dans le navigateur
         const script = document.createElement('script');
         script.src = src;
         script.type = 'text/javascript';
         script.async = true;

         script.onload = () => {
           console.log(`${src} loaded successfully.`);
           resolve();
         };

         script.onerror = () => {
           reject(new Error(`Failed to load script: ${src}`));
         };

         document.body.appendChild(script);
       } else {
         console.warn('Script loading skipped: Not running in the browser.');
         resolve(); // Résoudre la promesse pour éviter les blocages côté serveur
       }
     });
   }



   loadStyle(href: string): void {
     if (isPlatformBrowser(this.platformId)) {
       // Exécuter uniquement dans le navigateur
       const link = document.createElement('link');
       link.rel = 'stylesheet';
       link.href = href;
       link.type = 'text/css';
       document.head.appendChild(link);
       console.log(`${href} style loaded successfully.`);
     } else {
       console.warn('Style loading skipped: Not running in the browser.');
     }
   }

}
