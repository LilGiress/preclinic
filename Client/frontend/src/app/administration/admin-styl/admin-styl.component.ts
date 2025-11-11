
import {Component, OnInit,} from '@angular/core';
import {ScriptLoaderService} from "../../utils/script-loader.service";

declare let $: any;
@Component({
    selector: 'app-admin-styl',
    imports: [],
    templateUrl: './admin-styl.component.html',
    styleUrl: './admin-styl.component.css',
})
export class AdminStylComponent implements OnInit{
  constructor(
     private readonly scriptloader:ScriptLoaderService,
  ){}

 async ngOnInit(): Promise<void> {
     Promise.all([
      this.scriptloader.loadStyle('assets/preclinic/css/bootstrap.min.css'),
      this.scriptloader.loadStyle('assets/preclinic/css/font-awesome.min.css'),
        this.scriptloader.loadStyle('assets/preclinic/css/style.css'),
        this.scriptloader.loadStyle('assets/preclinic/css/bootstrap-datetimepicker.min.css'),
         this.scriptloader.loadStyle('assets/preclinic/css/dataTables.bootstrap4.min.css'),
         this.scriptloader.loadStyle('assets/preclinic/css/fullcalendar.min.css'),
       this.scriptloader.loadStyle('assets/preclinic/css/select2.min.css'),
       this.scriptloader.loadStyle('assets/preclinic/tagsinput.css'),

     ]);

     await this.scriptloader.loadScriptsSequentially([
       'assets/preclinic/js/jquery-3.2.1.min.js',
       'assets/preclinic/js/popper.min.js',
       'assets/preclinic/js/bootstrap.min.js',

       'assets/preclinic/js/moment.min.js',
       'assets/preclinic/js/bootstrap-datetimepicker.min.js',

       'assets/preclinic/js/jquery.slimscroll.js',

       'assets/preclinic/js/jquery.dataTables.min.js',
       'assets/preclinic/js/dataTables.bootstrap4.min.js',

       'assets/preclinic/js/fullcalendar.min.js',

       'assets/preclinic/js/select2.min.js',
       'assets/preclinic/js/tagsinput.js',

       'assets/preclinic/plugins/summernote/dist/summernote-bs4.min.js',
       'assets/preclinic/plugins/light-gallery/js/lightgallery-all.min.js',

       // ✅ DERNIER
       'assets/preclinic/js/app.js'
     ])



  }





}
