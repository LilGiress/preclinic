import { Component, inject } from '@angular/core';
import { RolesService } from '../../services/roles.service';
import { CommonModule } from '@angular/common';
import { FormArray, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { NgxSpinnerService } from 'ngx-spinner';
import { ModalService } from '../../shared/service/modal.service';
import { Role } from '../../models/role';
import { AuthorityDataService } from '../../services/AuthorityData/authority-data.service';
import { Action,Permission, SubModulePermission } from '../../models/group-attributio-permission';



declare  let $:any;

@Component({
    selector: 'app-role-permission',
    imports: [CommonModule, FormsModule, ReactiveFormsModule],
    templateUrl: './role-permission.component.html',
    styleUrl: './role-permission.component.css'
})
export class RolePermissionComponent {

  modulePermission: Permission[] = [];
  role = {
    name: '',
    description: '',
    permissions: [] as Permission[]
  };

deleteRole(_t15: number) {

}
editRole(_t15: number) {

}
   private readonly fb = inject(FormBuilder);
   private readonly dataService = inject(AuthorityDataService);
   active: string | null = null;
   selectedRoleIndex = 0;
 
   
 

 submitted = false;
  message='';
  loading=false;
  roles: Role[] = [];
  selectedRole: Role = {};
  RoleForm: FormGroup;
  allActions:Action[]=[];
  selectedModuleLabel!: string; 
  selectedSubDescription!: string;
  permissions: Permission[] = [];
  // sub_module?:SubModulePermission[]=[]
   sub_modules_with_parent: { parent: string; subModule: SubModulePermission }[] = [];
  constructor(
    private readonly rolesService: RolesService,
    private readonly modalService:ModalService,
     private readonly spinner:NgxSpinnerService,

  ) {
    this.RoleForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      actions: [[]],
    });
  }

  

  ngOnInit(): void {
    this.modulePermission = this.dataService.getAuthorities();
    //console.log('Liste modules permissions****************',this.modulePermission);
    this.sub_modules_with_parent = this.modulePermission.flatMap(module =>
  (module.subModules ?? []).map(sub => ({
    parent: module.label,
    subModule: sub
  }))
);


    this.getAllRoles();
  }

  
 // Fonction pour accéder facilement aux contrôles
  get f() {
    return this.RoleForm.controls;
  }
  get modules(): FormArray {
    return this.RoleForm.get('modules') as FormArray;
  }

  getSubModules(moduleIndex: number): FormArray {
    return this.modules.at(moduleIndex).get('subModules') as FormArray;
  }

  getActions(moduleIndex: number, subIndex: number): FormArray {
    return this.getSubModules(moduleIndex).at(subIndex).get('actions') as FormArray;
  }

  selectDescriptionSub(desc : any) {
this.selectedSubDescription=desc.description;
}
selectLabelModule(lab: any) {
this.selectedModuleLabel=lab.label;
}
 


// ----------- Builders -------------
  createModuleForm(module: Permission): FormGroup {
    return this.fb.group({
      label: [module.label],
      subModules: this.fb.array(module.subModules!.map(sm => this.createSubModuleForm(sm)))
    });
  }

  createSubModuleForm(sub: SubModulePermission): FormGroup {
    return this.fb.group({
      label:[sub.label],
      description: [sub.description],
      actions: this.fb.array(sub.actions.map(a => this.createActionForm(a)))
    });
  }

  createActionForm(action: Action): FormGroup {
    return this.fb.group({
      label: [action.label],
      selected: [action.selected]
    });
  }
    onSelectRole(role: any): void {
      this.selectedRole = role;
      console.log('Role retournée', this.selectedRole.permissions);
    }


   getAllRoles(): void {
     this.spinner.show();
     this.rolesService.getRoles().subscribe({
       next:(value:any) => {
          this.roles = value
          
           this.spinner.hide();
       },
      error:(err) =>{

           this.spinner.hide();
       },
     
   });
   
   }

    /** Vérifie si toutes les actions d’un sous-module sont cochées */
  isAllSelectedSub(sub: SubModulePermission): boolean {
    return sub.actions.every(a => a.selected);
  }

  /** Coche/décoche toutes les actions d’un sous-module */
  toggleAllSub(sub: SubModulePermission, checked: boolean): void {
    sub.actions.forEach(a => {
     // if (!a.disabled) a.selected = checked;
    });
  }

  /** Vérifie si toutes les actions de tous les sous-modules d’un module sont cochées */
  isAllSelectedModule(module: Permission): boolean {
    if (!module.subModules) return false;
    return module.subModules.every(sub => this.isAllSelectedSub(sub));
  }

 

  toggleAllModule(module: any, checked: boolean): void {
    module.subModules?.forEach((sub: any) => {
      sub.actions?.forEach((action: any) => {
        if (!action.disabled) {
           action.selected = checked;
        }
       
      });
    });
  }

  onActionChange(sub: any, action: any) {
  const index = this.allActions.findIndex(a => a.label === action.label);

  if (action.selected) {
    // Ajouter uniquement si pas déjà présent
    if (index === -1) {
      this.allActions.push(action);
    }
  } else {
    // Retirer si décoché
    if (index !== -1) {
      this.allActions.splice(index, 1);
    }
  }

  this.RoleForm.patchValue({
    actions:this.allActions,
  });

  console.log('Actions sélectionnées :', this.allActions);
}

  /** Sauvegarde du rôle avec ses permissions */
   saveRole(): void {
    this.submitted = true;
    if (this.RoleForm.invalid) {
      this.RoleForm.markAllAsTouched(); // marque tous les champs pour afficher les erreurs
      return;
    }
   const permissions: Permission[]=[{
     label: this.selectedModuleLabel,
     description:this.selectedSubDescription,
     actions: this.allActions,
   }
  ]
     const payload = {
    name: this.RoleForm.value.name,
    description: this.RoleForm.value.description,
    permissions:permissions

  };
 
this.spinner.show();
this.rolesService.createRole(payload).subscribe(
        {
          next: (value: any) => {
              this.spinner.hide();
            // ✅ Fermer le modal après succès
        ($('#exampleModal') as any).modal('hide');
              this.modalService.openSuccessModal(
                'Opération effectuer',
              );
             this.getAllRoles();
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


trackByAction(index: number, action: any): string {
  return action.label; // ou action.id si tu as un identifiant unique
}

onReset(): void {
    this.submitted = false;
    this.RoleForm.reset();
  }
// fonction pour extraire l’expression régulière ^CAN_([^_]+) capture tout ce qui se trouve après CAN_ jusqu’au prochain _.

//Exemple :

//CAN_READ_CHIRURGIE_BUCCALE → READ

//CAN_WRITE_TR_PATIENTS → WRITE

  extractAction(label: string): string {
  const regex = /^CAN_([^_]+)/; 
  const match = label.match(regex);
  return match ? match[1] : '';
}



}

