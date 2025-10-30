import { Permission } from "./group-attributio-permission";

export interface IRole{
    id?:number,
    name?:string,
    permissions?:Permission[]
}

export class Role implements IRole{
    constructor(
        public id?:number,
        public name?:string,
        public  permissions?:Permission[]
    ){}
}

export class RoleRequest{
    name?:string;
    description?:string;
    permissions?:Permission[]
}