export interface IPatient {
     id?: number;
  firstName?: string;
  lastName?: string;
  age?: number;
  email?: string;
  phone?: string;
  address?: string;
  city?: string;
  state?: string;
  postalCode?: string;
  country?: string;
  dateOfBirth?: Date;
  gender?: string;
  bloodType?: string;
  avatar?: string;
  medicalHistory?: string;
  emergencyContact?: string;
  emergencyPhone?: string;
  status?: string; // Active/Inactive
  createdAt?: Date;
  updatedAt?: Date;
}

export class Patient implements IPatient {
constructor(
     public id?: number,
  public firstName?: string,
    public lastName?: string,
    public age?: number,
    public email?: string,
    public phone?: string,
    public address?: string,
    public city?: string,
    public state?: string,
    public postalCode?: string,
    public country?: string,
    public dateOfBirth?: Date,
    public gender?: string,
    public bloodType?: string,
    public avatar?: string, 
    public medicalHistory?: string,
    public emergencyContact?: string,
    public emergencyPhone?: string,
    public status?: string, // Active/Inactive
    public createdAt?: Date,
    public updatedAt?: Date
 ) {}   
}