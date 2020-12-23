import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { SpecialOfferComponent } from './special-offer/special-offer.component';
import { LoginComponent } from './login/login.component';
import {FormsModule} from "@angular/forms";
import {RecipientService} from "./services/recipient.service";
import {AppConfig} from "./app-config";
import {ApiRequestService} from "./services/api-request.service";
import {SpecialOfferService} from "./services/special-offer.service";
import {VoucherCodeService} from "./services/voucher-code.service";
import {HttpClientModule} from "@angular/common/http";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SpecialOfferComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    FormsModule
  ],
  providers: [RecipientService,AppConfig,ApiRequestService,SpecialOfferService,VoucherCodeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
