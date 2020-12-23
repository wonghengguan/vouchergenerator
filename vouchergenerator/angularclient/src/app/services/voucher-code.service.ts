import { Injectable } from '@angular/core';
import {Router} from "@angular/router";
import {ApiRequestService} from "./api-request.service";

@Injectable({
  providedIn: 'root'
})
export class VoucherCodeService {

  constructor(private router: Router,
              private apiService: ApiRequestService) { }

  getValidVoucherForRecipient(body: Object) {
    return this.apiService.post("api/voucherCode/getValidVoucherForRecipient", body);
  }

  getExpiredVoucherForRecipient(body: Object) {
    return this.apiService.post("api/voucherCode/getExpiredVoucherForRecipient", body);
  }

  getRedeemedVoucherForRecipient(body: Object) {
    return this.apiService.post("api/voucherCode/getRedeemedVoucherForRecipient", body);
  }

  useVoucherCode(body: Object) {
    return this.apiService.post("api/voucherCode/useVoucherCode", body);
  }

  generateAll(body: Object) {
    return this.apiService.post("api/voucherCode/generateAll", body);
  }

  generateVoucherCode(body: Object) {
    return this.apiService.post("api/voucherCode/generateVoucherCodeForSpecialOffer", body);
  }
}
