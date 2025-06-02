import { IDepartement } from "./departments";
import { IROle } from "./role";

export interface IUser {
    id?:number;
    firstname?:string;
    lastname?:string;
    username?:string;
    email?:string;
    password?:string;
    enabled?:boolean;
    accountLocked?:boolean,
    roles?:IROle[],
    departments?:IDepartement,
}

export class User implements IUser {
   constructor(
    public id?: number,
    public firstname?: string,
    public lastname?: string,
    public username?:string,
    public email?: string,
    public password?: string,
    public enabled?: boolean,
    public accountLocked?: boolean,
    public roles?: IROle[],
    public departments?:IDepartement,
   ){}
    
}