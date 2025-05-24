export interface IROle{
    name?:string
}

export class Role implements IROle{
    constructor(
        public name?:string,
    ){}
}