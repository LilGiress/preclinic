export interface GroupAttributioPermission {
}
export interface Action {
  label: string;
  isSelected: boolean;
  disabled: boolean;
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
  isSelected: boolean;
  disabled: boolean;
}


export interface Permission {
  id?:number;
  label: string;            
  description?: string;
  selected?: boolean;
  disabled?: boolean;
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

type ActionKeys =
  | 'read'
  | 'write'
  | 'update'
  | 'delete'
  | 'import'
  | 'export'
  | 'approve'
  | 'validate'
  | 'assign'
  | 'activate'
  | 'generate';

const actionKeys: ActionKeys[] = [
  'read', 'write', 'update', 'delete', 'import', 'export',
  'approve', 'validate', 'assign', 'activate', 'generate'
];



