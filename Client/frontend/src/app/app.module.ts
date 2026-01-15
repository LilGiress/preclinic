import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {SharedModule} from "./shared/shared.module";
import {httpInterceptorProviders} from "./utils/helper/http.interceptor";
import { AdministrationModule } from "./administration/administration.module";
import { PaginationService } from "./services/pagination.service";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { AuthInterceptor } from "./utils/core/auth.interceptor";
import { HTTP_INTERCEPTORS } from "@angular/common/http";



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
    AuthInterceptor,
    PaginationService
    
  ],
  bootstrap: [],
})
export class AppModule {

 }
