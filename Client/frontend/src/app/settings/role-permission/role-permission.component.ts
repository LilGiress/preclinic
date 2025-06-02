import { Component } from '@angular/core';
import { RolesService } from '../../services/roles.service';
import { CommonModule } from '@angular/common';
import { FormArray, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
    selector: 'app-role-permission',
    imports: [CommonModule, FormsModule, ReactiveFormsModule],
    templateUrl: './role-permission.component.html',
    styleUrl: './role-permission.component.css'
})
export class RolePermissionComponent {
openCreateRoleForm // Ajouter un nouveau rôle
() {
throw new Error('Method not implemented.');
}
  roles: any[] = [];
  selectedRole: any;
  newRoleForm: FormGroup;
  constructor(
    private rolesService: RolesService,
    private fb:FormBuilder

  ) {
    this.newRoleForm = this.fb.group({
      name: ['', Validators.required],
      modules: this.fb.array([]),
    });
  }

  

  ngOnInit(): void {
  /*  this.rolesService.roles$.subscribe((roles) => {
      this.roles = roles;
      this.selectedRole = this.roles[0]; // Sélection par défaut
    });*/
  }

  // Ajouter un nouveau rôle
  addRole(): void {
    const newRole = this.newRoleForm.value;
    this.rolesService.createRole(newRole).subscribe(
      (response) => {
        console.log('Role créé avec succès', response);
        this.roles.push(response); // Ajoute le rôle à la liste locale
        this.newRoleForm.reset();
      },
      (error) => {
        console.error('Erreur lors de la création du rôle', error);
      }
    );
  }

  // Méthode pour ajouter un module dans le formulaire
  addModule(name: string): void {
    const modules = this.newRoleForm.get('modules') as FormArray;
    modules.push(
      this.fb.group({
        name: [name, Validators.required],
        access: [false],
        permission: this.fb.group({
          read: [false],
          write: [false],
          create: [false],
          delete: [false],
          import: [false],
          export: [false],
        }),
      })
    );
  }

  get modules(): FormArray {
    return this.newRoleForm.get('modules') as FormArray;
  }

  onSelectRole(role: any): void {
    this.selectedRole = role;
  }

  toggleAccess(module: any): void {
    module.access = !module.access;
   // this.rolesService.updateRole(this.selectedRole);
  }

  togglePermission(module: any, permission: keyof any): void {
    module.permission[permission] = !module.permission[permission];
  //  this.rolesService.updateRole(this.selectedRole);
  }

}
