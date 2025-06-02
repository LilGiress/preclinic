import { IDepartement } from "./departments";

export interface IServices{
    id?:number;
    name?:string;
    description?:string;
    medicalRecordId?:number;
    departement?:IDepartement;

}

export class Services implements IServices{
    constructor(
    public id?:number,
    public name?:string,
    public description?:string,
    public medicalRecordId?:number,
    public departement?:IDepartement
    ){}

}