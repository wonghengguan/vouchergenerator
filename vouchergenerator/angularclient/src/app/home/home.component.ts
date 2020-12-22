import { Component, OnInit } from '@angular/core';
import {RecipientService} from "../services/recipient.service";
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";
import {VoucherCodeService} from "../services/voucher-code.service";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public recipientID:any;
  public recipient:any;
  public errormsg:any;
  public voucherCodeList:any;


  constructor(private titleService: Title,
              private router: Router,
              private cookieService: CookieService,
              private voucherCodeService: VoucherCodeService,
              private recipientService: RecipientService) { }

  ngOnInit(): void {
    this.getRecipient();
    this.getVoucherCodeList();
  }

  getRecipient() {
    this.recipientID = this.cookieService.get("recipientID");

    let userForm = {
      id:this.recipientID,
    }
    this.recipientService.getRecipient(userForm).subscribe( res => {
        if(res != null){
          this.recipient=res;
        } else {
          this.errormsg = "Unable to find recipient";
        }
      }
    )
  }

  getVoucherCodeList() {
    let voucherCodeForm = {
      recipientID:this.recipientID
    }
    this.voucherCodeService.getVoucherCodeByRecipientID(voucherCodeForm).subscribe(res=> {
        if(res != null) {
          this.voucherCodeList=res;
        } else {
          this.errormsg = "Recipient does not have any voucher code."
        }
      }
    )
  }

  useVoucherCode(voucherCodeID: any) {
    let voucherCodeForm = {
      id:voucherCodeID,
      recipientID:this.recipientID
    }
    this.voucherCodeService.getVoucherCodeByRecipientID(voucherCodeForm).subscribe(res=> {
        if(res != null) {
          this.voucherCodeList=res;
        } else {
          this.errormsg = "Recipient does not have any voucher code."
        }
      }
    )
  }

  viewSpecialOfferList() {
    this.router.navigate(['/specialOffers']);
  }
}
