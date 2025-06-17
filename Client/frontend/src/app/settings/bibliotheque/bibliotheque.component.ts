import { CommonModule } from '@angular/common';
import { HttpClient, HttpEventType } from '@angular/common/http';
import { Component } from '@angular/core';
import { UploadFile } from '../../models/file';

@Component({
    selector: 'app-bibliotheque',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './bibliotheque.component.html',
    styleUrl: './bibliotheque.component.css'
})
export class BibliothequeComponent {
uploads: UploadFile[] = [];
  selectedFile: File | null = null;
  message = '';
  uploadProgress: number = -1; // -1 = rien à afficher
// Ajoute les vérifications dans
  MAX_SIZE_MB = 5;
ALLOWED_TYPES = ['image/jpeg', 'image/png', 'application/pdf'];

constructor(private http: HttpClient) {}



onFilesSelected(event: any) {
  this.uploads = [];
  for (let file of event.target.files) {
    // Vérifie le type
    if (!this.ALLOWED_TYPES.includes(file.type)) {
      alert(`⛔ Type non autorisé : ${file.name}`);
      continue;
    }

    // Vérifie la taille (Mo)
    if (file.size > this.MAX_SIZE_MB * 1024 * 1024) {
      alert(`⛔ Fichier trop gros (> ${this.MAX_SIZE_MB} Mo) : ${file.name}`);
      continue;
    }

    // Fichier accepté
    this.uploads.push({ file, progress: 0 });
  }
}







 

  uploadFiles() {
    this.uploads.forEach((upload, index) => {
      const formData = new FormData();
      formData.append('file', upload.file);

      this.http.post('http://localhost:8080/upload', formData, {
        reportProgress: true,
        observe: 'events',
        responseType: 'text'
      }).subscribe({
        next: (event) => {
          if (event.type === HttpEventType.UploadProgress && event.total) {
            upload.progress = Math.round(100 * (event.loaded / event.total));
          } else if (event.type === HttpEventType.Response) {
            upload.status = '✅ Terminé';
          }
        },
        error: () => {
          upload.status = '❌ Échec';
        }
      });
    });
  }






  

  uploadFile() {
    if (!this.selectedFile) return;

    const formData = new FormData();
    formData.append("file", this.selectedFile);

    this.http.post('http://localhost:8080/upload', formData, {
      reportProgress: true,
      observe: 'events',
      responseType: 'text'
    }).subscribe({
      next: (event) => {
        if (event.type === HttpEventType.UploadProgress && event.total) {
          this.uploadProgress = Math.round(100 * (event.loaded / event.total));
        } else if (event.type === HttpEventType.Response) {
          this.message = event.body ?? 'Fichier envoyé';
          this.uploadProgress = -1; // cacher la barre
        }
      },
      error: () => {
        this.message = "Erreur lors de l'envoi";
        this.uploadProgress = -1;
      }
    });
  }
}




