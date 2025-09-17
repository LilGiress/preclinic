import { Injectable } from '@angular/core';
import { ActionPermission, Module, ModulePermission, Permission } from '../../models/group-attributio-permission';
import { Role } from '../../models/role';





@Injectable({
  providedIn: 'root'
})
export class AuthorityDataService {

  constructor() { }

// authorities: Module[]=[
  
  
//     {
//       "label": "TRAINING",
//       "description": "Gestion des formations",
//       "subModules": [
//         {
//           "label": "COURSES",
//           "description": "Gestion des cours",
//           "actions": [
//             { "label": "CAN_READ", "isSelected": false, "disabled": false },
//             { "label": "CAN_WRITE", "isSelected": false, "disabled": false },
//             { "label": "CAN_CREATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_UPDATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_DELETE", "isSelected": false, "disabled": false },
//             { "label": "CAN_IMPORT", "isSelected": false, "disabled": false },
//             { "label": "CAN_EXPORT", "isSelected": false, "disabled": false },
//             { "label": "CAN_APPROVE", "isSelected": false, "disabled": false },
//             { "label": "CAN_VALIDATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_ASSIGN", "isSelected": false, "disabled": false },
//             { "label": "CAN_ACTIVATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_GENERATE", "isSelected": false, "disabled": false }
//           ]
//         },
//         {
//           "label": "SESSIONS",
//           "description": "Gestion des sessions",
//           "actions": [
//             { "label": "CAN_READ", "isSelected": false, "disabled": false },
//             { "label": "CAN_WRITE", "isSelected": false, "disabled": false },
//             { "label": "CAN_CREATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_UPDATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_DELETE", "isSelected": false, "disabled": false },
//             { "label": "CAN_IMPORT", "isSelected": false, "disabled": false },
//             { "label": "CAN_EXPORT", "isSelected": false, "disabled": false },
//             { "label": "CAN_APPROVE", "isSelected": false, "disabled": false },
//             { "label": "CAN_VALIDATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_ASSIGN", "isSelected": false, "disabled": false },
//             { "label": "CAN_ACTIVATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_GENERATE", "isSelected": false, "disabled": false }
//           ]
//         }
//       ]
//     },
//     {
//       "label": "PATIENTS",
//       "description": "Gestion des patients",
//       "subModules": [
//         {
//           "label": "DOSSIERS",
//           "description": "Gestion des dossiers médicaux",
//           "actions": [
//             { "label": "CAN_READ", "isSelected": false, "disabled": false },
//             { "label": "CAN_WRITE", "isSelected": false, "disabled": false },
//             { "label": "CAN_CREATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_UPDATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_DELETE", "isSelected": false, "disabled": false },
//             { "label": "CAN_IMPORT", "isSelected": false, "disabled": false },
//             { "label": "CAN_EXPORT", "isSelected": false, "disabled": false },
//             { "label": "CAN_APPROVE", "isSelected": false, "disabled": false },
//             { "label": "CAN_VALIDATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_ASSIGN", "isSelected": false, "disabled": false },
//             { "label": "CAN_ACTIVATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_GENERATE", "isSelected": false, "disabled": false }
//           ]
//         },
//         {
//           "label": "CONSULTATIONS",
//           "description": "Gestion des consultations",
//           "actions": [
//             { "label": "CAN_READ", "isSelected": false, "disabled": false },
//             { "label": "CAN_WRITE", "isSelected": false, "disabled": false },
//             { "label": "CAN_CREATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_UPDATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_DELETE", "isSelected": false, "disabled": false },
//             { "label": "CAN_IMPORT", "isSelected": false, "disabled": false },
//             { "label": "CAN_EXPORT", "isSelected": false, "disabled": false },
//             { "label": "CAN_APPROVE", "isSelected": false, "disabled": false },
//             { "label": "CAN_VALIDATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_ASSIGN", "isSelected": false, "disabled": false },
//             { "label": "CAN_ACTIVATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_GENERATE", "isSelected": false, "disabled": false }
//           ]
//         }
//       ]
//     },
//     {
//       "label": "CONFIGURATION",
//       "description": "privilège super administrateur",
//       "subModules": [
//         {
//           "label": "USERS",
//           "description": "Gestion des utilisateurs",
//           "actions": [
//             { "label": "CAN_READ", "isSelected": false, "disabled": false },
//             { "label": "CAN_WRITE", "isSelected": false, "disabled": false },
//             { "label": "CAN_CREATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_UPDATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_DELETE", "isSelected": false, "disabled": false },
//             { "label": "CAN_IMPORT", "isSelected": false, "disabled": false },
//             { "label": "CAN_EXPORT", "isSelected": false, "disabled": false },
//             { "label": "CAN_APPROVE", "isSelected": false, "disabled": false },
//             { "label": "CAN_VALIDATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_ASSIGN", "isSelected": false, "disabled": false },
//             { "label": "CAN_ACTIVATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_GENERATE", "isSelected": false, "disabled": false }
//           ]
//         },
//         {
//           "label": "ROLES",
//           "description": "Gestion des rôles et permissions",
//           "actions": [
//             { "label": "CAN_READ", "isSelected": false, "disabled": false },
//             { "label": "CAN_WRITE", "isSelected": false, "disabled": false },
//             { "label": "CAN_CREATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_UPDATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_DELETE", "isSelected": false, "disabled": false },
//             { "label": "CAN_IMPORT", "isSelected": false, "disabled": false },
//             { "label": "CAN_EXPORT", "isSelected": false, "disabled": false },
//             { "label": "CAN_APPROVE", "isSelected": false, "disabled": false },
//             { "label": "CAN_VALIDATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_ASSIGN", "isSelected": false, "disabled": false },
//             { "label": "CAN_ACTIVATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_GENERATE", "isSelected": false, "disabled": false }
//           ]
//         }
//       ]
//     },
//     {
//       "label": "COMPTABLE",
//       "description": "Gestion de la comptabilité",
//       "subModules": [
//         {
//           "label": "FACTURES",
//           "description": "Gestion des factures",
//           "actions": [
//             { "label": "CAN_READ", "isSelected": false, "disabled": false },
//             { "label": "CAN_WRITE", "isSelected": false, "disabled": false },
//             { "label": "CAN_CREATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_UPDATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_DELETE", "isSelected": false, "disabled": false },
//             { "label": "CAN_IMPORT", "isSelected": false, "disabled": false },
//             { "label": "CAN_EXPORT", "isSelected": false, "disabled": false },
//             { "label": "CAN_APPROVE", "isSelected": false, "disabled": false },
//             { "label": "CAN_VALIDATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_ASSIGN", "isSelected": false, "disabled": false },
//             { "label": "CAN_ACTIVATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_GENERATE", "isSelected": false, "disabled": false }
//           ]
//         },
//         {
//           "label": "PAIEMENTS",
//           "description": "Suivi des paiements",
//           "actions": [
//             { "label": "CAN_READ", "isSelected": false, "disabled": false },
//             { "label": "CAN_WRITE", "isSelected": false, "disabled": false },
//             { "label": "CAN_CREATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_UPDATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_DELETE", "isSelected": false, "disabled": false },
//             { "label": "CAN_IMPORT", "isSelected": false, "disabled": false },
//             { "label": "CAN_EXPORT", "isSelected": false, "disabled": false },
//             { "label": "CAN_APPROVE", "isSelected": false, "disabled": false },
//             { "label": "CAN_VALIDATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_ASSIGN", "isSelected": false, "disabled": false },
//             { "label": "CAN_ACTIVATE", "isSelected": false, "disabled": false },
//             { "label": "CAN_GENERATE", "isSelected": false, "disabled": false }
//           ]
//         }
//       ]
//     }
  


// ]


private authorities: Permission[] = [
    {
      "label": "TRAINING",
      "description": "Gestion des formations",
      "actions": [],
      "subModules": [
        {
          "label": "COURSES",
          "description": "Gestion des cours",
          "actions": this.getDefaultActions()
        },
        {
          "label": "SESSIONS",
          "description": "Gestion des sessions",
          "actions": this.getDefaultActions()
        }
      ]
    },
    {
      "label": "PATIENTS",
      "description": "Gestion des patients",
      "actions": [],
      "subModules": [
        {
          "label": "DOSSIERS",
          "description": "Gestion des dossiers médicaux",
          "actions": this.getDefaultActions()
        },
        {
          "label": "CONSULTATIONS",
          "description": "Gestion des consultations",
          "actions": this.getDefaultActions()
        }
      ]
    },
    {
      "label": "CONFIGURATION",
      "description": "privilège super administrateur",
      "actions": [],
      "subModules": [
        {
          "label": "USERS",
          "description": "Gestion des utilisateurs",
          "actions": this.getDefaultActions()
        },
        {
          "label": "ROLES",
          "description": "Gestion des rôles et permissions",
          "actions": this.getDefaultActions()
        }
      ]
    },
    {
      "label": "COMPTABLE",
      "description": "Gestion de la comptabilité",
      "actions": [],
      "subModules": [
        {
          "label": "FACTURES",
          "description": "Gestion des factures",
          "actions": this.getDefaultActions()
        },
        {
          "label": "PAIEMENTS",
          "description": "Suivi des paiements",
          "actions": this.getDefaultActions()
        }
      ]
    }
  ];


/** Expose la liste des modules */
  getAuthorities(): Permission[] {
    return JSON.parse(JSON.stringify(this.authorities)); // clone pour éviter mutation directe
  }

  /** Actions de base */
  private getDefaultActions() {
    return [
      { label: "CAN_READ", isSelected: false, disabled: false },
      { label: "CAN_WRITE", isSelected: false, disabled: false },
      { label: "CAN_CREATE", isSelected: false, disabled: false },
      { label: "CAN_UPDATE", isSelected: false, disabled: false },
      { label: "CAN_DELETE", isSelected: false, disabled: false },
      { label: "CAN_IMPORT", isSelected: false, disabled: false },
      { label: "CAN_EXPORT", isSelected: false, disabled: false },
      { label: "CAN_APPROVE", isSelected: false, disabled: false },
      { label: "CAN_VALIDATE", isSelected: false, disabled: false },
      { label: "CAN_ASSIGN", isSelected: false, disabled: false },
      { label: "CAN_ACTIVATE", isSelected: false, disabled: false },
      { label: "CAN_GENERATE", isSelected: false, disabled: false }
    ];
  }

  

}


















