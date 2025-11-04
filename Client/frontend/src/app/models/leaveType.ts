import {EntityStatus} from "./Enum/EntityStatus";

export interface ILeaveType {
id?:number,
  leaveType?:string,
  leaveDays?:number,
  status?:EntityStatus
}

export class LeaveType implements ILeaveType{
  constructor(
    public id?:number,
    public leaveType?:string,
   public leaveDays?:number,
   public status?:EntityStatus
  ) {

  }
}
