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
  public name:any;
  public errorMsg: any;
  public isRegister:any;
  public successMsg:any;
  public registrationErrorMsg:any;

  constructor(private router: Router,
              private cookieService: CookieService,
              private recipientService: RecipientService) { }

  ngOnInit(): void {
    this.isRegister = false;
  }

  onSubmit() {
    this.errorMsg = "";
    this.registrationErrorMsg = "";
    this.successMsg = "";

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
        this.cookieService.set("recipientEmail", data.email, 30,"/");
        if(data.email!="admin") {
          this.router.navigate(['/home']);
        } else {
          this.router.navigate(['/specialOffers']);
        }
      }
    });
  }

  register() {
    this.isRegister = true;
    this.registrationErrorMsg = "";
    this.successMsg = "";
    this.errorMsg = "";
  }

  submitRegistration() {
    if (this.name == undefined || this.email == undefined) {
      this.errorMsg = "Please enter name and email to register.";
    }
    else if (!/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(this.email) && this.email!="admin")
    {
      this.errorMsg = "Please enter a valid email address.";
    }
    else {
      this.registrationErrorMsg = "";
      this.successMsg = "";

      let form = {
        name: this.name,
        email: this.email
      };

      this.recipientService.register(form).subscribe(data => {
        if (data != null) {
          if (data.exists) {
            this.registrationErrorMsg = "Email was registered before this. Please enter the email to login."
            this.errorMsg = "";
            this.successMsg = "";
          } else {
            this.successMsg = "Registration was successful. Please enter the email to login."
            this.errorMsg = "";
            this.registrationErrorMsg = "";
          }
        }
      });
      this.isRegister = false;
      this.name="";
      this.email="";
    }
  }
}
