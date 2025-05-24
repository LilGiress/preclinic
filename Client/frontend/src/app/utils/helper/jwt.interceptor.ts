import {HttpEvent, HttpHandler, HttpInterceptor, HttpInterceptorFn, HttpRequest} from '@angular/common/http';
import {Observable} from "rxjs";
import {TokenService} from "../../services/token/token.service";
import {Injectable} from "@angular/core";

@Injectable()
export class JwtInterceptor implements HttpInterceptor{

  constructor(private tokenservice : TokenService){}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.tokenservice.getToken();
    if (token) {
      req = req.clone({
        setHeaders:{
          Authorization: `Bearer ${token}`
        },
      });
    }
    return next.handle(req);
  }

}
