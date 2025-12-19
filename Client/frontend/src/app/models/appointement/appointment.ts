export interface IAppointment {
    id?: number;
  appointmentId?: string;
  patientId?: number;
  patientName?: string;
  patientEmail?: string;
  patientPhone?: string;
  patientImage?: string;
  doctorId?: number;
  doctorName?: string;
  patientCode?: string;
  departmentId?: number;
  departmentName?: string;
  appointmentDate?: Date;
  appointmentTime?: string;
  message?: string;
  status?: string; // Active/Inactive
  age?: number;
  purpose?: string;
  type?: string; // New Patient / Old Patient
  paidAmount?: number;
}

export class Appointment implements IAppointment {
    constructor(
        public id?: number, 
        public appointmentId?: string,
        public patientId?: number,
        public patientName?: string,
        public patientEmail?: string,
        public patientPhone?: string,
        public patientImage?: string,
        public doctorId?: number,
        public doctorName?: string,
        public patientCode?: string,
        public departmentId?: number,
        public departmentName?: string,
        public appointmentDate?: Date,
        public appointmentTime?: string,
        public message?: string,
        public status?: string,
        public age?: number,
        public purpose?: string,
       public type?: string, // New Patient / Old Patient
         public paidAmount?: number
    ) {}
}