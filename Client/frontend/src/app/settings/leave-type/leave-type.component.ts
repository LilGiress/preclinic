import {Component, inject, OnInit} from '@angular/core';
import {ModalService} from "../../shared/service/modal.service";
import {NgxSpinnerService} from "ngx-spinner";
import { NgIf} from "@angular/common";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {LeaveType} from "../../models/leaveType";
import {LeavetypeService} from "../../services/leavetype.service";
import {EntityStatus} from "../../models/Enum/EntityStatus";
import {LeaveTypeRequest} from "../../models/playload/LeaveTypeRequest";
declare  let $:any;
@Component({
    selector: 'app-leave-type',
  imports: [
    NgIf,
    ReactiveFormsModule
  ],
    templateUrl: './leave-type.component.html',
    styleUrl: './leave-type.component.css'
})
export class LeaveTypeComponent implements OnInit {
  private readonly fb = inject(FormBuilder);
  submitted = false;
  message='';
  loading=false;
  leaveTypeForm: FormGroup;
  leaveTypes:LeaveType[]=[];

    ngOnInit(): void {
      this.getAllLeaveType();
    }

    constructor(
      private readonly leaveTypeService:LeavetypeService,
      private readonly modalService:ModalService,
      private readonly spinner:NgxSpinnerService,

    ) {
      this.leaveTypeForm = this.fb.group({
        LeaveType: ['', Validators.required],
        leaveDays: ['', Validators.required],
      });

    }
  // Fonction pour accéder facilement aux contrôles
  get f() {
    return this.leaveTypeForm.controls;
  }


  saveLeaveType() {
    this.submitted = true;
    if (this.leaveTypeForm.invalid) {
      this.leaveTypeForm.markAllAsTouched(); // marque tous les champs pour afficher les erreurs
      return;
    }
    let payload : LeaveTypeRequest = {
      leaveType:this.leaveTypeForm.get('LeaveType')?.value ?? '',
      leaveDays: this.leaveTypeForm.get('leaveDays')?.value ?? 1,
      status:EntityStatus.ACTIVE
    };

    this.spinner.show();
    this.leaveTypeService.create(payload).subscribe(
      {
        next: (value: any) => {
          this.spinner.hide();
          // ✅ Fermer le modal après succès
          ($('#exampleModal') as any).modal('hide');
          this.modalService.openSuccessModal(
            'Opération effectuer',
          );
          this.getAllLeaveType();
          this.onReset();

        },
        error: (err: any) => {
          this.spinner.hide();
          this.message = err.error.error;
          this.modalService.openWarning(this.message, 'Échec');
        },
      }
    )
    console.log('Payload à envoyer à l’API :', payload);

  }

  getAllLeaveType(): void {
    this.spinner.show();
    this.leaveTypeService.getLeaves().subscribe({
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

  onReset(): void {
    this.submitted = false;
    this.leaveTypeForm.reset();
  }

}
