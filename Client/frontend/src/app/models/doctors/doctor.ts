export interface Profile {
    id?: number;
    name: string;
    specialization: string;
    pricing: string;
    services: string[];
    education: { degree: string; college: string; yearOfCompletion: number }[];
    experience: { hospitalName: string; designation: string; fromDate: string; toDate: string }[];
  }

  export interface IDoctor {
     id?: number;
  firstName?: string;
  lastName?: string;
  username?: string;
  email?: string;
   phoneNumber: string;
  password?: string;
  dateOfBirth?: Date;
  gender?: string; // Male/Female
  phone?: string;
  address?: string;
  country?: string;
  city?: string;
  state?: string;
  postalCode?: string;
  avatar?: string;
  biography?: string;
  specialization?: string;
  status?: string; // Active/Inactive
  createdAt?: Date;
  updatedAt?: Date;
  }

  export class Doctor implements IDoctor {
     constructor(
         public id?: number,
         public firstName?: string,
          public lastName?: string,
          public username?: string,
          public email?: string,
          public phoneNumber: string = '',
          public password?: string,
          public dateOfBirth?: Date,
          public gender?: string,
          public phone?: string,
          public address?: string,  
          public country?: string,
          public city?: string,
          public state?: string,
          public postalCode?: string,
          public avatar?: string,
          public biography?: string,
          public specialization?: string,
          public status?: string,
          public createdAt?: Date,
          public updatedAt?: Date
     ) {}
  }