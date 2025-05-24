export interface Permission {
    read?: boolean;
    write?: boolean;
    delete?: boolean;
    import?: boolean;
    export?: boolean;
  }

  /*export class Permission implements IPermission{
    constructor(
    public read?: boolean,
    public write?: boolean,
    public delete?: boolean,
    public import?: boolean,
    public export?: boolean,
    ){}
  }*/