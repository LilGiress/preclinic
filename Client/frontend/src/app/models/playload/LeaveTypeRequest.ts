import {EntityStatus} from "../Enum/EntityStatus";

export class LeaveTypeRequest {
    leaveType?:string;
    leaveDays?:number;
    status?:EntityStatus
    description?:string;
}
