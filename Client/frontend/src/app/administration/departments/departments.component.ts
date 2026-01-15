import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { DepartementService } from '../../services/department/departement.service';
import { Departement } from '../../models/departments';
import { ModalService } from '../../services/modal.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { EntityStatus } from '../../models/Enum/EntityStatus';
import { Leave } from '../../models/leaves';
import { ServiceConfigService } from '../../services/service-config.service';
import { Services } from '../../models/services';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from "@angular/forms";
declare  let $:any;
@Component({
    selector: 'app-departments',
    imports: [CommonModule, FormsModule,ReactiveFormsModule],
    templateUrl: './departments.component.html',
    styleUrl: './departments.component.css'
})
export class DepartmentsComponent implements OnInit{

   private readonly fb = inject(FormBuilder);
    departments:Departement[]=[];
    leaves:Leave[]=[];
    services:Services[]=[];
    departmentForm:FormGroup;
    submitted = false;
    message='';
    statut=EntityStatus;
  
    constructor(
    private readonly departementService:DepartementService,
    private readonly modalService:ModalService,
    private readonly spinner:NgxSpinnerService,
    private readonly svc: ServiceConfigService,
    ){
      this.departmentForm=this.fb.group({
        name:['', [Validators.required]],
        description:['', [Validators.required]],
        service:['', [Validators.required]],
        status:[EntityStatus.ACTIVE],
      });
      
    }

    ngOnInit(): void {
       this.getAllDepartements();
        this.loadAll();
    }

// Fonction pour accéder facilement aux contrôles
  get f() {
    return this.departmentForm.controls;
  }

  save() {
      this.submitted = true;
      if (this.departmentForm.invalid) {
      this.departmentForm.markAllAsTouched(); // marque tous les champs pour afficher les erreurs
      return;
    }
    this.spinner.show();
    const payload={
      name:this.departmentForm.get('name')?.value ?? '',
      description:this.departmentForm.get('description')?.value ?? '',
      serviceId:this.departmentForm.get('service')?.value ?? '',
      status:EntityStatus.ACTIVE,
    }
    this.departementService.create(payload).subscribe({
      next: (value:any) =>{ 
         ($('#exampleModal') as any).modal('hide');
          this.modalService.openSuccessModal('Département créé avec succès.', ); 
          this.getAllDepartements();
           this.onReset();
          this.submitted=false;
          this.spinner.hide();
        },
        error:(err) =>{
          this.spinner.hide();
        }
    })
  }

     getAllDepartements(): void {
         this.spinner.show();
    this.departementService.getAll().subscribe({
      next: (value:any) =>{
         this.departments = value;
          console.log('--------------------------------this.departements',this.departments);
          this.spinner.hide();

        },
        error:(err) =>{
          this.spinner.hide();

        }
    })
      
  }

 
  loadAll(): void {
    this.spinner.show();
     const filters ={
     size: 1000000,
     page : 0,
     } 
    this.svc.getAll(filters).subscribe({
      next: (value:any) => {
        this.services = value.content;
        console.log('--------------------------------this.services',this.services);
        this.spinner.hide();
      },
      error: (err) => {
        this.spinner.hide();
      }
    });
  }



    onReset(): void {
    this.submitted = false;
    this.departmentForm.reset();
  }

}
