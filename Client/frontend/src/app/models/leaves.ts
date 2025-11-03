import {LeaveType} from "./leaveType";

export interface ILeave {
  id?:number,
  startDate?:Date,
  endDate?:Date,
  leaveReason?:string,
  employeeId?:number,
  leaveType?: LeaveType[]
}

export class Leave implements ILeave {
  constructor(public id:number,
              public startDate?:Date,
              public endDate?:Date,
              public leaveReason?:string,
              public employeeId?:number,
              public leaveType?: LeaveType[]
              ) {
  }
}
