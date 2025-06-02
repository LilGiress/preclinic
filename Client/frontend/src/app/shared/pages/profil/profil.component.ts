import { Component, OnInit } from '@angular/core';
import { FileService } from '../../../services/file/file.service';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-profil',
    imports: [CommonModule],
    templateUrl: './profil.component.html',
    styleUrl: './profil.component.css'
})
export class ProfilComponent implements OnInit{
photoUrl: any|"assets/img/patients/patient.jpg";
  selectedFile: any;
  userId!: number;
  role!: string;
  folders: string[] = [];
  message: string = '';
  folderName: any;

  constructor(
    private fileService:FileService
  ){}

  ngOnInit() {
    this.loadFolders();
  }
  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }
  
  uploadPhoto() {
    if (this.selectedFile) {
      this.fileService.uploadProfilePhoto(this.selectedFile, this.userId, this.role).subscribe({
        next: url => this.photoUrl = url,
        error: () => alert("Échec de l'upload")
      });
    }
  }

  createFolder() {
    if (!this.folderName) {
      this.message = "Veuillez entrer un nom de dossier";
      return;
    }

    const userId = 12345; // Remplacer par l'ID de l'utilisateur connecté
    const role = "PATIENT"; // Remplacer par le rôle de l'utilisateur connecté

    this.fileService.createFolder(userId, role, this.folderName).subscribe({
      next: response => this.message = "Dossier créé avec succès",
      error: () => this.message = "Erreur lors de la création du dossier"
    });
  }

  loadFolders() {
    const userId = 12345; // Remplacer par l'ID de l'utilisateur connecté
    const role = "PATIENT"; // Remplacer par le rôle de l'utilisateur

    this.fileService.listFolders(userId, role).subscribe({
      next: folders => this.folders = folders,
      error: () => this.message = "Erreur lors du chargement des dossiers"
    });
  }

  deleteFolder(folderName: string) {
    const userId = 12345;
    const role = "PATIENT";

    this.fileService.deleteFolder(userId, role, folderName).subscribe({
      next: () => {
        this.message = "Dossier supprimé !";
        this.loadFolders(); // Recharger la liste après suppression
      },
      error: () => this.message = "Erreur lors de la suppression"
    });
  }

}
