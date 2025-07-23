import { IDepartement } from "./departments";
import { Role } from "./role";

export interface IUser {
    id?:number;
    firstname?:string;
    lastname?:string;
    username?:string;
    email?:string;
    enabled?:boolean;
    accountLocked?:boolean,
    roles?:Role[],
    departments?:IDepartement,
}

export class User implements IUser {
   constructor(
    public id?: number,
    public firstname?: string,
    public lastname?: string,
    public username?:string,
    public email?: string,
    public enabled?: boolean,
    public accountLocked?: boolean,
    public roles?: Role[],
    public departments?:IDepartement,
   ){}
    
}