import { Role } from "../role";

export class RegistrationRequest {
      firstname?:string;
      lastname?:string;
      email?:string;
      password?:string;
      roles?:Role[]=[] ;
      departments?:number[];

}
