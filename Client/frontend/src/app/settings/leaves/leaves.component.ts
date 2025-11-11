import {Component, inject, OnInit} from '@angular/core';
import {LeaveService} from "../../services/leave.service";
import {ModalService} from "../../shared/service/modal.service";
import {NgxSpinnerService} from "ngx-spinner";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {Leave} from "../../models/leaves";
import {LeavetypeService} from "../../services/leavetype.service";
import {LeaveType} from "../../models/leaveType";
import {dateRangeValidators} from "../../shared/validators/date-range.validators";
import {minDateValidators} from "../../shared/validators/min-date.validator";
import {NgForOf, NgIf} from "@angular/common";

@Component({
    selector: 'app-leaves',
  imports: [
    ReactiveFormsModule,
    NgForOf,
    NgIf
  ],
    templateUrl: './leaves.component.html',
    styleUrl: './leaves.component.css'
})
export class LeavesComponent implements OnInit {
  private readonly fb = inject(FormBuilder);
  submitted = false;
  message='';
  loading=false;
  leaves:Leave[] = [];
  leaveTypes:LeaveType[]=[];
  leaveForm: FormGroup;
  today=new Date();
  leaveTypeSelect?:LeaveType={}

    ngOnInit(): void {
this. getAllLeaveType();
    }

    constructor(
      private readonly leaveService: LeaveService,
      private readonly modalService:ModalService,
      private readonly spinner:NgxSpinnerService,
      private readonly leaveTypeService:LeavetypeService,
    ) {
      this.leaveForm = this.fb.group({
        startDate: ['',[Validators.required,minDateValidators(this.today)] ],
        endDate: ['',[Validators.required,minDateValidators(this.today)]],
        leaveReason: ['', Validators.required],
          leaveType: ['', Validators.required],
      },
        {
          validators: dateRangeValidators('startDate', 'endDate'),
        });

    }

  getAllLeaveType(): void {
    this.spinner.show();
    this.leaveTypeService.getLeaves().subscribe({
      next:(value:any) => {
        this.leaveTypes = value;
        this.spinner.hide();
      },
      error:(err) =>{
        this.spinner.hide();
      },

    });

  }

  onReset(): void {
    this.submitted = false;
    this.leaveForm.reset();
  }

  getLeavetypeSelected(type:any) {
   // if(!type) return;
   /// const selectedId=+type.target.value;
   // const selectedType=this.leaveTypes.find(l=>l.id==selectedId);
   // if(selectedType){
     // this.leaveTypeSelect= this.getCurrentLeaveType();
    //  console.log('Leave Type sélectionné :', this.leaveTypeSelect!.leaveDays);
    //}
    this.leaveTypeSelect= this.getCurrentLeaveType();
    console.log('Leave Type sélectionné :', this.leaveTypeSelect!.leaveDays);

  }

  // Pour récupérer la valeur actuelle à tout moment
  getCurrentLeaveType() {
    return this.leaveForm.get('leaveType')?.value;
  }

  // Fonction pour accéder facilement aux contrôles
  get f() {
    return this.leaveForm.controls;
  }


  saveLeaves(){
    this.submitted = true;
    console.log('Leave Type sélectionné :', this.leaveForm.value);
    //if (this.leaveForm.invalid) {
      //this.leaveForm.markAllAsTouched(); // marque tous les champs pour afficher les erreurs
     // return;
   // }
  }
}
