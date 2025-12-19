import { IEvent } from "./event";

export interface ICalendar{
    id?:number;
    name?:string;
    events?:IEvent[];
    userId?:number;
}

export class Calendar implements ICalendar{
    constructor(
        public id?:number,
        public name?:string,
        public events?:IEvent[],
        public userId?:number
    ){}
}    