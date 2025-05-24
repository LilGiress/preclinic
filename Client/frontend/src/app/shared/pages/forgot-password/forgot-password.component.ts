import {Component, OnInit} from '@angular/core';
import { Validators, FormBuilder, AbstractControl } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-forgot-password',
  standalone: true,
  imports: [],
  templateUrl: './forgot-password.component.html',
  styleUrl: './forgot-password.component.css'
})
export class ForgotPasswordComponent implements OnInit{
  data:any
  submitted = false;
  message='';
  /*forgotform = this.fb.group(
    {
      email: ['', [Validators.required, Validators.email]],

    },
  );*/

  constructor(
    private router:Router,
    private fb:FormBuilder,
   /* private toastrService:ToastrService,
    private spinner:NgxSpinnerService,
    private accountService:AccountService*/

  ){}
  ngOnInit(): void {
  }

  /*get f(): { [key: string]: AbstractControl } {
    return this.forgotform.controls;
  }*/

 /* onSubmit() {
    this.submitted = true;
    if (this.forgotform.valid) {
      this.spinner.show();
      this.accountService.ForgotPassword(this.forgotform.value).subscribe({
        next:(value)=> {
          this.data=value
          this.onReset();
          this.toastrService.success('Operation effectuer','Success')
          this.router.navigateByUrl("/reset-password");
        },
        error:(err)=> {
          this.submitted=false
          this.spinner.hide();
          this.toastrService.error('Operation echouer','Echec')
        },
      })

    }
  }*/


 /* onReset(): void {
    this.submitted = false;
    this.forgotform.reset();
  }*/

}
