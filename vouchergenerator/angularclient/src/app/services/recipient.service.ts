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

  getRecipientByID(body: Object) {
    return this.apiService.post("api/recipient/getRecipientByID", body);
  }

  register(body: Object) {
    return this.apiService.post("api/recipient/newRecipient", body);
  }

  getRecipientByEmail(body: Object) {
    return this.apiService.post("api/recipient/getRecipientByEmail", body);
  }

}
