import {Component, inject, OnInit} from '@angular/core';
import {ModalService} from "../../services/modal.service";
import {NgxSpinnerService} from "ngx-spinner";
import {NgForOf, NgIf} from "@angular/common";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {LeaveType} from "../../models/leaveType";
import {LeavetypeService} from "../../services/leavetype.service";
import {EntityStatus} from "../../models/Enum/EntityStatus";
import {LeaveTypeRequest} from "../../models/playload/LeaveTypeRequest";
import { PaginationComponent } from "../../utils/pagination/pagination.component";


declare  let $:any;
@Component({
    selector: 'app-leave-type',
  imports: [
    NgIf,
    ReactiveFormsModule,
    NgForOf,
    PaginationComponent
],
    templateUrl: './leave-type.component.html',
    styleUrl: './leave-type.component.css'
})
export class LeaveTypeComponent implements OnInit {
  private readonly fb = inject(FormBuilder);
  protected readonly EntityStatus = EntityStatus;
  submitted = false;
  message='';
  loading=false;
  leaveTypeForm: FormGroup;
  updateleaveTypeForm: FormGroup;
  leaveTypes:LeaveType[]=[];
  index?:number;
  updateId?:number;
  changeStatus?:string;
  filteredRequests: any[] = [];
  paginatedRequests: any[] = [];
  
  // Pagination
  currentPage: number = 1;
  itemsPerPage: number = 10;
  totalItems: number = 0;


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
        description:['',Validators.required]
      });

      this.updateleaveTypeForm = this.fb.group({
        LeaveType: ['', Validators.required],
        leaveDays: ['', Validators.required],
        description:['',Validators.required]
      });

    }
  // Fonction pour accéder facilement aux contrôles
  get f() {
    return this.leaveTypeForm.controls;
  }

  // Fonction pour accéder facilement aux contrôles
  get u() {
    return this.updateleaveTypeForm.controls;
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
      description:this.leaveTypeForm.get('description')?.value ?? '',
      status:EntityStatus.ACTIVE
    };

    this.spinner.show();
    this.leaveTypeService.createLeaveType(payload).subscribe(
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
        this.filteredRequests = value;
        this.totalItems = value.length;
        this.updatePaginatedData();
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

  selectStatus(status: string,type:LeaveType) {
     this.index=Number(type.id);
    this.spinner.show();
    this.leaveTypeService.ChangeLeave(this.index,status).subscribe(
      {
        next: (value: any) => {
          this.spinner.hide();
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

  }


  // 🟩 Méthode appelée quand tu ouvres la modale d'édition
  openUpdateModal(type:LeaveType): void {
      this.updateId=type.id;
    this.submitted = false;
    this.changeStatus=type.status;
    this.updateleaveTypeForm.patchValue({
      LeaveType:type.leaveType,
      leaveDays: type.leaveDays,


    })

  }

  updateLeave() {
    this.submitted = true;
    if (this.updateleaveTypeForm.invalid) {
      this.updateleaveTypeForm.markAllAsTouched(); // marque tous les champs pour afficher les erreurs
      return;
    }
    const payload = {
      leaveType: this.updateleaveTypeForm.get('LeaveType')?.value ?? '',
      leaveDays: this.updateleaveTypeForm.get('leaveDays')?.value ?? '',
      description:this.updateleaveTypeForm.get('description')?.value ?? '',
      status: this.changeStatus,
    };

    this.spinner.show();
    this.leaveTypeService.updateLeave(Number(this.updateId), payload).subscribe({
      next: (value: any) => {
        this.spinner.hide();
        // ✅ Fermer le modal après succès
        ($('#updateModal') as any).modal('hide');
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
      }
    });

  }

  confirmDeleteLeaveType(type: LeaveType) {
    this.modalService.openDeleteModal(
      `Voulez-vous vraiment supprimer : ${type.leaveType} ?`,
      () =>  this.deleteLeaveType(Number(type.id))
    );
  }

  deleteLeaveType(index:number): void {
    this.leaveTypeService.deleteLeave(Number(index)).subscribe({
      next: (value: any) => {
        this.modalService.openSuccessModal(
          'Opération effectuer',
        );
        this.getAllLeaveType();
        this.onReset();

      },
      error: (err: any) => {
        this.message = err.error.error;
        this.modalService.openWarning(this.message, 'Échec');
      }
    });
  }

  onPageChange(page: number): void {
    this.currentPage = page;
    this.updatePaginatedData();
  }

  onPageSizeChange(size: number): void {
    this.itemsPerPage = size;
    this.currentPage = 1; // Reset to first page
    this.updatePaginatedData();
  }

  updatePaginatedData(): void {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.paginatedRequests = this.filteredRequests.slice(startIndex, endIndex);
  }

  // Appelée quand les filtres changent
  onFiltersChange(filteredData: any[]): void {
    this.filteredRequests = filteredData;
    this.totalItems = filteredData.length;
    this.currentPage = 1; // Reset to first page
    this.updatePaginatedData();


  }

}
