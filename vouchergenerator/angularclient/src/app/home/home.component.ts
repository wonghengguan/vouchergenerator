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
  public email:any;
  public recipient:any;
  public errormsg:any;
  public validVoucherCodeList:any;
  public expiredVoucherCodeList:any;
  public redeemedVoucherCodeList:any;
  public voucherCodeSuccessMsg:any;
  public voucherCodeFailMsg:any;
  public redeem:any;
  public voucherCode:any;



  constructor(private titleService: Title,
              private router: Router,
              private cookieService: CookieService,
              private voucherCodeService: VoucherCodeService,
              private recipientService: RecipientService) { }

  ngOnInit(): void {
    this.redeem = false;
    this.voucherCodeSuccessMsg = "";
    this.getRecipient();
    this.getValidVoucherForRecipient();
    this.getExpiredVoucherForRecipient();
    this.getRedeemedVoucherForRecipient();
  }

  getRecipient() {
    this.email = this.cookieService.get("recipientEmail");

    let userForm = {
      email:this.email,
    }
    this.recipientService.getRecipientByEmail(userForm).subscribe(res => {
        if(res != null){
          this.recipient=res;
        } else {
          this.errormsg = "Unable to find recipient";
        }
      }
    )
  }

  getValidVoucherForRecipient() {
    let voucherCodeForm = {
      email:this.email
    }
    this.voucherCodeService.getValidVoucherForRecipient(voucherCodeForm).subscribe(res=> {
        if(res != null) {
          this.validVoucherCodeList=res;
        } else {
          this.errormsg = "Recipient does not have any voucher code. Please contact admin for more information."
        }
      }
    )
  }

  getExpiredVoucherForRecipient() {
    let voucherCodeForm = {
      email:this.email
    }
    this.voucherCodeService.getExpiredVoucherForRecipient(voucherCodeForm).subscribe(res=> {
        if(res != null) {
          this.expiredVoucherCodeList=res;
        } else {
          this.errormsg = "Recipient does not have any voucher code. Please contact admin for more information."
        }
      }
    )
  }

  getRedeemedVoucherForRecipient() {
    let voucherCodeForm = {
      email:this.email
    }
    this.voucherCodeService.getRedeemedVoucherForRecipient(voucherCodeForm).subscribe(res=> {
        if(res != null) {
          this.redeemedVoucherCodeList=res;
        } else {
          this.errormsg = "Recipient does not have any voucher code. Please contact admin for more information."
        }
      }
    )
  }

  useVoucherCode() {
    this.redeem = true;
    this.voucherCode="";
    this.voucherCodeSuccessMsg="";
    this.voucherCodeFailMsg="";
  }

  submitVoucherCode() {
    console.log("code = "  +this.voucherCode)
    console.log("email = " + this.email)
    let voucherCodeForm = {
      code:this.voucherCode,
      email:this.email
    }
    this.voucherCodeService.useVoucherCode(voucherCodeForm).subscribe(res=> {
        if(res != null) {
          this.validVoucherCodeList=res.validVoucherCodeList;
          this.redeemedVoucherCodeList=res.redeemedVoucherCodeList;
          this.expiredVoucherCodeList=res.expiredVoucherCodeList;
          if(res.voucherCodeExists){
            this.voucherCodeSuccessMsg="Redeem success!";
          } else {
            this.voucherCodeFailMsg="Invalid voucher code!"
          }
        }
      }
    )
    this.redeem = false;
  }

  viewSpecialOfferList() {
    this.router.navigate(['/specialOffers']);
  }

  logout() {
    this.cookieService.delete("recipientID");
    this.router.navigate(['']);
  }
}
