import { EntityStatus } from "./Enum/EntityStatus"
import { IServices } from "./services"

export interface IDepartement{
    id?:number;
    name?:string;
    description?:string;
    status?: EntityStatus;
    services?:IServices;
    userId?:number;
    leaveId?:number;

}

export class Departement implements IDepartement{
    constructor(
    public id?:number,
    public name?:string,
    public description?:string,
    public status?: EntityStatus,
    public services?:IServices,
    public userId?:number,
    public leaveId?:number,
    ){}
}