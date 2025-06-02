import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {SharedModule} from "./shared/shared.module";
import {httpInterceptorProviders} from "./utils/helper/http.interceptor";
import { AdministrationModule } from "./administration/administration.module";
import { PaginationService } from "./shared/service/pagination.service";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";



@NgModule({
  declarations: [

  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    SharedModule,
    AdministrationModule,
    
    


  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [
    httpInterceptorProviders,
    PaginationService
  ],
  bootstrap: [],
})
export class AppModule {

 }
