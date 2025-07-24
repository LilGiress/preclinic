import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { DepartementService } from '../../services/department/departement.service';
import { Departement } from '../../models/departments';
import { ModalService } from '../../shared/service/modal.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { EntityStatus } from '../../models/Enum/EntityStatus';

@Component({
    selector: 'app-departments',
    imports: [CommonModule],
    templateUrl: './departments.component.html',
    styleUrl: './departments.component.css'
})
export class DepartmentsComponent implements OnInit{
    departments:Departement[]=[];
    submitted = false;
    message='';
    statut=EntityStatus;
    constructor(
    private readonly departementService:DepartementService,
    private readonly modalService:ModalService,
    private readonly spinner:NgxSpinnerService,
    ){}

    ngOnInit(): void {
       this.getAllDepartements();
    }


     getAllDepartements(): void {
         this.spinner.show();
    this.departementService.getAll().subscribe({
      next: (value:any) =>{
         this.departments = value;
          console.log('--------------------------------this.departements',this.departments);
          this.spinner.hide();
          //  console.log(value);
         // this.toastr.openSuccessModal('Opéretion effectuer');
         // this.route.navigate(['/activation-code'])

        },
        error:(err) =>{
          this.spinner.hide();
          this.message= err.error
          this.modalService.openWarning('Opération echouer ','Echec');

        }
    })
      
  }
}
