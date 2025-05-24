import {
  HTTP_INTERCEPTORS,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpInterceptorFn,
  HttpRequest
} from '@angular/common/http';
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";

@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor{
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    req = req.clone({
      withCredentials:true,
    })

    return next.handle(req);
  }

}

export const httpInterceptorProviders= [
  {
    provide:HTTP_INTERCEPTORS,useClass:HttpRequestInterceptor, multi:true
  }
]
