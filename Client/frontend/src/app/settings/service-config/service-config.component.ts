import { Component, inject, OnInit } from '@angular/core';
import { Services } from '../../models/services';
import { ServiceConfigService } from '../../services/service-config.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ModalService } from '../../services/modal.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { DepartementService } from '../../services/department/departement.service';
import { Departement } from '../../models/departments';

declare  let $:any;
@Component({
  selector: 'app-service-config',
  imports: [ReactiveFormsModule,CommonModule,NgFor,NgIf],
  templateUrl: './service-config.component.html',
  styleUrl: './service-config.component.css'
})
export class ServiceConfigComponent implements OnInit {
 private readonly fb = inject(FormBuilder);
   services: Services[] = [];
   departments:Departement[]=[];
  serviceForm: FormGroup;
  updateServiceForm: FormGroup;
   submitted = false;
  message='';
  updateId: number =0;
   constructor(private readonly svc: ServiceConfigService,
     private readonly modalService:ModalService,
      private readonly spinner:NgxSpinnerService,
      private readonly departementService:DepartementService,
   ) {
   
    this.serviceForm = this.fb.group({
      name: ['', [Validators.required, Validators.maxLength(100)]],
      description: ['',[Validators.maxLength(255)]],
      departement:[null, Validators.required],
    });
    this.updateServiceForm = this.fb.group({
      name: ['', [Validators.required, Validators.maxLength(100)]],
      description: ['',[Validators.maxLength(255)]],
      departement:[null, Validators.required],
    });
   }

  ngOnInit(): void {
    this.loadAll();
    this. getAllDepartements();
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


 getAllDepartements(): void {
         this.spinner.show();
    this.departementService.getAll().subscribe({
      next: (value:any) =>{
         this.departments = value;
          this.spinner.hide();
        },
        error:(err) =>{
          this.spinner.hide();

        }
    })
      
  }

  // Fonction pour accéder facilement aux contrôles
  get f() {
    return this.serviceForm.controls;
  }

    // Fonction pour accéder facilement aux contrôles
  get u() {
    return this.updateServiceForm.controls;
  }
 
  create(): void {
    this.submitted = true;
     if (this.serviceForm.invalid) {
      this.serviceForm.markAllAsTouched();
      return;
    }
    const payload :Services = {
          name: this.serviceForm.get('name')?.value ?? '',
          description: this.serviceForm.get('description')?.value ?? '',
          departement: {
            id: Number(this.serviceForm.get('departement')?.value ?? 0), 
          },
        
        };
        this.spinner.show();
    this.svc.create(payload).subscribe({
      next: (value:any) => {
        this.spinner.hide();
       // ✅ Fermer le modal après succès
          ($('#exampleModal') as any).modal('hide');
          this.modalService.openSuccessModal(
            'Opération effectuer',
          );
          this.loadAll();
          this.onReset();
      },
      error: (err) => {
        this.submitted = true;
        this.spinner.hide();
      }
    });
  }

  // 🟩 Méthode appelée quand tu ouvres la modale d'édition
  openUpdateModal(ser:Services): void {
      this.updateId=ser.id!;
    
    this.getServiceById(ser);
    this.submitted = false;
    //($('#updateModal') as any).modal('show');
  }

    getServiceById(ser:Services): void {
          this.svc.getById(ser.id!).subscribe({
            next:(value:any) => {
              this.updateServiceForm.patchValue({

       name:ser.name,
       description:ser.description,
       departement:ser.departement?.id,
  
      })
                    
            },
            error:(err) =>{
        }
  
       }); 
  
    }

 update() {
    this.submitted = true;
    if (this.updateServiceForm.invalid) {
      this.updateServiceForm.markAllAsTouched(); // marque tous les champs pour afficher les erreurs
      return;
    }
    const payload :Services = {
      id: this.updateId!,
      name: this.updateServiceForm.get('name')?.value ?? '',
      description: this.updateServiceForm.get('description')?.value ?? '',
      departement: {
        id: Number(this.updateServiceForm.get('departement')?.value ?? 0), 
      },
    };

    this.spinner.show();
    this.svc.update(Number(this.updateId), payload).subscribe({
      next: (value: any) => {
        this.spinner.hide();
        // ✅ Fermer le modal après succès
        ($('#updateModal') as any).modal('hide');
        this.modalService.openSuccessModal(
          'Opération effectuer',
        );
        this.loadAll();
        this.onReset();

      },
      error: (err: any) => {
        this.spinner.hide();
      }
    });

  }


  confirmDeleteLeaveType(type: Services) {
      this.modalService.openDeleteModal(
        `Voulez-vous vraiment supprimer : ${type.name} ?`,
        () =>  this.delete(Number(type.id))
      );
    }
  
  delete(index:number){
   this.spinner.show();
   this.svc.delete(Number(index)).subscribe({
      next: (value: any) => {
        this.modalService.openSuccessModal(
          'Opération effectuer',
        );
        this.spinner.hide();
        this.loadAll();
        this.onReset();

      },
      error: (err: any) => {
        this.spinner.hide();
      }
    });


}


  onReset(): void {
    this.submitted = false;
    this.serviceForm.reset();
  }

}
