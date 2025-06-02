import { isPlatformBrowser } from '@angular/common';
import {Component, Inject, OnInit, PLATFORM_ID, Renderer2, ViewEncapsulation} from '@angular/core';
declare var $: any;
@Component({
    selector: 'app-admin-styl',
    imports: [],
    templateUrl: './admin-styl.component.html',
    styleUrl: './admin-styl.component.css',
    encapsulation: ViewEncapsulation.None
})
export class AdminStylComponent implements OnInit{
  constructor(
    private renderer:Renderer2,
    @Inject(PLATFORM_ID) private platformId:object
  ){}

  ngOnInit(): void {
    Promise.all([
      this.loadStyle('assets/preclinic/bootstrap-datetimepicker.min.css'),
      this.loadStyle('/assets/preclinic/bootstrap.min.css'),
      this.loadStyle('assets/preclinic/dataTables.bootstrap4.min.css'),
      this.loadStyle('assets/preclinic/select2.min.css'),
      this.loadStyle('assets/preclinic/style.css'),
      this.loadStyle('assets/preclinic/tagsinput.css'),

    ])
    .then(() => {
      console.log('All style loaded successfully!');
    })
    .catch((err) => {
      console.error('Error loading style:', err);
    });
    

    Promise.all([
     // this.loadScript('assets/js/preclinic/jquery-3.2.1.min.js'),
      this.loadScript('assets/js/preclinic/popper.min.js'),
      this.loadScript('assets/js/preclinic/jquery.slimscroll.js'),
      this.loadScript('assets/js/preclinic/select2.min.js'),
      this.loadScript('assets/js/preclinic/moment.min.js'),
      this.loadScript('assets/js/preclinic/jquery-ui.min.html'),
      this.loadScript('assets/js/preclinic/bootstrap-datetimepicker.min.js'),
      this.loadScript('assets/js/preclinic/chart.js'),
    // this.loadScript('assets/js/preclinic/bootstrap.min.js'),
      this.loadScript('assets/js/preclinic/Chart.bundle.js'),
      this.loadScript('assets/js/preclinic/dataTables.bootstrap4.min.js'),
      this.loadScript('assets/js/preclinic/jquery.dataTables.min.js'),
      this.loadScript('assets/js/preclinic/tagsinput.js'),
      this.loadScript('assets/js/preclinic/app.js'),
    
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
