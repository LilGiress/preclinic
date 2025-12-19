import {Component, inject, OnInit} from '@angular/core';
import {LeaveService} from "../../services/leave.service";
import {ModalService} from "../../shared/service/modal.service";
import {NgxSpinnerService} from "ngx-spinner";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {Leave} from "../../models/leaves";
import {LeavetypeService} from "../../services/leavetype.service";
import {LeaveType} from "../../models/leaveType";
import {dateRangeValidators} from "../../shared/validators/date-range.validators";
import { DatePipe, NgForOf, NgIf, NgSwitch, NgSwitchCase } from "@angular/common";
import { LeaveStatus } from '../../models/Enum/LeaveStatus';
import { PaginationComponent } from "../../utils/pagination/pagination.component";
import { AuthService } from '../../services/auth/auth.service';


declare  let $:any;
@Component({
    selector: 'app-leaves',
  imports: [
    ReactiveFormsModule,
    NgForOf,
    NgIf,
    PaginationComponent,
    DatePipe,
    NgSwitch,
    NgSwitchCase
],
    templateUrl: './leaves.component.html',
    styleUrl: './leaves.component.css'
})
export class LeavesComponent implements OnInit {
  private readonly fb = inject(FormBuilder);
  submitted = false;
  message='';
  loading=false;
  // Pagination
  currentPage: number = 1;
  itemsPerPage: number = 10;
  totalItems: number = 0;
  leaves:Leave[] = [];  // Liste des congés
  leaveTypes:LeaveType[]=[]; // Liste des types de congés
  leaveForm: FormGroup;
  today=new Date();
  leaveTypeSelect?:LeaveType={};
  leaveTypeSelectUpdate?:LeaveType={};
   minDate!: string;
    selectedOption: any = '';
 filterForm: FormGroup ;
  leaveList: Leave[] = [];
  leaveStatusList: string[] = []; // Liste des statuts de congé
 LeaveStatus: typeof LeaveStatus = LeaveStatus;
  daysRequested?: number;
  paginatedRequests: any[] = [];
  filteredRequests: any[] = [];
updateLeavesForm: FormGroup;
  index?: number;
  updateId?: number;
   currentUser$: any;

    ngOnInit(): void {
      this.leaveStatusList=Object.values(LeaveStatus);
         const today = new Date();
    this.minDate = today.toISOString().split('T')[0];
      this.leaveForm.get('startDate')?.valueChanges.subscribe(start => {
    this.calculateDays(start, this.leaveForm.get('endDate')?.value);
  });

    this.leaveForm.get('endDate')?.valueChanges.subscribe(end => {
      this.calculateDays(this.leaveForm.get('startDate')?.value, end);
    });


    this.updateLeavesForm.get('startDate')?.valueChanges.subscribe(start => {
      this.calculateDays(start, this.updateLeavesForm.get('endDate')?.value);
    });

    this.updateLeavesForm.get('endDate')?.valueChanges.subscribe(end => {
      this.calculateDays(this.updateLeavesForm.get('startDate')?.value, end);
    });

    this. getAllLeaveType();
    this.getAllLeaves();
    }


    constructor(
      private readonly  authService:AuthService,
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

         this.filterForm = this.fb.group({
        leaveType: [''],
        leaveStatus: [''],
        startDate: [''],
        endDate: [''],
      });


      this.updateLeavesForm = this.fb.group({
          leaveType: ['', Validators.required],
        startDate: ['',[Validators.required] ],
        endDate: ['',[Validators.required]],
        leaveReason: ['', Validators.required],
        numberOfDays: ['',Validators.required],
       
      },
        {
          validators: dateRangeValidators('startDate', 'endDate'),
        });

      // assign currentUser$ after authService has been initialized
      this.currentUser$ = this.authService.currentUser$;
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
    this.leaveForm.reset(
      {
        
      }
    );
  }

  resetFilters(): void {
    this.filterForm.reset({
      employeeName: '',
      leaveType: '',
      status: '',
      startDate: '',
      endDate: ''
    });
    this.totalItems  = 1;
    this.totalItems = this.leaves.length;
    this.currentPage = 1; // Réinitialiser à la première page après réinitialisation des filtres
    this.updatePaginatedData();
  }

// setupAutoFilters(): void {
//     this.filterForm?.valueChanges.pipe(
//       debounceTime(300),
//       distinctUntilChanged()
//     ).subscribe(() => {
//      // this.applyFilters();
//   });
      
// }


  getLeavetypeSelected(event: Event) {
    if(!event) return;
    const selectedId=+(event.target as HTMLSelectElement).value;
    this.selectedOption=selectedId;
     this.leaveTypeService.getLeaveById(selectedId).subscribe({
      next:(value:any) => {
        this.leaveTypeSelect = value;      
      },
      error:(err) =>{
       
      },

    });
  }

   getLeavetypeSelectedUpdate(event: Event) {
    if(!event) return;
    const selectedId=+(event.target as HTMLSelectElement).value;
    this.selectedOption=selectedId;
     this.leaveTypeService.getLeaveById(selectedId).subscribe({
      next:(value:any) => {
        this.leaveTypeSelectUpdate= value;      
      },
      error:(err) =>{
       
      },

    });
  }

  // Fonction pour accéder facilement aux contrôles
  get f() {
    return this.leaveForm.controls;
  }

  // Fonction pour accéder facilement aux contrôles
  get u() {
    return this.updateLeavesForm.controls;
  }

  // setupFilterListeners(): void {
  //   // Écouter tous les changements de filtres
  // console.log("Valeur sélectionnée du statut :", this.filterForm.value);
  //     const filters : any = {
  //     leaveType : this.filterForm.get('leaveType')?.value ?? '',
  //     leaveStatus : this.filterForm.get('leaveStatus')?.value ?? '',
  //     startDate : this.filterForm.get('startDate')?.value ?? '',
  //     endDate : this.filterForm.get('endDate')?.value ?? '',
  //     };
  //     //this.applyFilters();
  //     this.currentPage = 0; // Réinitialiser à la première page après application des filtres
  //     this.getAllLeaves(); // Recharger les congés avec les nouveaux filtres
   
  // }



  /**
   * fonction pour créer un congé
   * @returns 
   */

  saveLeaves(){
    this.submitted = true;
      if (this.leaveForm.invalid) {
      this.leaveForm.markAllAsTouched(); // marque tous les champs pour afficher les erreurs
      return;
    }
 let payload = {
      leaveType:this.leaveTypeSelect,
      startDate:this.leaveForm.get('startDate')?.value ?? '',
      endDate:this.leaveForm.get('endDate')?.value ?? '',
      leaveReason:this.leaveForm.get('leaveReason')?.value ?? '',
      numberOfDays:this.leaveTypeSelect?.leaveDays,
      employeeId:0,
      RemainingLeave: 0,
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
          this.getAllLeaves();
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

/**
 * fonction pour récupérer tous les congés
 */

 getAllLeaves(){
     this.spinner.show();
     const filters = this.filterForm.value;
     filters.currentPage = this.currentPage!;
     filters.itemsPerPage = this.itemsPerPage!;

    this.leaveService.getLeaves(filters).subscribe({
      next:(value:any) => {
        this.leaves = value.content;
        this.totalItems = value.length;
        this.filteredRequests  = value.content; // Stocker la liste complète des congés
          console.log("******************** All leaves  *************",value.content)
        this.spinner.hide();
      },
      error:(err) =>{
        this.spinner.hide();
      },

    });
  }



/**
 * fonction pour confirmer la suppression d'un type de congé
 * @param type 
 */

 confirmDeleteLeaveType(type: Leave) {
    this.modalService.openDeleteModal(
      `Voulez-vous vraiment supprimer : ${type.leaveType!.leaveType} ?`,
      () =>  this.deleteLeave(Number(type.id))
    );
  }

  getAvailableStatus(currentStatus: string): string[] {
  switch (currentStatus) {
    case LeaveStatus.NEW:
      return [LeaveStatus.PENDING];

    case LeaveStatus.PENDING:
      return [LeaveStatus.APPROVED, LeaveStatus.DECLINED];

    default:
      return []; // Approved ou Declined → pas d’option
  }
}


 // 🟩 Méthode appelée quand tu ouvres la modale d'édition
  openUpdateModal(leave:Leave): void {
      this.updateId=leave.id;
    
    this.getLeavetyById(leave);
    this.submitted = false;
    
  }

    getLeavetyById(leave:Leave): void {
        this.leaveTypeService.getLeaveById(leave.id).subscribe({
          next:(value:any) => {
            this.updateLeavesForm.patchValue({
      leaveType: value.id,
      startDate: leave.startDate,
      endDate: leave.endDate,
      leaveReason: leave.leaveReason,
      status: leave.status,
      numberOfDays: leave.numberOfDays

    })
                  
          },
          error:(err) =>{
      }

     }); 

  }

/**
 * fonction pour mettre à jour un congé
 */


  updateLeave() {
    this.submitted = true;
    if (this.updateLeavesForm.invalid) {
      this.updateLeavesForm.markAllAsTouched(); // marque tous les champs pour afficher les erreurs
      return;
    }
    const payload :Leave = {
      id: this.updateId!,
      leaveType: this.updateLeavesForm.get('LeaveType')?.value ?? '',
      status: this.updateLeavesForm.get('status')?.value ?? '',
      startDate: this.updateLeavesForm.get('startDate')?.value ?? '',
      endDate: this.updateLeavesForm.get('endDate')?.value ?? '',
      leaveReason: this.updateLeavesForm.get('leaveReason')?.value ?? '',
      numberOfDays: this.updateLeavesForm.get('numberOfDays')?.value ?? '',
      employeeId: this.currentUser$.id! ?? 0,
    };

    this.spinner.show();
    this.leaveService.updateLeaves(Number(this.updateId), payload).subscribe({
      next: (value: any) => {
        this.spinner.hide();
        // ✅ Fermer le modal après succès
        ($('#updateleavesModal') as any).modal('hide');
        this.modalService.openSuccessModal(
          'Opération effectuer',
        );
        this.getAllLeaves();
        this.onReset();

      },
      error: (err: any) => {
        this.spinner.hide();
        this.message = err.error.error;
        this.modalService.openWarning(this.message, 'Échec');
      }
    });

  }



/**
 * fonction pour changer le statut d'un congé
 * @param leave 
 * @param statut 
 */

  selectStatus(leave: any,statut: string) {
     this.index=Number(leave.id);
     console.log("Selected Status :",statut, this.index);
    this.spinner.show();
    this.leaveService.changeLeaveStatus(this.index, statut).subscribe(
      {
        next: (value: any) => {
          this.spinner.hide();
          this.modalService.openSuccessModal(
            'Opération effectuer',
          );
          this.getAllLeaves();
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


/**
 * fonction pour supprimer un congé
 * @param index 
 */

deleteLeave(index:number){
   this.spinner.show();
   this.leaveService.deleteLeaves(Number(index)).subscribe({
      next: (value: any) => {
        this.modalService.openSuccessModal(
          'Opération effectuer',
        );
        this.spinner.hide();
        this.getAllLeaves();
        this.onReset();

      },
      error: (err: any) => {
        this.message = err.error.error;
        this.modalService.openWarning(this.message, 'Échec');
      }
    });


}


// applyFilters(filters: any): void {
    
//      console.log("******************** All leaves form  *************",filters)
//     this.leaves = this.leaveList.filter(request => {
//       // Filtre par nom d'employé
//       // if (filters.employeeName) {
//       //   const nameMatch = request.employee.name
//       //     .toLowerCase()
//       //     .includes(filters.employeeName.toLowerCase());
//       //   if (!nameMatch) return false;
//       // }
      
//       // Filtre par type de congé
//       if (filters.leaveType) {
//         if (request.leaveType !== filters.leaveType) return false;
//       }
      
//       // Filtre par statut
//       if (filters.status) {
//         if (request.status !== filters.status) return false;
//       }
      
//       // Filtre par date de début
//       if (filters.startDate) {
//         const filterStartDate = new Date(filters.startDate);
//         if (request.startDate! < filterStartDate) return false;
//       }
      
//       // Filtre par date de fin
//       if (filters.endDate) {
//         const filterEndDate = new Date(filters.endDate);
//         if (request.endDate! > filterEndDate) return false;
//       }
      
//       return true;
//     });
    
//     // Réinitialiser à la première page après filtrage
//     this.totalItems =this.leaves.length; 
  
//   }


 /**
   * Appliquer les filtres
   */
  applyFilters(): void {
    const filters = this.filterForm.value;
    
    console.log('Application des filtres:', filters);

    // Partir de toutes les données
    this.leaveList = this.leaves.filter(leave => {
      
      // Filtre par nom d'employé
      // if (filters.employeeName && filters.employeeName.trim() !== '') {
      //   const searchTerm = filters.employeeName.toLowerCase().trim();
      //   const employeeName = leave.employeeName.toLowerCase();
      //   if (!employeeName.includes(searchTerm)) {
      //     return false;
      //   }
      // }

      // Filtre par type de congé
      if (filters.leaveType && filters.leaveType !== '') {
        // Convertir en number si c'est une string
        const selectedTypeId = typeof filters.leaveType === 'string' 
          ? parseInt(filters.leaveType) 
          : filters.leaveType;
        
        if (leave.leaveType?.id !== selectedTypeId) {
          return false;
        }
      }

      // Filtre par statut
      if (filters.leaveStatus && filters.leaveStatus !== '') {
        if (leave.status !== filters.leaveStatus) {
          return false;
        }
      }

      // Filtre par date de début
      if (filters.startDate && filters.startDate !== '') {
        const filterStartDate = new Date(filters.startDate);
        const leaveStartDate = new Date(leave.startDate!);
        
        // Comparer uniquement les dates (sans l'heure)
        filterStartDate.setHours(0, 0, 0, 0);
        leaveStartDate.setHours(0, 0, 0, 0);
        
        if (leaveStartDate < filterStartDate) {
          return false;
        }
      }

      // Filtre par date de fin
      if (filters.endDate && filters.endDate !== '') {
        const filterEndDate = new Date(filters.endDate);
        const leaveEndDate = new Date(leave.endDate!);
        
        filterEndDate.setHours(0, 0, 0, 0);
        leaveEndDate.setHours(0, 0, 0, 0);
        
        if (leaveEndDate > filterEndDate) {
          return false;
        }
      }

      return true;
    });

    console.log(`Résultats filtrés: ${this.leaveList.length} sur ${this.leaves.length}`);

    // Mettre à jour la pagination
    this.totalItems = this.leaveList.length;
    this.currentPage = 1; // Retour à la première page
    this.updatePaginatedData();
  }

 /**
  * fonction pour calculer le nombre de jours demandés  
  * @param startDate 
  * @param endDate 
  * @returns 
  */

calculateDays(startDate: string | Date, endDate: string | Date) {
  const start = new Date(startDate);
  const end = new Date(endDate);

  if (!start || !end) {
    this.daysRequested = 0;
    return;
  }

  const diffInMs = end.getTime() - start.getTime();

  if (diffInMs < 0) {
    this.daysRequested = 0;
    return;
  }

  this.daysRequested = Math.floor(diffInMs / (1000 * 60 * 60 * 24)) + 1;

  // Validation par rapport au nombre de jours autorisés
  if (this.leaveTypeSelect && this.daysRequested > (this.leaveTypeSelect?.leaveDays ?? 0)) {
    this.leaveForm.get("endDate")?.setErrors({ tooManyDays: true });
  } else {
    this.leaveForm.get("endDate")?.setErrors(null);
  }

   // Validation par rapport au nombre de jours autorisés
  if (this.leaveTypeSelect && this.daysRequested > (this.leaveTypeSelect?.leaveDays ?? 0)) {
    this.updateLeavesForm.get("endDate")?.setErrors({ tooManyDays: true });
  } else {
    this.updateLeavesForm.get("endDate")?.setErrors(null);
  }
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