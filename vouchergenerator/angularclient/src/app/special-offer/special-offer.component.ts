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
  public recipientEmail:any;
  public recipient:any;
  public specialOfferList:any;
  public name:any;
  public discountPercentage:any;
  public errormsg:any;
  public isNew:any;
  public canCreate:any;

  constructor(private titleService: Title,
              private cookieService: CookieService,
              private router: Router,
              private specialOfferService: SpecialOfferService,
              private voucherCodeService: VoucherCodeService) { }

  ngOnInit(): void {
    this.recipientEmail = this.cookieService.get("recipientEmail");
    this.getSpecialOfferList();
    this.isNew = false;
    this.canCreate = this.recipientEmail=="admin";
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

  new() {
    this.isNew = true;
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
    this.isNew = false;
  }

  generateVoucherCode(specialOfferID: any) {

    let form ={
      specialOfferID:specialOfferID,
      email:this.recipientEmail
    }
    this.voucherCodeService.generateVoucherCode(form).subscribe( res=> {
          if(res!=null) {
            this.specialOfferList = res;
          } else {

          }
      }
    )
  }

  logout() {
    this.cookieService.delete("recipientID");
    this.router.navigate(['']);
  }
}