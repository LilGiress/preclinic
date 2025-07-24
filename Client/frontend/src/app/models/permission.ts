export interface IPermission {
    id?:number;
        description?:string;
      canRead?:boolean;   // Lire les informations
      canWrite?:boolean;  // Modifier les informations
      canCreate?:boolean; // Ajouter de nouvelles entrées
      canDelete?:boolean; // Supprimer une entrée
      canImport?:boolean; // Importer des données
      canExport?:boolean; // Exporter des données
      canApprove?:boolean; // Approuver des documents ou dossiers
      canValidate?:boolean; // Valider un dossier médical
      canAssign?:boolean;  // Assigner un médecin à un patient
      canGenerateReport?:boolean; // Générer des rapports
      canActivate?:boolean;

}

export class Permission implements IPermission{
    constructor (
       public id?:number,
        public description?:string,
        public  canRead?:boolean,   // Lire les informations
        public  canWrite?:boolean,  // Modifier les informations
        public  canCreate?:boolean, // Ajouter de nouvelles entrées
        public  canDelete?:boolean, // Supprimer une entrée
        public  canImport?:boolean, // Importer des données
        public  canExport?:boolean, // Exporter des données
        public  canApprove?:boolean, // Approuver des documents ou dossiers
        public  canValidate?:boolean, // Valider un dossier médical
        public  canAssign?:boolean,  // Assigner un médecin à un patient
        public  canGenerateReport?:boolean, // Générer des rapports
        public  canActivate?:boolean,
    ){}

}