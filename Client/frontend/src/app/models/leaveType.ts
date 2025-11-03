import {EntityStatus} from "./Enum/EntityStatus";

export interface ILeaveType {
id?:number,
  leavetype?:string,
  leaveDays?:number,
  status?:EntityStatus
}

export class LeaveType implements ILeaveType{
  constructor(
    public id?:number,
    public leavetype?:string,
   public leaveDays?:number,
   public status?:EntityStatus
  ) {

  }
}
