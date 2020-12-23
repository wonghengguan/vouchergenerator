import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {HomeComponent} from "./home/home.component";
import {SpecialOfferComponent} from "./special-offer/special-offer.component";

const routes: Routes = [
  {
    path: '', component: LoginComponent, data: { title: 'Voucher pool - Login' }
  },
  {
    path: '#/**', component: LoginComponent, data: { title: 'Voucher pool - Login' }
  },
  {
    path: 'login', component: LoginComponent, data: { title: 'Voucher pool - Login' }
  },
  {
    path: 'home', component: HomeComponent, data: { title: 'Voucher pool - Home' }
  },
  {
    path: 'specialOffers', component: SpecialOfferComponent, data: { title: 'Voucher pool - Special offers' }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash:true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
