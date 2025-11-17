import {Component, inject, OnInit} from '@angular/core';
import {LeaveService} from "../../services/leave.service";
import {ModalService} from "../../shared/service/modal.service";
import {NgxSpinnerService} from "ngx-spinner";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {Leave} from "../../models/leaves";
import {LeavetypeService} from "../../services/leavetype.service";
import {LeaveType} from "../../models/leaveType";
import {dateRangeValidators} from "../../shared/validators/date-range.validators";
import {NgForOf, NgIf} from "@angular/common";
import { LeaveStatus } from '../../models/Enum/LeaveStatus';

declare  let $:any;
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
  leaveTypeSelect?:LeaveType={};
   minDate!: string;
    selectedOption: any = '';

    ngOnInit(): void {
         const today = new Date();
    this.minDate = today.toISOString().split('T')[0];
    this. getAllLeaveType();
    }


    constructor(
      private readonly leaveService: LeaveService,
      private readonly modalService:ModalService,
      private readonly spinner:NgxSpinnerService,
      private readonly leaveTypeService:LeavetypeService,
    ) {
      this.leaveForm = this.fb.group({
          leaveType: ['', Validators.required],
        startDate: ['',[Validators.required] ],
        endDate: ['',[Validators.required]],
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

  getLeavetypeSelected(event: Event) {
    if(!event) return;
    const selectedId=+(event.target as HTMLSelectElement).value;
    this.selectedOption=selectedId;
     this.leaveTypeService.getLeaveById(selectedId).subscribe({
      next:(value:any) => {
        this.leaveTypeSelect = value;
         console.log('Leave Type leaveTypeSelect :', this.leaveTypeSelect);
      
      },
      error:(err) =>{
       
      },

    });
    console.log('Leave Type sélectionné :', this.selectedOption);

  }

  // Fonction pour accéder facilement aux contrôles
  get f() {
    return this.leaveForm.controls;
  }


  saveLeaves(){
    this.submitted = true;
    console.log('Leave Type sélectionné :', this.leaveForm.value);
    if (this.leaveForm.invalid) {
      this.leaveForm.markAllAsTouched(); // marque tous les champs pour afficher les erreurs
      return;
    }
 let payload  = {
      leaveType:this.leaveForm.get('LeaveType')?.value ?? '',
      startDate:this.leaveForm.get('startDate')?.value ?? '',
      endDate:this.leaveForm.get('endDate')?.value ?? '',
      leaveReason:this.leaveForm.get('leaveReason')?.value ?? '',
      employeeId:0,
      RemainingLeave:0,
      status:LeaveStatus.NEW,
    };

     this.spinner.show();
    this.leaveService.createLeave(payload).subscribe(
      {
        next: (value: any) => {
          this.spinner.hide();
          // ✅ Fermer le modal après succès
          ($('#createleavesModal') as any).modal('hide');
          this.modalService.openSuccessModal(
            'Opération effectuer',
          );
         // this.getAllLeaveType();
          this.onReset();

        },
        error: (err: any) => {
          this.submitted = false;
          this.spinner.hide();
          this.message = err.error.error;
          this.modalService.openWarning(this.message, 'Échec');
        },
      }
    )

  }

  getAllLeaves(){
     this.spinner.show();
    this.leaveService.getLeaves().subscribe({
      next:(value:any) => {
        this.leaveTypes = value
          console.log("******************** All leaves Types *************",value)
        this.spinner.hide();
      },
      error:(err) =>{

        this.spinner.hide();
      },

    });
  }
}
