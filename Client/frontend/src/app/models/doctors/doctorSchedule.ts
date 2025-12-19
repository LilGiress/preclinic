import e from "express";

export interface IDoctorSchedule {
    id?: number;
  doctorId?: number;
  doctorName?: string;
  departmentId?: number;
  departmentName?: string;
  availableDays?: string[]; // Array of day names
  startTime?: string;
  endTime?: string;
  message?: string;
  status?: string; // Active/Inactive
}

export class DoctorSchedule implements IDoctorSchedule {
    constructor(
        public id?: number,
        public doctorId?: number,
        public doctorName?: string,
        public departmentId?: number,
        public departmentName?: string,
        public availableDays?: string[],
        public startTime?: string,
        public endTime?: string,    
        public message?: string,
        public status?: string
    ) {}
}