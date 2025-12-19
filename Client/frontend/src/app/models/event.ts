
import { EventCategory } from "./Enum/eventCategory";
import { Holiday } from "./holidays";
import { ICalendar } from "./calendar";

export interface IEvent{
     id?:number;
     title?:string;
    eventDate?:Date;
    category?: EventCategory;
    calendar?: ICalendar;
     relatedUserId?: number;
     holiday?: Holiday;
}

export class Event implements IEvent{
    constructor(
    public id?:number,
    public title?:string,
    public eventDate?:Date,
    public category?: EventCategory,
    public calendar?: ICalendar,
    public relatedUserId?: number,
    public holiday?: Holiday,
    ){}
}