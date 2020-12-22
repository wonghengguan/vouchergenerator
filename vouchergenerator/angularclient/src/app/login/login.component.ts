import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";
import {RecipientService} from "../services/recipient.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public email: any;
  public errorMsg: any;

  constructor(private router: Router,
              private cookieService: CookieService,
              private recipientService: RecipientService) { }

  ngOnInit(): void {

  }

  onSubmit() {
    this.errorMsg = undefined;

    if (this.email == undefined || this.email == '') {
      this.errorMsg = 'please enter email.';
      return;
    }

    let form = {
      email: this.email
    };

    this.recipientService.login(form).subscribe(data => {
      if (data == null) {
        this.errorMsg = 'User not found!';
      } else if (data != null) {
        this.cookieService.set("recipientID", data, 30,"/");
        this.router.navigate(['/home']);
      }
    });
  }
}
