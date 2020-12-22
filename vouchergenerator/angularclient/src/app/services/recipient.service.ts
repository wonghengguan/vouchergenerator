import { Injectable } from '@angular/core';
import {Router} from "@angular/router";
import {ApiRequestService} from "./api-request.service";

@Injectable({
  providedIn: 'root'
})
export class RecipientService {

  constructor(private router: Router,
              private apiService: ApiRequestService) { }

  login(body: Object) {
    return this.apiService.post("api/recipient/getRecipientByEmail", body);
  }

  getRecipient(body: Object) {
    return this.apiService.post("api/recipient/getRecipientByEmail", body);
  }

  logout() {
    this.router.navigate(['/login']);
  }
}
