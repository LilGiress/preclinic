import { isPlatformBrowser } from '@angular/common';
import {Component, Inject, OnInit, PLATFORM_ID, Renderer2, ViewEncapsulation} from '@angular/core';
declare let $: any;
@Component({
    selector: 'app-admin-styl',
    imports: [],
    templateUrl: './admin-styl.component.html',
    styleUrl: './admin-styl.component.css',
})
export class AdminStylComponent implements OnInit{
  constructor(
     private readonly renderer:Renderer2,
     @Inject(PLATFORM_ID) private readonly platformId:object
  ){}

  ngOnInit(): void {
     Promise.all([
      this.loadStyle('assets/preclinic/css/bootstrap.min.css'),
      this.loadStyle('assets/preclinic/css/font-awesome.min.css'),
        this.loadStyle('assets/preclinic/css/style.css'),
        this.loadStyle('assets/preclinic/css/bootstrap-datetimepicker.min.css'),
         this.loadStyle('assets/preclinic/css/dataTables.bootstrap4.min.css'),
         this.loadStyle('assets/preclinic/css/fullcalendar.min.css'),
       this.loadStyle('assets/preclinic/css/select2.min.css'),
       this.loadStyle('assets/preclinic/tagsinput.css'),

     ])
     .then(() => {
       console.log('All style loaded successfully!');
     })
     .catch((err) => {
       console.error('Error loading style:', err);
     });


     Promise.all([
       this.loadScript('assets/preclinic/js/jquery-3.2.1.min.js'),
       this.loadScript('assets/preclinic/js/popper.min.js'),
        this.loadScript('assets/preclinic/js/bootstrap.min.js'),
        this.loadScript('assets/preclinic/js/jquery.slimscroll.js'),
        //this.loadScript('assets/preclinic/js/Chart.bundle.js'),
        //this.loadScript('assets/preclinic/js/chart.js'),
       this.loadScript('assets/preclinic/js/moment.min.js'),
        this.loadScript('assets/preclinic/js/bootstrap-datetimepicker.min.js'),
       this.loadScript('assets/preclinic/js/jquery.dataTables.min.js'),
       this.loadScript('assets/preclinic/js/dataTables.bootstrap4.min.js'),
       this.loadScript('assets/preclinic/js/fullcalendar.min.js'),
      this.loadScript('assets/preclinic/js/select2.min.js'),
      this.loadScript('assets/preclinic/js/tagsinput.js'),
      this.loadScript('assets/preclinic/plugins/summernote/dist/summernote-bs4.min.js'),
      this.loadScript('assets/preclinic/plugins/light-gallery/js/lightgallery-all.min.js'),
       this.loadScript('assets/preclinic/js/app.js'),

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
