import { Injectable } from '@angular/core';
import {AppConfig} from "../app-config";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable, throwError} from "rxjs";
import 'rxjs/add/operator/catch';

@Injectable({
  providedIn: 'root'
})
export class ApiRequestService {

  constructor(
    public appConfig:AppConfig,
    public http: HttpClient,
    public router:Router
  ) { }

  getHeaders():HttpHeaders {
    let headers = new HttpHeaders();
    headers = headers.append('Content-Type', 'application/json');
    return headers;
  }

  post(url:string, body:Object):Observable<any>{
    let me = this;
    return this.http.post(this.appConfig.baseApiPath + url, JSON.stringify(body), { headers:this.getHeaders()})
      .catch(function(error:any){
        return throwError(error || 'Server error')
      });
  }
}
