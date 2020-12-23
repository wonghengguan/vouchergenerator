import { Injectable } from '@angular/core';

/**
 * This is a singleton class
 */
@Injectable()
export class AppConfig {
  // Provide all the Application Configs here

  // API Related configs
  public apiPort:string;
  public apiProtocol:string;
  public apiHostName:string;
  public baseApiPath:string;
  public developerMode:boolean;

  constructor() {
    this.apiProtocol = "http:";
    this.apiHostName = "localhost";
    this.apiPort = "8080";

    this.baseApiPath = this.apiProtocol + "//" + this.apiHostName + ":" + this.apiPort +"/";

    this.developerMode = false;
  }

}
