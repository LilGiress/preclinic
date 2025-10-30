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
//             { "label": "CAN_READ", "selected": false, "disabled": false },
//             { "label": "CAN_WRITE", "selected": false, "disabled": false },
//             { "label": "CAN_CREATE", "selected": false, "disabled": false },
//             { "label": "CAN_UPDATE", "selected": false, "disabled": false },
//             { "label": "CAN_DELETE", "selected": false, "disabled": false },
//             { "label": "CAN_IMPORT", "selected": false, "disabled": false },
//             { "label": "CAN_EXPORT", "selected": false, "disabled": false },
//             { "label": "CAN_APPROVE", "selected": false, "disabled": false },
//             { "label": "CAN_VALIDATE", "selected": false, "disabled": false },
//             { "label": "CAN_ASSIGN", "selected": false, "disabled": false },
//             { "label": "CAN_ACTIVATE", "selected": false, "disabled": false },
//             { "label": "CAN_GENERATE", "selected": false, "disabled": false }
//           ]
//         },
//         {
//           "label": "SESSIONS",
//           "description": "Gestion des sessions",
//           "actions": [
//             { "label": "CAN_READ", "selected": false, "disabled": false },
//             { "label": "CAN_WRITE", "selected": false, "disabled": false },
//             { "label": "CAN_CREATE", "selected": false, "disabled": false },
//             { "label": "CAN_UPDATE", "selected": false, "disabled": false },
//             { "label": "CAN_DELETE", "selected": false, "disabled": false },
//             { "label": "CAN_IMPORT", "selected": false, "disabled": false },
//             { "label": "CAN_EXPORT", "selected": false, "disabled": false },
//             { "label": "CAN_APPROVE", "selected": false, "disabled": false },
//             { "label": "CAN_VALIDATE", "selected": false, "disabled": false },
//             { "label": "CAN_ASSIGN", "selected": false, "disabled": false },
//             { "label": "CAN_ACTIVATE", "selected": false, "disabled": false },
//             { "label": "CAN_GENERATE", "selected": false, "disabled": false }
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
//             { "label": "CAN_READ", "selected": false, "disabled": false },
//             { "label": "CAN_WRITE", "selected": false, "disabled": false },
//             { "label": "CAN_CREATE", "selected": false, "disabled": false },
//             { "label": "CAN_UPDATE", "selected": false, "disabled": false },
//             { "label": "CAN_DELETE", "selected": false, "disabled": false },
//             { "label": "CAN_IMPORT", "selected": false, "disabled": false },
//             { "label": "CAN_EXPORT", "selected": false, "disabled": false },
//             { "label": "CAN_APPROVE", "selected": false, "disabled": false },
//             { "label": "CAN_VALIDATE", "selected": false, "disabled": false },
//             { "label": "CAN_ASSIGN", "selected": false, "disabled": false },
//             { "label": "CAN_ACTIVATE", "selected": false, "disabled": false },
//             { "label": "CAN_GENERATE", "selected": false, "disabled": false }
//           ]
//         },
//         {
//           "label": "CONSULTATIONS",
//           "description": "Gestion des consultations",
//           "actions": [
//             { "label": "CAN_READ", "selected": false, "disabled": false },
//             { "label": "CAN_WRITE", "selected": false, "disabled": false },
//             { "label": "CAN_CREATE", "selected": false, "disabled": false },
//             { "label": "CAN_UPDATE", "selected": false, "disabled": false },
//             { "label": "CAN_DELETE", "selected": false, "disabled": false },
//             { "label": "CAN_IMPORT", "selected": false, "disabled": false },
//             { "label": "CAN_EXPORT", "selected": false, "disabled": false },
//             { "label": "CAN_APPROVE", "selected": false, "disabled": false },
//             { "label": "CAN_VALIDATE", "selected": false, "disabled": false },
//             { "label": "CAN_ASSIGN", "selected": false, "disabled": false },
//             { "label": "CAN_ACTIVATE", "selected": false, "disabled": false },
//             { "label": "CAN_GENERATE", "selected": false, "disabled": false }
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
//             { "label": "CAN_READ", "selected": false, "disabled": false },
//             { "label": "CAN_WRITE", "selected": false, "disabled": false },
//             { "label": "CAN_CREATE", "selected": false, "disabled": false },
//             { "label": "CAN_UPDATE", "selected": false, "disabled": false },
//             { "label": "CAN_DELETE", "selected": false, "disabled": false },
//             { "label": "CAN_IMPORT", "selected": false, "disabled": false },
//             { "label": "CAN_EXPORT", "selected": false, "disabled": false },
//             { "label": "CAN_APPROVE", "selected": false, "disabled": false },
//             { "label": "CAN_VALIDATE", "selected": false, "disabled": false },
//             { "label": "CAN_ASSIGN", "selected": false, "disabled": false },
//             { "label": "CAN_ACTIVATE", "selected": false, "disabled": false },
//             { "label": "CAN_GENERATE", "selected": false, "disabled": false }
//           ]
//         },
//         {
//           "label": "ROLES",
//           "description": "Gestion des rôles et permissions",
//           "actions": [
//             { "label": "CAN_READ", "selected": false, "disabled": false },
//             { "label": "CAN_WRITE", "selected": false, "disabled": false },
//             { "label": "CAN_CREATE", "selected": false, "disabled": false },
//             { "label": "CAN_UPDATE", "selected": false, "disabled": false },
//             { "label": "CAN_DELETE", "selected": false, "disabled": false },
//             { "label": "CAN_IMPORT", "selected": false, "disabled": false },
//             { "label": "CAN_EXPORT", "selected": false, "disabled": false },
//             { "label": "CAN_APPROVE", "selected": false, "disabled": false },
//             { "label": "CAN_VALIDATE", "selected": false, "disabled": false },
//             { "label": "CAN_ASSIGN", "selected": false, "disabled": false },
//             { "label": "CAN_ACTIVATE", "selected": false, "disabled": false },
//             { "label": "CAN_GENERATE", "selected": false, "disabled": false }
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
//             { "label": "CAN_READ", "selected": false, "disabled": false },
//             { "label": "CAN_WRITE", "selected": false, "disabled": false },
//             { "label": "CAN_CREATE", "selected": false, "disabled": false },
//             { "label": "CAN_UPDATE", "selected": false, "disabled": false },
//             { "label": "CAN_DELETE", "selected": false, "disabled": false },
//             { "label": "CAN_IMPORT", "selected": false, "disabled": false },
//             { "label": "CAN_EXPORT", "selected": false, "disabled": false },
//             { "label": "CAN_APPROVE", "selected": false, "disabled": false },
//             { "label": "CAN_VALIDATE", "selected": false, "disabled": false },
//             { "label": "CAN_ASSIGN", "selected": false, "disabled": false },
//             { "label": "CAN_ACTIVATE", "selected": false, "disabled": false },
//             { "label": "CAN_GENERATE", "selected": false, "disabled": false }
//           ]
//         },
//         {
//           "label": "PAIEMENTS",
//           "description": "Suivi des paiements",
//           "actions": [
//             { "label": "CAN_READ", "selected": false, "disabled": false },
//             { "label": "CAN_WRITE", "selected": false, "disabled": false },
//             { "label": "CAN_CREATE", "selected": false, "disabled": false },
//             { "label": "CAN_UPDATE", "selected": false, "disabled": false },
//             { "label": "CAN_DELETE", "selected": false, "disabled": false },
//             { "label": "CAN_IMPORT", "selected": false, "disabled": false },
//             { "label": "CAN_EXPORT", "selected": false, "disabled": false },
//             { "label": "CAN_APPROVE", "selected": false, "disabled": false },
//             { "label": "CAN_VALIDATE", "selected": false, "disabled": false },
//             { "label": "CAN_ASSIGN", "selected": false, "disabled": false },
//             { "label": "CAN_ACTIVATE", "selected": false, "disabled": false },
//             { "label": "CAN_GENERATE", "selected": false, "disabled": false }
//           ]
//         }
//       ]
//     }
  


// ]


private authorities: Permission[] = [
    {
      "label": "MEDECIN",
      "description": "Gestion des formations",
      "actions": [],
      "subModules": [
        {
          "label": "MEDECINE",
          "description": "Suivi et gestion des medecins",
          "actions": [
          { label: "CAN_READ_MEDECIN", selected: false  },
          { label: "CAN_WRITE_MEDECIN", selected: false  },
          { label: "CAN_CREATE_MEDECIN", selected: false  },
          { label: "CAN_UPDATE_MEDECIN", selected: false  },
          { label: "CAN_DELETE_MEDECIN", selected: false  },
          { label: "CAN_IMPORT_MEDECIN", selected: false  },
          { label: "CAN_EXPORT_MEDECIN", selected: false  },
          { label: "CAN_APPROVE_MEDECIN", selected: false  },
          { label: "CAN_VALIDATE_MEDECIN", selected: false  },
          { label: "CAN_ASSIGN_MEDECIN", selected: false  },
          { label: "CAN_ACTIVATE_MEDECIN", selected: false  },
          { label: "CAN_GENERATE_MEDECIN", selected: false  }
        ]
        }
      ]
    },
    {
      "label": "INFIRMIER",
      "description": "Gestion des patients",
      "actions": [],
      "subModules": [
        {
          "label": "INFIRMIER",
          "description": "Suivi de base des infirmiers",
          "actions": [
          { label: "CAN_READ_INFIRMIER", selected: false   },
          { label: "CAN_WRITE_INFIRMIER", selected: false   },
          { label: "CAN_CREATE_INFIRMIER", selected: false   },
          { label: "CAN_UPDATE_INFIRMIER", selected: false  },
          { label: "CAN_DELETE_INFIRMIER", selected: false   },
          { label: "CAN_IMPORT_INFIRMIER", selected: false   },
          { label: "CAN_EXPORT_INFIRMIER", selected: false   },
          { label: "CAN_APPROVE_INFIRMIER", selected: false   },
          { label: "CAN_VALIDATE_INFIRMIER", selected: false   },
          { label: "CAN_ASSIGN_INFIRMIER", selected: false   },
          { label: "CAN_ACTIVATE_INFIRMIER", selected: false   },
          { label: "CAN_GENERATE_INFIRMIER", selected: false   }
        ]
        }
      ]
    },
    {
      "label": "CONFIGURATION",
      "description": "privilège super administrateur",
      "actions": [],
      "subModules": [
    
      {
        "label": "TR_PATIENTS",
        "description": "Gestion des patients",
        "actions": [
          { label: "CAN_READ_PATIENTS", selected: false   },
          { label: "CAN_WRITE_PATIENTS", selected: false   },
          { label: "CAN_CREATE_PATIENTS", selected: false   },
          { label: "CAN_UPDATE_PATIENTS", selected: false   },
          { label: "CAN_DELETE_PATIENTS", selected: false   },
          { label: "CAN_IMPORT_PATIENTS", selected: false   },
          { label: "CAN_EXPORT_PATIENTS", selected: false   },
          { label: "CAN_APPROVE_PATIENTS", selected: false   },
          { label: "CAN_VALIDATE_PATIENTS", selected: false   },
          { label: "CAN_ASSIGN_PATIENTS", selected: false  },
          { label: "CAN_ACTIVATE_PATIENTS", selected: false   },
          { label: "CAN_GENERATE_PATIENTS", selected: false   }
        ]
      },
      {
        "label": "MEDECINE",
        "description": "Suivi et gestion des medecins",
        "actions": [
          { label: "CAN_READ_MEDECIN", selected: false   },
          { label: "CAN_WRITE_MEDECIN", selected: false   },
          { label: "CAN_CREATE_MEDECIN", selected: false   },
          { label: "CAN_UPDATE_MEDECIN", selected: false   },
          { label: "CAN_DELETE_MEDECIN", selected: false   },
          { label: "CAN_IMPORT_MEDECIN", selected: false   },
          { label: "CAN_EXPORT_MEDECIN", selected: false   },
          { label: "CAN_APPROVE_MEDECIN", selected: false   },
          { label: "CAN_VALIDATE_MEDECIN", selected: false   },
          { label: "CAN_ASSIGN_MEDECIN", selected: false   },
          { label: "CAN_ACTIVATE_MEDECIN", selected: false   },
          { label: "CAN_GENERATE_MEDECIN", selected: false   }
        ]
      },
      {
        "label": "INFIRMIER",
        "description": "Suivi de base des infirmiers",
        "actions": [
          { label: "CAN_READ_INFIRMIER", selected: false   },
          { label: "CAN_WRITE_INFIRMIER", selected: false   },
          { label: "CAN_CREATE_INFIRMIER", selected: false   },
          { label: "CAN_UPDATE_INFIRMIER", selected: false  },
          { label: "CAN_DELETE_INFIRMIER", selected: false   },
          { label: "CAN_IMPORT_INFIRMIER", selected: false   },
          { label: "CAN_EXPORT_INFIRMIER", selected: false   },
          { label: "CAN_APPROVE_INFIRMIER", selected: false   },
          { label: "CAN_VALIDATE_INFIRMIER", selected: false   },
          { label: "CAN_ASSIGN_INFIRMIER", selected: false   },
          { label: "CAN_ACTIVATE_INFIRMIER", selected: false   },
          { label: "CAN_GENERATE_INFIRMIER", selected: false   }
        ]
      },
      {
        "label": "SECRETARIA",
        "description": "Gestion administrative des patients et personnel",
        "actions": [
          { label: "CAN_READ_SECRETAIRE", selected: false   },
          { label: "CAN_WRITE_SECRETAIRE", selected: false   },
          { label: "CAN_CREATE_SECRETAIRE", selected: false   },
          { label: "CAN_UPDATE_SECRETAIRE", selected: false   },
          { label: "CAN_DELETE_SECRETAIRE", selected: false   },
          { label: "CAN_IMPORT_SECRETAIRE", selected: false   },
          { label: "CAN_EXPORT_SECRETAIRE", selected: false   },
          { label: "CAN_APPROVE_SECRETAIRE", selected: false   },
          { label: "CAN_VALIDATE_SECRETAIRE", selected: false   },
          { label: "CAN_ASSIGN_SECRETAIRE", selected: false   },
          { label: "CAN_ACTIVATE_SECRETAIRE", selected: false   },
          { label: "CAN_GENERATE_SECRETAIRE", selected: false   }
        ]
      },
      {
        "label": "PNEUMOLOGIE",
        "description": "Accès à ses propres informations médicales",
        "actions": [
          { label: "CAN_READ_PNEUMOLOGIE", selected: false   },
          { label: "CAN_WRITE_PNEUMOLOGIE", selected: false  },
          { label: "CAN_CREATE_PNEUMOLOGIE", selected: false  },
          { label: "CAN_UPDATE_PNEUMOLOGIE", selected: false  },
          { label: "CAN_DELETE_PNEUMOLOGIE", selected: false  },
          { label: "CAN_IMPORT_PNEUMOLOGIE", selected: false  },
          { label: "CAN_EXPORT_PNEUMOLOGIE", selected: false  },
          { label: "CAN_APPROVE_PNEUMOLOGIE", selected: false  },
          { label: "CAN_VALIDATE_PNEUMOLOGIES", selected: false  },
          { label: "CAN_ASSIGN_PNEUMOLOGIE", selected: false  },
          { label: "CAN_ACTIVATE_PNEUMOLOGIE", selected: false  },
          { label: "CAN_GENERATE_PNEUMOLOGIE", selected: false  }
        ]
      },
      {
        "label": "MEDECINE INTERNE",
        "description": "Accès à ses propres informations médicales",
        "actions": [
          { label: "CAN_READ_MEDECINE_INTERNE", selected: false   },
          { label: "CAN_WRITE_MEDECINE_INTERNE", selected: false  },
          { label: "CAN_CREATE_MEDECINE_INTERNE", selected: false  },
          { label: "CAN_UPDATE_MEDECINE_INTERNE", selected: false  },
          { label: "CAN_DELETE_MEDECINE_INTERNE", selected: false  },
          { label: "CAN_IMPORT_MEDECINE_INTERNE", selected: false  },
          { label: "CAN_EXPORT_MEDECINE_INTERNE", selected: false  },
          { label: "CAN_APPROVE_MEDECINE_INTERNE", selected: false  },
          { label: "CAN_VALIDATE_MEDECINE_INTERNE", selected: false  },
          { label: "CAN_ASSIGN_MEDECINE_INTERNE", selected: false  },
          { label: "CAN_ACTIVATE_MEDECINE_INTERNE", selected: false  },
          { label: "CAN_GENERATE_MEDECINE_INTERNE", selected: false  }
        ]
      },
      {
        "label": "CARDIOLOGIE",
        "description": "Accès à ses propres informations médicales",
        "actions": [
          { label: "CAN_READ_CARDIOLOGIE", selected: false   },
          { label: "CAN_WRITE_CARDIOLOGIE", selected: false  },
          { label: "CAN_CREATE_CARDIOLOGIE", selected: false  },
          { label: "CAN_UPDATE_CARDIOLOGIE", selected: false  },
          { label: "CAN_DELETE_CARDIOLOGIE", selected: false  },
          { label: "CAN_IMPORT_CARDIOLOGIE", selected: false  },
          { label: "CAN_EXPORT_CARDIOLOGIE", selected: false  },
          { label: "CAN_APPROVE_CARDIOLOGIE", selected: false  },
          { label: "CAN_VALIDATE_CARDIOLOGIE", selected: false  },
          { label: "CAN_ASSIGN_CARDIOLOGIE", selected: false  },
          { label: "CAN_ACTIVATE_CARDIOLOGIE", selected: false },
          { label: "CAN_GENERATE_CARDIOLOGIE", selected: false  }
        ]
      },
      {
        "label": "SOINS PALLIATIFS",
        "description": "Gestion des services liés au soins palliatifs.",
        "actions": [
          { label: "CAN_READ_SOINS_PALLIATIFS", selected: false   },
          { label: "CAN_WRITE_SOINS_PALLIATIFS", selected: false  },
          { label: "CAN_CREATE_SOINS_PALLIATIFS", selected: false  },
          { label: "CAN_UPDATE_SOINS_PALLIATIFS", selected: false  },
          { label: "CAN_DELETE_SOINS_PALLIATIFS", selected: false  },
          { label: "CAN_IMPORT_SOINS_PALLIATIFS", selected: false  },
          { label: "CAN_EXPORT_SOINS_PALLIATIFS", selected: false  },
          { label: "CAN_APPROVE_SOINS_PALLIATIFS", selected: false  },
          { label: "CAN_VALIDATE_SOINS_PALLIATIFS", selected: false  },
          { label: "CAN_ASSIGN_SOINS_PALLIATIFS", selected: false  },
          { label: "CAN_ACTIVATE_SOINS_PALLIATIFS", selected: false  },
          { label: "CAN_GENERATE_SOINS_PALLIATIFS", selected: false  }
        ]
      },
      {
        "label": "PEDIATRIE",
        "description": "Gestion des services liés à la pédiatrie.",
        "actions": [
          { label: "CAN_READ_PEDIATRIE", selected: false   },
          { label: "CAN_WRITE_PEDIATRIE", selected: false  },
          { label: "CAN_CREATE_PEDIATRIE", selected: false  },
          { label: "CAN_UPDATE_PEDIATRIE", selected: false  },
          { label: "CAN_DELETE_PEDIATRIE", selected: false  },
          { label: "CAN_IMPORT_PEDIATRIE", selected: false  },
          { label: "CAN_EXPORT_PEDIATRIE", selected: false  },
          { label: "CAN_APPROVE_PEDIATRIE", selected: false  },
          { label: "CAN_VALIDATE_PEDIATRIE", selected: false  },
          { label: "CAN_ASSIGN_PEDIATRIE", selected: false  },
          { label: "CAN_ACTIVATE_PEDIATRIE", selected: false  },
          { label: "CAN_GENERATE_PEDIATRIE", selected: false  }
        ]
      },
      {
        "label": "HOSPITALISATION",
        "description": "Gestion des services liés à l'hospitalisation.",
        "actions": [
          { label: "CAN_READ_HOSPITALISATION", selected: false   },
          { label: "CAN_WRITE_HOSPITALISATION", selected: false  },
          { label: "CAN_CREATE_HOSPITALISATION", selected: false  },
          { label: "CAN_UPDATE_HOSPITALISATION", selected: false  },
          { label: "CAN_DELETE_HOSPITALISATION", selected: false  },
          { label: "CAN_IMPORT_HOSPITALISATION", selected: false  },
          { label: "CAN_EXPORT_HOSPITALISATION", selected: false  },
          { label: "CAN_APPROVE_HOSPITALISATION", selected: false  },
          { label: "CAN_VALIDATE_HOSPITALISATION", selected: false  },
          { label: "CAN_ASSIGN_HOSPITALISATION", selected: false  },
          { label: "CAN_ACTIVATE_HOSPITALISATION", selected: false  },
          { label: "CAN_GENERATE_HOSPITALISATION", selected: false  }
        ]
      },
      {
        "label": "OBSTETRIQUE",
        "description": "Gestion des services liés à obstetrique.",
        "actions": [
          { label: "CAN_READ_OBSTETRIQUE", selected: false   },
          { label: "CAN_WRITE_OBSTETRIQUE", selected: false  },
          { label: "CAN_CREATE_OBSTETRIQUE", selected: false  },
          { label: "CAN_UPDATE_OBSTETRIQUE", selected: false  },
          { label: "CAN_DELETE_OBSTETRIQUE", selected: false  },
          { label: "CAN_IMPORT_OBSTETRIQUE", selected: false  },
          { label: "CAN_EXPORT_OBSTETRIQUE", selected: false  },
          { label: "CAN_APPROVE_OBSTETRIQUE", selected: false  },
          { label: "CAN_VALIDATE_OBSTETRIQUE", selected: false  },
          { label: "CAN_ASSIGN_OBSTETRIQUE", selected: false  },
          { label: "CAN_ACTIVATE_OBSTETRIQUE", selected: false  },
          { label: "CAN_GENERATE_OBSTETRIQUE", selected: false  }
        ]
      },
      {
        "label": "IVG",
        "description": "Gestion des services liés à IVG.",
        "actions": [
          { label: "CAN_READ_IVG", selected: false   },
          { label: "CAN_WRITE_IVG", selected: false  },
          { label: "CAN_CREATE_IVG", selected: false  },
          { label: "CAN_UPDATE_IVG", selected: false  },
          { label: "CAN_DELETE_IVG", selected: false  },
          { label: "CAN_IMPORT_IVG", selected: false  },
          { label: "CAN_EXPORT_IVG", selected: false  },
          { label: "CAN_APPROVE_IVG", selected: false  },
          { label: "CAN_VALIDATE_IVG", selected: false  },
          { label: "CAN_ASSIGN_IVG", selected: false  },
          { label: "CAN_ACTIVATE_IVG", selected: false  },
          { label: "CAN_GENERATE_IVG", selected: false  }
        ]
      },
      {
        "label": "CHIRURGIE BUCCALE",
        "description": "Gestion des services de chirurgie buccale.",
        "actions": [
          { label: "CAN_READ_CHIRURGIE_BUCCALE", selected: false   },
          { label: "CAN_WRITE_CHIRURGIE_BUCCALE", selected: false  },
          { label: "CAN_CREATE_CHIRURGIE_BUCCALE", selected: false  },
          { label: "CAN_UPDATE_TCHIRURGIE_BUCCALE", selected: false  },
          { label: "CAN_DELETE_CHIRURGIE_BUCCALE", selected: false  },
          { label: "CAN_IMPORT_CHIRURGIE_BUCCALE", selected: false  },
          { label: "CAN_EXPORT_CHIRURGIE_BUCCALE", selected: false  },
          { label: "CAN_APPROVE_CHIRURGIE_BUCCALE", selected: false  },
          { label: "CAN_VALIDATE_CHIRURGIE_BUCCALE", selected: false  },
          { label: "CAN_ASSIGN_CHIRURGIE_BUCCALE", selected: false  },
          { label: "CAN_ACTIVATE_CHIRURGIE_BUCCALE", selected: false  },
          { label: "CAN_GENERATE_CHIRURGIE_BUCCALE", selected: false  }
        ]
      },
      {
        "label": "CHIRURGIE GENERALE",
        "description": "Gestion des services de la chirurgie générale.",
        "actions": [
          { label: "CAN_READ_CHIRURGIE_GENERALE", selected: false   },
          { label: "CAN_WRITE_CHIRURGIE_GENERALE", selected: false  },
          { label: "CAN_CREATE_CHIRURGIE_GENERALE", selected: false  },
          { label: "CAN_UPDATE_CHIRURGIE_GENERALE", selected: false  },
          { label: "CAN_DELETE_CHIRURGIE_GENERALE", selected: false  },
          { label: "CAN_IMPORT_CHIRURGIE_GENERALE", selected: false  },
          { label: "CAN_EXPORT_CHIRURGIE_GENERALE", selected: false  },
          { label: "CAN_APPROVE_CHIRURGIE_GENERALE", selected: false  },
          { label: "CAN_VALIDATE_CHIRURGIE_GENERALE", selected: false  },
          { label: "CAN_ASSIGN_CHIRURGIE_GENERALE", selected: false  },
          { label: "CAN_ACTIVATE_CHIRURGIE_GENERALE", selected: false  },
          { label: "CAN_GENERATE_CHIRURGIE_GENERALE", selected: false  }
        ]
      },
      {
        "label": "CHIRURGIE PLASTIQUE",
        "description": "Gestion des services de la chirurgie plastique.",
        "actions": [
          { label: "CAN_READ_CHIRURGIE_PLASTIQUE", selected: false   },
          { label: "CAN_WRITE_CHIRURGIE_PLASTIQUE", selected: false  },
          { label: "CAN_CREATE_CHIRURGIE_PLASTIQUE", selected: false  },
          { label: "CAN_UPDATE_CHIRURGIE_PLASTIQUE", selected: false  },
          { label: "CAN_DELETE_CHIRURGIE_PLASTIQUE", selected: false  },
          { label: "CAN_IMPORT_CHIRURGIE_PLASTIQUE", selected: false  },
          { label: "CAN_EXPORT_CHIRURGIE_PLASTIQUE", selected: false  },
          { label: "CAN_APPROVE_CHIRURGIE_PLASTIQUE", selected: false  },
          { label: "CAN_VALIDATE_CHIRURGIE_PLASTIQUE", selected: false  },
          { label: "CAN_ASSIGN_CHIRURGIE_PLASTIQUE", selected: false  },
          { label: "CAN_ACTIVATE_CHIRURGIE_PLASTIQUE", selected: false  },
          { label: "CAN_GENERATE_CHIRURGIE_PLASTIQUE", selected: false  }
        ]
      },
      {
        "label": "CHIRURGIE VASCULAIRE",
        "description": "Gestion des services de la chirurgie vasculaire.",
        "actions": [
          { label: "CAN_READ_CHIRURGIE_VASCULAIRE", selected: false   },
          { label: "CAN_WRITE_CHIRURGIE_VASCULAIRE", selected: false  },
          { label: "CAN_CREATE_CHIRURGIE_VASCULAIRE", selected: false  },
          { label: "CAN_UPDATE_CHIRURGIE_VASCULAIRE", selected: false  },
          { label: "CAN_DELETE_CHIRURGIE_VASCULAIRE", selected: false  },
          { label: "CAN_IMPORT_CHIRURGIE_VASCULAIRE", selected: false  },
          { label: "CAN_EXPORT_CHIRURGIE_VASCULAIRE", selected: false  },
          { label: "CAN_APPROVE_CHIRURGIE_VASCULAIRE", selected: false  },
          { label: "CAN_VALIDATE_CHIRURGIE_VASCULAIRE", selected: false  },
          { label: "CAN_ASSIGN_CHIRURGIE_VASCULAIRE", selected: false  },
          { label: "CAN_ACTIVATE_CHIRURGIE_VASCULAIRE", selected: false  },
          { label: "CAN_GENERATE_CHIRURGIE_VASCULAIRE", selected: false  }
        ]
      },
      {
        "label": "ORL",
        "description": "Gestion des services d'ORL.",
        "actions": [
          { label: "CAN_READ_ORL", selected: false   },
          { label: "CAN_WRITE_ORL", selected: false  },
          { label: "CAN_CREATE_ORL", selected: false  },
          { label: "CAN_UPDATE_ORL", selected: false  },
          { label: "CAN_DELETE_ORL", selected: false  },
          { label: "CAN_IMPORT_ORL", selected: false  },
          { label: "CAN_EXPORT_ORL", selected: false  },
          { label: "CAN_APPROVE_ORL", selected: false  },
          { label: "CAN_VALIDATE_ORL", selected: false  },
          { label: "CAN_ASSIGN_ORL", selected: false  },
          { label: "CAN_ACTIVATE_ORL", selected: false  },
          { label: "CAN_GENERATE_TR_PATIENTS", selected: false  }
        ]
      },
      {
        "label": "OPHTALMOLOGIE",
        "description": "Gestion des services d'ophtalmologie.",
        "actions": [
          { label: "CAN_READ_OPHTALMOLOGIE", selected: false   },
          { label: "CAN_WRITE_OPHTALMOLOGIE", selected: false  },
          { label: "CAN_CREATE_OPHTALMOLOGIE", selected: false  },
          { label: "CAN_UPDATE_OPHTALMOLOGIE", selected: false  },
          { label: "CAN_DELETE_OPHTALMOLOGIE", selected: false  },
          { label: "CAN_IMPORT_OPHTALMOLOGIE", selected: false  },
          { label: "CAN_EXPORT_OPHTALMOLOGIE", selected: false  },
          { label: "CAN_APPROVE_OPHTALMOLOGIE", selected: false  },
          { label: "CAN_VALIDATE_OPHTALMOLOGIE", selected: false  },
          { label: "CAN_ASSIGN_OPHTALMOLOGIE", selected: false },
          { label: "CAN_ACTIVATE_OPHTALMOLOGIE", selected: false  },
          { label: "CAN_GENERATE_OPHTALMOLOGIE", selected: false  }
        ]
      },
      {
        "label": "ORTHOPEDIE",
        "description": "Gestion des services d'orthopédie.",
        "actions": [
          { label: "CAN_READ_ORTHOPEDIE", selected: false  },
          { label: "CAN_WRITE_ORTHOPEDIE", selected: false },
          { label: "CAN_CREATE_ORTHOPEDIE", selected: false },
          { label: "CAN_UPDATE_ORTHOPEDIE", selected: false },
          { label: "CAN_DELETE_ORTHOPEDIE", selected: false },
          { label: "CAN_IMPORT_ORTHOPEDIE", selected: false },
          { label: "CAN_EXPORT_ORTHOPEDIE", selected: false },
          { label: "CAN_APPROVE_ORTHOPEDIE", selected: false },
          { label: "CAN_VALIDATE_ORTHOPEDIE", selected: false },
          { label: "CAN_ASSIGN_ORTHOPEDIE", selected: false },
          { label: "CAN_ACTIVATE_ORTHOPEDIE", selected: false },
          { label: "CAN_GENERATE_ORTHOPEDIE", selected: false }
        ]
      },
      {
        "label": "UROLOGIE",
        "description": "Gestion des services d'urologie.",
        "actions": [
          { label: "CAN_READ_UROLOGIE", selected: false  },
          { label: "CAN_WRITE_UROLOGIE", selected: false },
          { label: "CAN_CREATE_UROLOGIE", selected: false },
          { label: "CAN_UPDATE_UROLOGIE", selected: false },
          { label: "CAN_DELETE_UROLOGIE", selected: false },
          { label: "CAN_IMPORT_UROLOGIE", selected: false },
          { label: "CAN_EXPORT_UROLOGIE", selected: false },
          { label: "CAN_APPROVE_UROLOGIE", selected: false },
          { label: "CAN_VALIDATE_UROLOGIE", selected: false },
          { label: "CAN_ASSIGN_UROLOGIE", selected: false },
          { label: "CAN_ACTIVATE_UROLOGIE", selected: false },
          { label: "CAN_GENERATE_UROLOGIE", selected: false }
        ]
      },
      {
        "label": "ANESTHESISTE",
        "description": "Gestion des services d'anesthésie.",
        "actions": [
          { label: "CAN_READ_ANESTHESIE", selected: false  },
          { label: "CAN_WRITE_ANESTHESIE", selected: false },
          { label: "CAN_CREATE_ANESTHESIE", selected: false },
          { label: "CAN_UPDATE_ANESTHESIE", selected: false },
          { label: "CAN_DELETE_ANESTHESIE", selected: false },
          { label: "CAN_IMPORT_ANESTHESIE", selected: false },
          { label: "CAN_EXPORT_ANESTHESIE", selected: false },
          { label: "CAN_APPROVE_ANESTHESIE", selected: false },
          { label: "CAN_VALIDATE_ANESTHESIE", selected: false },
          { label: "CAN_ASSIGN_ANESTHESIE", selected: false },
          { label: "CAN_ACTIVATE_ANESTHESIE", selected: false },
          { label: "CAN_GENERATE_ANESTHESIE", selected: false }
        ]
      },
      {
        "label": "GYNECOLOGIE & OBSTETRIQUE",
        "description": "Gestion des services de gynécologie et obstetrique",
        "actions": [
          { label: "CAN_READ_GYNECOLOGIE_OBSTETRIQUE", selected: false  },
          { label: "CAN_WRITE_GYNECOLOGIE_OBSTETRIQUE", selected: false },
          { label: "CAN_CREATE_GYNECOLOGIE_OBSTETRIQUE", selected: false },
          { label: "CAN_UPDATE_GYNECOLOGIE_OBSTETRIQUE", selected: false },
          { label: "CAN_DELETE_GYNECOLOGIE_OBSTETRIQUE", selected: false },
          { label: "CAN_IMPORT_GYNECOLOGIE_OBSTETRIQUE", selected: false },
          { label: "CAN_EXPORT_GYNECOLOGIE_OBSTETRIQUE", selected: false },
          { label: "CAN_APPROVE_GYNECOLOGIE_OBSTETRIQUE", selected: false },
          { label: "CAN_VALIDATE_GYNECOLOGIE_OBSTETRIQUE", selected: false },
          { label: "CAN_ASSIGN_GYNECOLOGIE_OBSTETRIQUE", selected: false },
          { label: "CAN_ACTIVATE_GYNECOLOGIE_OBSTETRIQUE", selected: false },
          { label: "CAN_GENERATE_GYNECOLOGIE_OBSTETRIQUE", selected: false }
        ]
      },
      {
        "label": "SERVICE DES URGENCES",
        "description": "Gestion des services d'urgence médicale.",
        "actions": [
          { label: "CAN_READ_URGENCES", selected: false  },
          { label: "CAN_WRITE_URGENCES", selected: false },
          { label: "CAN_CREATE_URGENCES", selected: false },
          { label: "CAN_UPDATE_URGENCES", selected: false },
          { label: "CAN_DELETE_URGENCES", selected: false },
          { label: "CAN_IMPORT_URGENCES", selected: false },
          { label: "CAN_EXPORT_URGENCES", selected: false},
          { label: "CAN_APPROVE_URGENCES", selected: false },
          { label: "CAN_VALIDATE_URGENCES", selected: false },
          { label: "CAN_ASSIGN_URGENCES", selected: false },
          { label: "CAN_ACTIVATE_URGENCES", selected: false },
          { label: "CAN_GENERATE_URGENCES", selected: false }
        ]
      },
      {
        "label": "RADIOLOGIE",
        "description": "Gestion des services de radiologie",
        "actions": [
          { label: "CAN_READ_RADIOLOGIE", selected: false  },
          { label: "CAN_WRITE_RADIOLOGIE", selected: false },
          { label: "CAN_CREATE_RADIOLOGIE", selected: false },
          { label: "CAN_UPDATE_RADIOLOGIE", selected: false },
          { label: "CAN_DELETE_RADIOLOGIE", selected: false },
          { label: "CAN_IMPORT_RADIOLOGIE", selected: false },
          { label: "CAN_EXPORT_RADIOLOGIE", selected: false },
          { label: "CAN_APPROVE_RADIOLOGIE", selected: false },
          { label: "CAN_VALIDATE_RADIOLOGIE", selected: false },
          { label: "CAN_ASSIGN_RADIOLOGIE", selected: false },
          { label: "CAN_ACTIVATE_RADIOLOGIE", selected: false },
          { label: "CAN_GENERATE_RADIOLOGIE", selected: false }
        ]
      },
      {
        "label": "MEDECINE NUCLEAIRE",
        "description": "Gestion des services de la médecine nucléaire",
        "actions": [
          { label: "CAN_READ_MEDECINE_NUCLEAIRE", selected: false  },
          { label: "CAN_WRITE_MEDECINE_NUCLEAIRE", selected: false },
          { label: "CAN_CREATE_MEDECINE_NUCLEAIRE", selected: false },
          { label: "CAN_UPDATE_MEDECINE_NUCLEAIRE", selected: false },
          { label: "CAN_DELETE_MEDECINE_NUCLEAIRE", selected: false },
          { label: "CAN_IMPORT_MEDECINE_NUCLEAIRE", selected: false },
          { label: "CAN_EXPORT_MEDECINE_NUCLEAIRE", selected: false },
          { label: "CAN_APPROVE_TMEDECINE_NUCLEAIRE", selected: false },
          { label: "CAN_VALIDATE_MEDECINE_NUCLEAIRE", selected: false },
          { label: "CAN_ASSIGN_MEDECINE_NUCLEAIRE", selected: false },
          { label: "CAN_ACTIVATE_MEDECINE_NUCLEAIRE", selected: false },
          { label: "CAN_GENERATE_MEDECINE_NUCLEAIRE", selected: false }
        ]
      },
      {
        "label": "PEDIATRIE",
        "description": "Gestion des services de pédiatrie.",
        "actions": [
          { label: "CAN_READ_PEDIATRIE", selected: false  },
          { label: "CAN_WRITE_PEDIATRIE", selected: false },
          { label: "CAN_CREATE_PEDIATRIE", selected: false },
          { label: "CAN_UPDATE_PEDIATRIE", selected: false },
          { label: "CAN_DELETE_PEDIATRIE", selected: false },
          { label: "CAN_IMPORT_PEDIATRIE", selected: false },
          { label: "CAN_EXPORT_PEDIATRIE", selected: false },
          { label: "CAN_APPROVE_PEDIATRIE", selected: false },
          { label: "CAN_VALIDATE_PEDIATRIE", selected: false },
          { label: "CAN_ASSIGN_PEDIATRIE", selected: false },
          { label: "CAN_ACTIVATE_PEDIATRIE", selected: false },
          { label: "CAN_GENERATE_PEDIATRIE", selected: false }
        ]
      },
      {
        "label": "PHARMACIE",
        "description": "Accès à ses propres informations médicales",
        "actions": [
          { label: "CAN_READ_PHARMACIE", selected: false  },
          { label: "CAN_WRITE_PHARMACIE", selected: false },
          { label: "CAN_CREATE_PHARMACIE", selected: false },
          { label: "CAN_UPDATE_PHARMACIE", selected: false },
          { label: "CAN_DELETE_PHARMACIE", selected: false },
          { label: "CAN_IMPORT_PHARMACIE", selected: false },
          { label: "CAN_EXPORT_PHARMACIE", selected: false },
          { label: "CAN_APPROVE_PHARMACIE", selected: false },
          { label: "CAN_VALIDATE_PHARMACIE", selected: false },
          { label: "CAN_ASSIGN_PHARMACIE", selected: false },
          { label: "CAN_ACTIVATE_PHARMACIE", selected: false },
          { label: "CAN_GENERATE_PHARMACIE", selected: false }
        ]
      }
      ]
    },
    {
      "label": "PATIENT",
      "description": "Gestion de la comptabilité",
      "actions": [],
      "subModules": [
        {
        "label": "TR_PATIENTS",
        "description": "Accès à ses propres informations médicales",
        "actions": [
          { label: "CAN_READ_TR_PATIENTS", selected: false  },
          { label: "CAN_WRITE_TR_PATIENTS", selected: false },
          { label: "CAN_CREATE_TR_PATIENTS", selected: false },
          { label: "CAN_UPDATE_TR_PATIENTS", selected: false },
          { label: "CAN_DELETE_TR_PATIENTS", selected: false },
          { label: "CAN_IMPORT_TR_PATIENTS", selected: false },
          { label: "CAN_EXPORT_TR_PATIENTS", selected: false },
          { label: "CAN_APPROVE_TR_PATIENTS", selected: false },
          { label: "CAN_VALIDATE_TR_PATIENTS", selected: false },
          { label: "CAN_ASSIGN_TR_PATIENTS", selected: false },
          { label: "CAN_ACTIVATE_TR_PATIENTS", selected: false },
          { label: "CAN_GENERATE_TR_PATIENTS", selected: false }
        ]
      }
      ]
    },
    {
      "label": "PHARMACIE",
      "description": "Gestion de la comptabilité",
      "actions": [],
      "subModules": [
        {
          "label": "PHARMACIE",
          "description": "Gestion des médicaments",
          "actions": [
          { label: "CAN_READ_PHARMACIE", selected: false  },
          { label: "CAN_WRITE_PHARMACIE", selected: false },
          { label: "CAN_CREATE_PHARMACIE", selected: false },
          { label: "CAN_UPDATE_PHARMACIE", selected: false },
          { label: "CAN_DELETE_PHARMACIE", selected: false },
          { label: "CAN_IMPORT_PHARMACIE", selected: false },
          { label: "CAN_EXPORT_PHARMACIE", selected: false },
          { label: "CAN_APPROVE_PHARMACIE", selected: false },
          { label: "CAN_VALIDATE_PHARMACIE", selected: false },
          { label: "CAN_ASSIGN_PHARMACIE", selected: false },
          { label: "CAN_ACTIVATE_PHARMACIE", selected: false },
          { label: "CAN_GENERATE_PHARMACIE", selected: false }
        ]
        }
        
      ]
    },
      {
      "label": "PEDIATRIE",
      "description": "Gestion de la comptabilité",
      "actions": [],
      "subModules": [
        {
          "label": "PEDIATRIE",
          "description": "Gestion des services de pédiatrie.",
          "actions": [
          { label: "CAN_READ_PEDIATRIE", selected: false  },
          { label: "CAN_WRITE_PEDIATRIE", selected: false },
          { label: "CAN_CREATE_PEDIATRIE", selected: false },
          { label: "CAN_UPDATE_PEDIATRIE", selected: false },
          { label: "CAN_DELETE_PEDIATRIE", selected: false },
          { label: "CAN_IMPORT_PEDIATRIE", selected: false },
          { label: "CAN_EXPORT_PEDIATRIE", selected: false },
          { label: "CAN_APPROVE_PEDIATRIE", selected: false },
          { label: "CAN_VALIDATE_PEDIATRIE", selected: false },
          { label: "CAN_ASSIGN_PEDIATRIE", selected: false },
          { label: "CAN_ACTIVATE_PEDIATRIE", selected: false },
          { label: "CAN_GENERATE_PEDIATRIE", selected: false }
        ]
        }
        
      ]
    },
     {
      "label": "IMAGERIE MEDICALE",
      "description": "Gestion de la comptabilité",
      "actions": [],
      "subModules": [
         {
        "label": "RADIOLOGIE",
        "description": "Gestion des services de radiologie",
        "actions": [
          { label: "CAN_READ_RADIOLOGIE", selected: false  },
          { label: "CAN_WRITE_RADIOLOGIE", selected: false },
          { label: "CAN_CREATE_RADIOLOGIE", selected: false },
          { label: "CAN_UPDATE_RADIOLOGIE", selected: false },
          { label: "CAN_DELETE_RADIOLOGIE", selected: false },
          { label: "CAN_IMPORT_RADIOLOGIE", selected: false },
          { label: "CAN_EXPORT_RADIOLOGIE", selected: false },
          { label: "CAN_APPROVE_RADIOLOGIE", selected: false },
          { label: "CAN_VALIDATE_RADIOLOGIE", selected: false },
          { label: "CAN_ASSIGN_RADIOLOGIE", selected: false },
          { label: "CAN_ACTIVATE_RADIOLOGIE", selected: false },
          { label: "CAN_GENERATE_RADIOLOGIE", selected: false }
        ]
      },
      {
        label: "MEDECINE NUCLEAIRE",
        "description": "Gestion des services de la médecine nucléaire",
        "actions": [
          { label: "CAN_READ_MEDECINE_NUCLEAIRE", selected: false  },
          { label: "CAN_WRITE_MEDECINE_NUCLEAIRE", selected: false },
          { label: "CAN_CREATE_MEDECINE_NUCLEAIRE", selected: false },
          { label: "CAN_UPDATE_MEDECINE_NUCLEAIRE", selected: false },
          { label: "CAN_DELETE_MEDECINE_NUCLEAIRE", selected: false },
          { label: "CAN_IMPORT_MEDECINE_NUCLEAIRE", selected: false },
          { label: "CAN_EXPORT_MEDECINE_NUCLEAIRE", selected: false },
          { label: "CAN_APPROVE_TMEDECINE_NUCLEAIRE", selected: false },
          { label: "CAN_VALIDATE_MEDECINE_NUCLEAIRE", selected: false },
          { label: "CAN_ASSIGN_MEDECINE_NUCLEAIRE", selected: false },
          { label: "CAN_ACTIVATE_MEDECINE_NUCLEAIRE", selected: false },
          { label: "CAN_GENERATE_MEDECINE_NUCLEAIRE", selected: false }
        ]
      }
        
      ]
    },
    {
      "label": "MEDECINE D'URGENCE",
      "description": "Gestion de la comptabilité",
      "actions": [],
      "subModules": [
        {
          "label": "SERVICE DES URGENCES",
          "description": "Gestion des services de pédiatrie.",
          "actions": [
          { label: "CAN_READ_URGENCES", selected: false  },
          { label: "CAN_WRITE_URGENCES", selected: false },
          { label: "CAN_CREATE_URGENCES", selected: false },
          { label: "CAN_UPDATE_URGENCES", selected: false },
          { label: "CAN_DELETE_URGENCES", selected: false },
          { label: "CAN_IMPORT_URGENCES", selected: false },
          { label: "CAN_EXPORT_URGENCES", selected: false},
          { label: "CAN_APPROVE_URGENCES", selected: false },
          { label: "CAN_VALIDATE_URGENCES", selected: false },
          { label: "CAN_ASSIGN_URGENCES", selected: false },
          { label: "CAN_ACTIVATE_URGENCES", selected: false },
          { label: "CAN_GENERATE_URGENCES", selected: false }
        ]
        }
        
      ]
    },
    {
      "label": "GYNECOLOGIE ET OBSTETRIQUE",
      "description": "Gestion de la comptabilité",
      "actions": [],
      "subModules": [
        {
        "label": "GYNECOLOGIE & OBSTETRIQUE",
        "description": "Gestion des services de gynécologie et obstetrique",
        "actions": [
          { label: "CAN_READ_GYNECOLOGIE_OBSTETRIQUE", selected: false  },
          { label: "CAN_WRITE_GYNECOLOGIE_OBSTETRIQUE", selected: false },
          { label: "CAN_CREATE_GYNECOLOGIE_OBSTETRIQUE", selected: false },
          { label: "CAN_UPDATE_GYNECOLOGIE_OBSTETRIQUE", selected: false },
          { label: "CAN_DELETE_GYNECOLOGIE_OBSTETRIQUE", selected: false },
          { label: "CAN_IMPORT_GYNECOLOGIE_OBSTETRIQUE", selected: false },
          { label: "CAN_EXPORT_GYNECOLOGIE_OBSTETRIQUE", selected: false },
          { label: "CAN_APPROVE_GYNECOLOGIE_OBSTETRIQUE", selected: false },
          { label: "CAN_VALIDATE_GYNECOLOGIE_OBSTETRIQUE", selected: false },
          { label: "CAN_ASSIGN_GYNECOLOGIE_OBSTETRIQUE", selected: false },
          { label: "CAN_ACTIVATE_GYNECOLOGIE_OBSTETRIQUE", selected: false },
          { label: "CAN_GENERATE_GYNECOLOGIE_OBSTETRIQUE", selected: false }
        ]
      }
        
      ]
    },
     {
      "label": "ANESTHESIE",
      "description": "Gestion de la comptabilité",
      "actions": [],
      "subModules": [
        {
        "label": "ANESTHESISTE",
        "description": "Gestion des services d'anesthésie.",
        "actions": [
          { label: "CAN_READ_ANESTHESIE", selected: false  },
          { label: "CAN_WRITE_ANESTHESIE", selected: false },
          { label: "CAN_CREATE_ANESTHESIE", selected: false },
          { label: "CAN_UPDATE_ANESTHESIE", selected: false },
          { label: "CAN_DELETE_ANESTHESIE", selected: false },
          { label: "CAN_IMPORT_ANESTHESIE", selected: false },
          { label: "CAN_EXPORT_ANESTHESIE", selected: false },
          { label: "CAN_APPROVE_ANESTHESIE", selected: false },
          { label: "CAN_VALIDATE_ANESTHESIE", selected: false },
          { label: "CAN_ASSIGN_ANESTHESIE", selected: false },
          { label: "CAN_ACTIVATE_ANESTHESIE", selected: false },
          { label: "CAN_GENERATE_ANESTHESIE", selected: false }
        ]
      }
        
      ]
    },
     {
      "label": "CHIRURGIE",
      "description": "Gestion de la comptabilité",
      "actions": [],
      "subModules": [
         {
        "label": "CHIRURGIE BUCCALE",
        "description": "Gestion des services de chirurgie buccale.",
        "actions": [
          { label: "CAN_READ_CHIRURGIE_BUCCALE", selected: false  },
          { label: "CAN_WRITE_CHIRURGIE_BUCCALE", selected: false },
          { label: "CAN_CREATE_CHIRURGIE_BUCCALE", selected: false },
          { label: "CAN_UPDATE_TCHIRURGIE_BUCCALE", selected: false },
          { label: "CAN_DELETE_CHIRURGIE_BUCCALE", selected: false },
          { label: "CAN_IMPORT_CHIRURGIE_BUCCALE", selected: false },
          { label: "CAN_EXPORT_CHIRURGIE_BUCCALE", selected: false },
          { label: "CAN_APPROVE_CHIRURGIE_BUCCALE", selected: false },
          { label: "CAN_VALIDATE_CHIRURGIE_BUCCALE", selected: false },
          { label: "CAN_ASSIGN_CHIRURGIE_BUCCALE", selected: false },
          { label: "CAN_ACTIVATE_CHIRURGIE_BUCCALE", selected: false },
          { label: "CAN_GENERATE_CHIRURGIE_BUCCALE", selected: false }
        ]
      },
      {
        "label": "CHIRURGIE GENERALE",
        "description": "Gestion des services de la chirurgie générale.",
        "actions": [
          { label: "CAN_READ_CHIRURGIE_GENERALE", selected: false  },
          { label: "CAN_WRITE_CHIRURGIE_GENERALE", selected: false },
          { label: "CAN_CREATE_CHIRURGIE_GENERALE", selected: false },
          { label: "CAN_UPDATE_CHIRURGIE_GENERALE", selected: false },
          { label: "CAN_DELETE_CHIRURGIE_GENERALE", selected: false },
          { label: "CAN_IMPORT_CHIRURGIE_GENERALE", selected: false},
          { label: "CAN_EXPORT_CHIRURGIE_GENERALE", selected: false },
          { label: "CAN_APPROVE_CHIRURGIE_GENERALE", selected: false },
          { label: "CAN_VALIDATE_CHIRURGIE_GENERALE", selected: false },
          { label: "CAN_ASSIGN_CHIRURGIE_GENERALE", selected: false },
          { label: "CAN_ACTIVATE_CHIRURGIE_GENERALE", selected: false },
          { label: "CAN_GENERATE_CHIRURGIE_GENERALE", selected: false }
        ]
      },
      {
        "label": "CHIRURGIE PLASTIQUE",
        "description": "Gestion des services de la chirurgie plastique.",
        "actions": [
          { label: "CAN_READ_CHIRURGIE_PLASTIQUE", selected: false  },
          { label: "CAN_WRITE_CHIRURGIE_PLASTIQUE", selected: false },
          { label: "CAN_CREATE_CHIRURGIE_PLASTIQUE", selected: false },
          { label: "CAN_UPDATE_CHIRURGIE_PLASTIQUE", selected: false },
          { label: "CAN_DELETE_CHIRURGIE_PLASTIQUE", selected: false },
          { label: "CAN_IMPORT_CHIRURGIE_PLASTIQUE", selected: false },
          { label: "CAN_EXPORT_CHIRURGIE_PLASTIQUE", selected: false },
          { label: "CAN_APPROVE_CHIRURGIE_PLASTIQUE", selected: false },
          { label: "CAN_VALIDATE_CHIRURGIE_PLASTIQUE", selected: false },
          { label: "CAN_ASSIGN_CHIRURGIE_PLASTIQUE", selected: false },
          { label: "CAN_ACTIVATE_CHIRURGIE_PLASTIQUE", selected: false },
          { label: "CAN_GENERATE_CHIRURGIE_PLASTIQUE", selected: false }
        ]
      },
      {
        "label": "CHIRURGIE VASCULAIRE",
        "description": "Gestion des services de la chirurgie vasculaire.",
        "actions": [
          { label: "CAN_READ_CHIRURGIE_VASCULAIRE", selected: false  },
          { label: "CAN_WRITE_CHIRURGIE_VASCULAIRE", selected: false },
          { label: "CAN_CREATE_CHIRURGIE_VASCULAIRE", selected: false },
          { label: "CAN_UPDATE_CHIRURGIE_VASCULAIRE", selected: false },
          { label: "CAN_DELETE_CHIRURGIE_VASCULAIRE", selected: false },
          { label: "CAN_IMPORT_CHIRURGIE_VASCULAIRE", selected: false },
          { label: "CAN_EXPORT_CHIRURGIE_VASCULAIRE", selected: false },
          { label: "CAN_APPROVE_CHIRURGIE_VASCULAIRE", selected: false },
          { label: "CAN_VALIDATE_CHIRURGIE_VASCULAIRE", selected: false },
          { label: "CAN_ASSIGN_CHIRURGIE_VASCULAIRE", selected: false },
          { label: "CAN_ACTIVATE_CHIRURGIE_VASCULAIRE", selected: false },
          { label: "CAN_GENERATE_CHIRURGIE_VASCULAIRE", selected: false }
        ]
      },
      {
        "label": "ORL",
        "description": "Gestion des services d'ORL.",
        "actions": [
          { label: "CAN_READ_ORL", selected: false  },
          { label: "CAN_WRITE_ORL", selected: false },
          { label: "CAN_CREATE_ORL", selected: false },
          { label: "CAN_UPDATE_ORL", selected: false },
          { label: "CAN_DELETE_ORL", selected: false },
          { label: "CAN_IMPORT_ORL", selected: false },
          { label: "CAN_EXPORT_ORL", selected: false },
          { label: "CAN_APPROVE_ORL", selected: false },
          { label: "CAN_VALIDATE_ORL", selected: false },
          { label: "CAN_ASSIGN_ORL", selected: false },
          { label: "CAN_ACTIVATE_ORL", selected: false },
          { label: "CAN_GENERATE_TR_PATIENTS", selected: false }
        ]
      },
      {
        "label": "OPHTALMOLOGIE",
        "description": "Gestion des services d'ophtalmologie.",
        "actions": [
          { label: "CAN_READ_OPHTALMOLOGIE", selected: false  },
          { label: "CAN_WRITE_OPHTALMOLOGIE", selected: false },
          { label: "CAN_CREATE_OPHTALMOLOGIE", selected: false },
          { label: "CAN_UPDATE_OPHTALMOLOGIE", selected: false },
          { label: "CAN_DELETE_OPHTALMOLOGIE", selected: false },
          { label: "CAN_IMPORT_OPHTALMOLOGIE", selected: false },
          { label: "CAN_EXPORT_OPHTALMOLOGIE", selected: false },
          { label: "CAN_APPROVE_OPHTALMOLOGIE", selected: false },
          { label: "CAN_VALIDATE_OPHTALMOLOGIE", selected: false },
          { label: "CAN_ASSIGN_OPHTALMOLOGIE", selected: false },
          { label: "CAN_ACTIVATE_OPHTALMOLOGIE", selected: false },
          { label: "CAN_GENERATE_OPHTALMOLOGIE", selected: false }
        ]
      },
      {
        "label": "ORTHOPEDIE",
        "description": "Gestion des services d'orthopédie.",
        "actions": [
          { label: "CAN_READ_ORTHOPEDIE", selected: false  },
          { label: "CAN_WRITE_ORTHOPEDIE", selected: false },
          { label: "CAN_CREATE_ORTHOPEDIE", selected: false },
          { label: "CAN_UPDATE_ORTHOPEDIE", selected: false },
          { label: "CAN_DELETE_ORTHOPEDIE", selected: false },
          { label: "CAN_IMPORT_ORTHOPEDIE", selected: false },
          { label: "CAN_EXPORT_ORTHOPEDIE", selected: false },
          { label: "CAN_APPROVE_ORTHOPEDIE", selected: false },
          { label: "CAN_VALIDATE_ORTHOPEDIE", selected: false },
          { label: "CAN_ASSIGN_ORTHOPEDIE", selected: false },
          { label: "CAN_ACTIVATE_ORTHOPEDIE", selected: false },
          { label: "CAN_GENERATE_ORTHOPEDIE", selected: false }
        ]
      },
      {
        "label": "UROLOGIE",
        "description": "Gestion des services d'urologie.",
        "actions": [
          { label: "CAN_READ_UROLOGIE", selected: false  },
          { label: "CAN_WRITE_UROLOGIE", selected: false },
          { label: "CAN_CREATE_UROLOGIE", selected: false },
          { label: "CAN_UPDATE_UROLOGIE", selected: false },
          { label: "CAN_DELETE_UROLOGIE", selected: false },
          { label: "CAN_IMPORT_UROLOGIE", selected: false },
          { label: "CAN_EXPORT_UROLOGIE", selected: false },
          { label: "CAN_APPROVE_UROLOGIE", selected: false },
          { label: "CAN_VALIDATE_UROLOGIE", selected: false },
          { label: "CAN_ASSIGN_UROLOGIE", selected: false },
          { label: "CAN_ACTIVATE_UROLOGIE", selected: false },
          { label: "CAN_GENERATE_UROLOGIE", selected: false }
        ]
      }
        
      ]
    },
    {
      "label": "MEDECINE GENERALE",
      "description": "Gestion de la comptabilité",
      "actions": [],
      "subModules": [
         {
        "label": "SOINS PALLIATIFS",
        "description": "Gestion des services liés au soins palliatifs.",
        "actions": [
          { label: "CAN_READ_SOINS_PALLIATIFS", selected: false  },
          { label: "CAN_WRITE_SOINS_PALLIATIFS", selected: false },
          { label: "CAN_CREATE_SOINS_PALLIATIFS", selected: false },
          { label: "CAN_UPDATE_SOINS_PALLIATIFS", selected: false },
          { label: "CAN_DELETE_SOINS_PALLIATIFS", selected: false },
          { label: "CAN_IMPORT_SOINS_PALLIATIFS", selected: false },
          { label: "CAN_EXPORT_SOINS_PALLIATIFS", selected: false },
          { label: "CAN_APPROVE_SOINS_PALLIATIFS", selected: false },
          { label: "CAN_VALIDATE_SOINS_PALLIATIFS", selected: false },
          { label: "CAN_ASSIGN_SOINS_PALLIATIFS", selected: false },
          { label: "CAN_ACTIVATE_SOINS_PALLIATIFS", selected: false },
          { label: "CAN_GENERATE_SOINS_PALLIATIFS", selected: false }
        ]
      },
      {
        "label": "PEDIATRIE",
        "description": "Gestion des services liés à la pédiatrie.",
        "actions": [
          { label: "CAN_READ_PEDIATRIE", selected: false  },
          { label: "CAN_WRITE_PEDIATRIE", selected: false },
          { label: "CAN_CREATE_PEDIATRIE", selected: false },
          { label: "CAN_UPDATE_PEDIATRIE", selected: false },
          { label: "CAN_DELETE_PEDIATRIE", selected: false },
          { label: "CAN_IMPORT_PEDIATRIE", selected: false },
          { label: "CAN_EXPORT_PEDIATRIE", selected: false },
          { label: "CAN_APPROVE_PEDIATRIE", selected: false },
          { label: "CAN_VALIDATE_PEDIATRIE", selected: false },
          { label: "CAN_ASSIGN_PEDIATRIE", selected: false },
          { label: "CAN_ACTIVATE_PEDIATRIE", selected: false },
          { label: "CAN_GENERATE_PEDIATRIE", selected: false }
        ]
      },
      {
        "label": "HOSPITALISATION",
        "description": "Gestion des services liés à l'hospitalisation.",
        "actions": [
          { label: "CAN_READ_HOSPITALISATION", selected: false  },
          { label: "CAN_WRITE_HOSPITALISATION", selected: false },
          { label: "CAN_CREATE_HOSPITALISATION", selected: false },
          { label: "CAN_UPDATE_HOSPITALISATION", selected: false },
          { label: "CAN_DELETE_HOSPITALISATION", selected: false },
          { label: "CAN_IMPORT_HOSPITALISATION", selected: false },
          { label: "CAN_EXPORT_HOSPITALISATION", selected: false },
          { label: "CAN_APPROVE_HOSPITALISATION", selected: false },
          { label: "CAN_VALIDATE_HOSPITALISATION", selected: false },
          { label: "CAN_ASSIGN_HOSPITALISATION", selected: false },
          { label: "CAN_ACTIVATE_HOSPITALISATION", selected: false },
          { label: "CAN_GENERATE_HOSPITALISATION", selected: false }
        ]
      },
      {
        "label": "OBSTETRIQUE",
        "description": "Gestion des services liés à obstetrique.",
        "actions": [
          { label: "CAN_READ_OBSTETRIQUE", selected: false  },
          { label: "CAN_WRITE_OBSTETRIQUE", selected: false },
          { label: "CAN_CREATE_OBSTETRIQUE", selected: false },
          { label: "CAN_UPDATE_OBSTETRIQUE", selected: false },
          { label: "CAN_DELETE_OBSTETRIQUE", selected: false },
          { label: "CAN_IMPORT_OBSTETRIQUE", selected: false },
          { label: "CAN_EXPORT_OBSTETRIQUE", selected: false },
          { label: "CAN_APPROVE_OBSTETRIQUE", selected: false },
          { label: "CAN_VALIDATE_OBSTETRIQUE", selected: false },
          { label: "CAN_ASSIGN_OBSTETRIQUE", selected: false },
          { label: "CAN_ACTIVATE_OBSTETRIQUE", selected: false },
          { label: "CAN_GENERATE_OBSTETRIQUE", selected: false }
        ]
      },
      {
        "label": "IVG",
        "description": "Gestion des services liés à IVG.",
        "actions": [
          { label: "CAN_READ_IVG", selected: false  },
          { label: "CAN_WRITE_IVG", selected: false },
          { label: "CAN_CREATE_IVG", selected: false },
          { label: "CAN_UPDATE_IVG", selected: false },
          { label: "CAN_DELETE_IVG", selected: false },
          { label: "CAN_IMPORT_IVG", selected: false },
          { label: "CAN_EXPORT_IVG", selected: false },
          { label: "CAN_APPROVE_IVG", selected: false },
          { label: "CAN_VALIDATE_IVG", selected: false },
          { label: "CAN_ASSIGN_IVG", selected: false },
          { label: "CAN_ACTIVATE_IVG", selected: false },
          { label: "CAN_GENERATE_IVG", selected: false }
        ]
      }
        
      ]
    },
    {
      "label": "MEDECINE SPECIALISEE",
      "description": "Gestion de la comptabilité",
      "actions": [],
      "subModules": [
         {
        "label": "PNEUMOLOGIE",
        "description": "Accès à ses propres informations médicales",
        "actions": [
          { label: "CAN_READ_PNEUMOLOGIE", selected: false  },
          { label: "CAN_WRITE_PNEUMOLOGIE", selected: false },
          { label: "CAN_CREATE_PNEUMOLOGIE", selected: false },
          { label: "CAN_UPDATE_PNEUMOLOGIE", selected: false },
          { label: "CAN_DELETE_PNEUMOLOGIE", selected: false },
          { label: "CAN_IMPORT_PNEUMOLOGIE", selected: false },
          { label: "CAN_EXPORT_PNEUMOLOGIE", selected: false },
          { label: "CAN_APPROVE_PNEUMOLOGIE", selected: false },
          { label: "CAN_VALIDATE_PNEUMOLOGIES", selected: false },
          { label: "CAN_ASSIGN_PNEUMOLOGIE", selected: false },
          { label: "CAN_ACTIVATE_PNEUMOLOGIE", selected: false },
          { label: "CAN_GENERATE_PNEUMOLOGIE", selected: false }
        ]
      },
      {
        "label": "MEDECINE INTERNE",
        "description": "Accès à ses propres informations médicales",
        "actions": [
          { label: "CAN_READ_MEDECINE_INTERNE", selected: false  },
          { label: "CAN_WRITE_MEDECINE_INTERNE", selected: false },
          { label: "CAN_CREATE_MEDECINE_INTERNE", selected: false },
          { label: "CAN_UPDATE_MEDECINE_INTERNE", selected: false },
          { label: "CAN_DELETE_MEDECINE_INTERNE", selected: false },
          { label: "CAN_IMPORT_MEDECINE_INTERNE", selected: false },
          { label: "CAN_EXPORT_MEDECINE_INTERNE", selected: false },
          { label: "CAN_APPROVE_MEDECINE_INTERNE", selected: false },
          { label: "CAN_VALIDATE_MEDECINE_INTERNE", selected: false },
          { label: "CAN_ASSIGN_MEDECINE_INTERNE", selected: false },
          { label: "CAN_ACTIVATE_MEDECINE_INTERNE", selected: false },
          { label: "CAN_GENERATE_MEDECINE_INTERNE", selected: false }
        ]
      },
      {
        "label": "CARDIOLOGIE",
        "description": "Accès à ses propres informations médicales",
        "actions": [
          { label: "CAN_READ_CARDIOLOGIE", selected: false  },
          { label: "CAN_WRITE_CARDIOLOGIE", selected: false },
          { label: "CAN_CREATE_CARDIOLOGIE", selected: false },
          { label: "CAN_UPDATE_CARDIOLOGIE", selected: false },
          { label: "CAN_DELETE_CARDIOLOGIE", selected: false },
          { label: "CAN_IMPORT_CARDIOLOGIE", selected: false },
          { label: "CAN_EXPORT_CARDIOLOGIE", selected: false },
          { label: "CAN_APPROVE_CARDIOLOGIE", selected: false },
          { label: "CAN_VALIDATE_CARDIOLOGIE", selected: false },
          { label: "CAN_ASSIGN_CARDIOLOGIE", selected: false },
          { label: "CAN_ACTIVATE_CARDIOLOGIE", selected: false },
          { label: "CAN_GENERATE_CARDIOLOGIE", selected: false }
        ]
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
      { label: "CAN_READ", selected: false },
      { label: "CAN_WRITE", selected: false },
      { label: "CAN_CREATE", selected: false },
      { label: "CAN_UPDATE", selected: false },
      { label: "CAN_DELETE", selected: false },
      { label: "CAN_IMPORT", selected: false },
      { label: "CAN_EXPORT", selected: false },
      { label: "CAN_APPROVE", selected: false },
      { label: "CAN_VALIDATE", selected: false },
      { label: "CAN_ASSIGN", selected: false },
      { label: "CAN_ACTIVATE", selected: false },
      { label: "CAN_GENERATE", selected: false }
    ];
  }

  

}


















