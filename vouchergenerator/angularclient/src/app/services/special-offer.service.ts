import { Injectable } from '@angular/core';
import {Router} from "@angular/router";
import {ApiRequestService} from "./api-request.service";

@Injectable({
  providedIn: 'root'
})
export class SpecialOfferService {

  constructor(private router: Router,
              private apiService: ApiRequestService) { }

  getSpecialOfferList(body: Object) {
    return this.apiService.post("api/specialOffer/getAllSpecialOffer", body);
  }

  newSpecialOffer(body: Object) {
    return this.apiService.post("api/specialOffer/newSpecialOffer", body);
  }
}
