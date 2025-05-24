import { Permission } from "../permission/permission";

export interface Module {
    name: string;
    access: boolean; // Activer/Désactiver le module
    permission: Permission;
  }