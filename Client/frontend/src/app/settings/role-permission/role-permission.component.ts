import { Component, inject } from '@angular/core';
import { RolesService } from '../../services/roles.service';
import { CommonModule } from '@angular/common';
import { FormArray, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { NgxSpinnerService } from 'ngx-spinner';
import { ModalService } from '../../shared/service/modal.service';
import { Role } from '../../models/role';
import { AuthorityDataService } from '../../services/AuthorityData/authority-data.service';
import { Action, ActionPermission, Module, ModulePermission, Permission, SubModulePermission } from '../../models/group-attributio-permission';
import { HttpErrorResponse } from '@angular/common/http';

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
  // modulePermission:Module[]=[];
   
   

  form = this.fb.group({
    label: ['', Validators.required],
    roleName: [''],
    modules: this.fb.array([]),
  });

 

 

 submitted = false;
  message='';
  loading=false;
  roles: Role[] = [];
  selectedRole: any;
  newRoleForm: FormGroup;
  // sub_module?:SubModulePermission[]=[]
   sub_modules_with_parent: { parent: string; subModule: SubModulePermission }[] = [];
  constructor(
    private readonly rolesService: RolesService,
    private readonly modalService:ModalService,
     private readonly spinner:NgxSpinnerService,

  ) {
    this.newRoleForm = this.fb.group({
      name: ['', Validators.required],
      modules: this.fb.array([]),
    });
  }

  

  ngOnInit(): void {
    this.modulePermission = this.dataService.getAuthorities();
    console.log('Liste modules permissions****************',this.modulePermission);
    this.sub_modules_with_parent = this.modulePermission.flatMap(module =>
  (module.subModules ?? []).map(sub => ({
    parent: module.label,
    subModule: sub
  }))
);

     //   console.log('Liste sous modules ****************',this.soub_module);

    this.getAllRoles();
  }

  

   get modulesArray(): FormArray {
  return this.form.get('modules') as FormArray;
}




    onSelectRole(role: any): void {
      this.selectedRole = role;
    }


   getAllRoles(): void {
     this.spinner.show();
     this.rolesService.getRoles().subscribe({
       next:(value:any) => {
          this.roles = value
          console.log('Role retournée', value);
           this.spinner.hide();
       },
      error:(err) =>{

           this.spinner.hide();
       },
     
   });
   
   }

    /** Vérifie si toutes les actions d’un sous-module sont cochées */
  isAllSelectedSub(sub: SubModulePermission): boolean {
    return sub.actions.every(a => a.isSelected);
  }

  /** Coche/décoche toutes les actions d’un sous-module */
  toggleAllSub(sub: SubModulePermission, checked: boolean): void {
    sub.actions.forEach(a => {
      if (!a.disabled) a.isSelected = checked;
    });
  }

  /** Vérifie si toutes les actions de tous les sous-modules d’un module sont cochées */
  isAllSelectedModule(module: Permission): boolean {
    if (!module.subModules) return false;
    return module.subModules.every(sub => this.isAllSelectedSub(sub));
  }

  /** Coche/décoche toutes les actions d’un module */
  // toggleAllModule(module: Permission, checked: boolean): void {
  //   module.subModules?.forEach(sub => this.toggleAllSub(sub, checked));
  // }

  toggleAllModule(module: any, checked: boolean): void {
    module.subModules?.forEach((sub: any) => {
      sub.actions?.forEach((action: any) => {
        action.isSelected = checked;
      });
    });
  }

  /** Sauvegarde du rôle avec ses permissions */
   saveRole(): void {
    /// this.role.permissions = this.modules;
     console.log('Rôle enregistré : ', this.role);
   }


trackByAction(index: number, action: any): string {
  return action.label; // ou action.id si tu as un identifiant unique
}


}

