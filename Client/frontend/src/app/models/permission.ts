export interface IPermission {
    id?:number;
        description?:string;
    

}

export class Permission implements IPermission{
    constructor (
       public id?:number,
        public description?:string,
       
    ){}

}