import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AlertManagerService {

  private alertmanagerUrl = 'http://localhost:9093/api/v2/alerts';

  constructor(private http: HttpClient) { }

  getAlerts(): Observable<any> {
    return this.http.get<any>(this.alertmanagerUrl);
  }
}
