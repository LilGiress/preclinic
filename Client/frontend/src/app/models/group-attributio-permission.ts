export interface GroupAttributioPermission {
}
export interface Action {
  label: string;
  selected: boolean;
}

export interface ModulePermission {
  label: string;
  description: string;
  selected: boolean;
  disabled: boolean;
  moduleName: string;

  // Actions
  read: Action;
  write: Action;
  update: Action;
  delete: Action;
  import: Action;
  export: Action;
  approve: Action;
  validate: Action;
  assign: Action;
  activate: Action;
  generate: Action;

  // Sous-modules
  permissions?: ModulePermission[];
}

export interface ActionPermission {
  label: string;
  selected: boolean;
}


export interface Permission {
  id?:number;
  label: string;            
  description?: string;
  actions: Action[];
  subModules?: SubModulePermission[];
}
export interface SubModulePermission {
  label: string;
  description: string;
  actions: Action[];
}

export interface Module {
  label: string;
  description: string;
  subModules?: SubModulePermission[]; 
}





