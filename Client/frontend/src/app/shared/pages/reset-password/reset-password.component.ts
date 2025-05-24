import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import { Router } from '@angular/router';


@Component({
  selector: 'app-reset-password',
  standalone: true,
  imports: [],
  templateUrl: './reset-password.component.html',
  styleUrl: './reset-password.component.css'
})
export class ResetPasswordComponent implements OnInit{
  submitted = false;
  data:any
  message='';
  
  constructor(
    private router:Router,
    private fb:FormBuilder,
    /*private toastrService:ToastrService,
    private spinner:NgxSpinnerService,
    private accountService:AccountService*/
  ){}

 /* resetForm = this.fb.group(
    {

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

    },
    { validator: this.passwordMatchValidator }
  );*/
  ngOnInit(): void {

  }

 /* get f(): { [key: string]: AbstractControl } {
    return this.resetForm.controls;
  }*/
 /* onSubmit() {
    this.submitted = true;
    if (this.resetForm.valid) {
      this.spinner.show();
      this.accountService.resetpassword(this.resetForm.value).subscribe({
        next:(value)=> {
          this.data=value;
          this.onReset();
          this.toastrService.success('Operation effectuer','Success');
          this.router.navigateByUrl("/login");
        },
        error:(err) => {
          this.submitted=false
          this.spinner.hide();
          this.toastrService.error('Operation echouer','Echec');
        },
      })

    }
  }*/


 /* onReset(): void {
    this.submitted = false;
    this.resetForm.reset();
  }*/
  // Custom validator function
  passwordMatchValidator(formGroup: FormGroup) {
    const password = formGroup.get('password')!.value;
    const confirmPassword = formGroup.get('confirmPassword')!.value;
    return password === confirmPassword ? null : { mismatch: true };
  }
}
