import {LeaveType} from "./leaveType";
import {LeaveStatus} from "./Enum/LeaveStatus";

export interface ILeave {
  id?:number,
  startDate?:Date,
  endDate?:Date,
  leaveReason?:string,
  employeeId?:number,
  RemainingLeave?:number,
   numberOfDays?:number,
  leaveType?: LeaveType,
  status?:LeaveStatus
}

export class Leave implements ILeave {
  constructor(public id:number,
              public startDate?:Date,
              public endDate?:Date,
              public leaveReason?:string,
              public employeeId?:number,
             public RemainingLeave?:number,
             public numberOfDays?:number,
              public leaveType?: LeaveType,
              public status?:LeaveStatus
              ) {
  }
}
