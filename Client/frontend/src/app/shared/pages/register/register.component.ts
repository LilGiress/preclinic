import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, Validators} from "@angular/forms";
import {AuthService} from "../../../services/auth/auth.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit{
 /* registrationForm = this.fb.group(
    {
      firstname: ['', Validators.required],
      lastname: ['',Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(40)
        ]
      ],
      confirmPassword: ['',
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(40)
        ]
      ],
      birthday: ['', Validators.required]
    },
    { validator: this.passwordMatchValidator }
  );*/
  submitted = false;
  message='';
  isOkay=true;
  playload=''
  loading=false

  constructor(
    private fb:FormBuilder,
    private authservice:AuthService,
    private route:Router,
    //private toastr:ToastrService,
   /// private spinner:NgxSpinnerService,
  ){}
  ngOnInit(): void {

  }

 /* get f(): { [key: string]: AbstractControl } {
    return this.registrationForm.controls;
  }*/

 /* onSubmit():void{
    this.submitted=true;
    if (this.registrationForm.valid) {

      this.spinner.show();
      // this.playload=JSON.stringify(this.registrationForm.value)
      this.authservice.register(this.playload).subscribe({
        next: (value: any) =>{

          //  console.log(value);
          this.toastr.success('Opéretion effectuer','Succes');
          this.route.navigate(['/activation-code'])

        },
        error:(err) =>{
          this.spinner.hide();
          this.message= err.error
          this.toastr.error('Opération echouer ','Echec');

        }
      })
    }




  }*/


  /**+
   * Pour reset le formulaire
   */

 /* onReset(): void {
    this.submitted = false;
    this.registrationForm.reset();
  }*/
  // Custom validator function
  /*passwordMatchValidator(formGroup: FormGroup) {
    const password = formGroup.get('password')!.value;
    const confirmPassword = formGroup.get('confirmPassword')!.value;
    return password === confirmPassword ? null : { mismatch: true };
  }*/
}
