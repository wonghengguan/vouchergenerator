import { Component, OnInit } from '@angular/core';
import {Title} from "@angular/platform-browser";
import {CookieService} from "ngx-cookie-service";
import {Router} from "@angular/router";
import {SpecialOfferService} from "../services/special-offer.service";
import {VoucherCodeService} from "../services/voucher-code.service";

@Component({
  selector: 'app-special-offer',
  templateUrl: './special-offer.component.html',
  styleUrls: ['./special-offer.component.css']
})
export class SpecialOfferComponent implements OnInit {
  public recipientID:any;
  public recipient:any;
  public specialOfferList:any;
  public name:any;
  public discountPercentage:any;
  public errormsg:any;

  constructor(private titleService: Title,
              private cookieService: CookieService,
              private router: Router,
              private specialOfferService: SpecialOfferService,
              private voucherCodeService: VoucherCodeService) { }

  ngOnInit(): void {
    this.recipientID = this.cookieService.get("recipientID");
    this.getSpecialOfferList();
  }

  getSpecialOfferList() {
    let form = {}
    this.specialOfferService.getSpecialOfferList(form).subscribe( res=> {
        if(res!=null) {
          this.specialOfferList = res;
        } else {
            this.errormsg="There are no special offer available.";
        }
      }
    )
  }

  newSpecialOffer() {
    let form = {
      name:this.name,
      discountPercentage:this.discountPercentage
    }
    this.specialOfferService.newSpecialOffer(form).subscribe( res=> {
        if(res!=null) {
          this.specialOfferList = res;
        }
      }
    )
  }

  generateVoucherCode(specialOfferID: any) {
    let form ={
      specialOfferID:specialOfferID,
      recipientID:this.recipientID
    }
    this.voucherCodeService.generateVoucherCode(form).subscribe( res=> {
          if(res!=null) {
            this.specialOfferList = res;
          } else {

          }
      }
    )
  }

  backToHome() {
    this.router.navigate(['/home']);
  }
}
