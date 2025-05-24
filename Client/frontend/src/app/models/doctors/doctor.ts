export interface Profile {
    id?: number;
    name: string;
    specialization: string;
    pricing: string;
    services: string[];
    education: { degree: string; college: string; yearOfCompletion: number }[];
    experience: { hospitalName: string; designation: string; fromDate: string; toDate: string }[];
  }