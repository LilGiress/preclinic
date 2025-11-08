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

@Component({
    selector: 'app-leaves',
  imports: [
    ReactiveFormsModule
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

    ngOnInit(): void {
this. getAllLeaveType();
    }

    constructor(
      private leaveService: LeaveService,
      private readonly modalService:ModalService,
      private readonly spinner:NgxSpinnerService,
      private readonly leaveTypeService:LeavetypeService,
    ) {
      this.leaveForm = this.fb.group({
        startDate: ['',[Validators.required,minDateValidators(this.today)] ],
        endDate: ['',[Validators.required,minDateValidators(this.today)]],
        leaveReason: ['', Validators.required],
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

}
