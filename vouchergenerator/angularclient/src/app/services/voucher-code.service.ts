import { Injectable } from '@angular/core';
import {Router} from "@angular/router";
import {ApiRequestService} from "./api-request.service";

@Injectable({
  providedIn: 'root'
})
export class VoucherCodeService {

  constructor(private router: Router,
              private apiService: ApiRequestService) { }

  getVoucherCodeByRecipientID(body: Object) {
    return this.apiService.post("api/voucherCode/getVoucherCodeListByRecipientID", body);
  }

  useVoucherCode(body: Object) {
    return this.apiService.post("api/voucherCode/getVoucherCodeListByRecipientID", body);
  }

  generateVoucherCode(body: Object) {
    return this.apiService.post("api/voucherCode/generateVoucherCode", body);
  }
}
