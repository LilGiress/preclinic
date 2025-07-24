
export class RegistrationRequest {
    
    email: string;
    firstname: string;
    lastname: string;
    password: string;
    
  
    constructor(options: {
       
        email?: string;
        firstname?: string;
        lastname?: string;
        password?: string;
       
      } = {}) {
      
      this.email = options.email || '';
      this.firstname = options.firstname || '';
      this.lastname = options.lastname || '';
      this.password = options.password  || '';
     
    }
  }