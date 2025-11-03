import {EntityStatus} from "../Enum/EntityStatus";

export class LeaveTypeRequest {
    leaveType?:String;
    leaveDays?:Number;
    status?:EntityStatus
}
