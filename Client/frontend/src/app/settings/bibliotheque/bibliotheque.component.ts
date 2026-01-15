import { CommonModule, NgFor, NgIf } from '@angular/common';
import { HttpClient, HttpEventType } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UploadFile } from '../../models/file';
import { ModalService } from '../../services/modal.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
declare var $: any;

interface FileItem {
  id?: number;
  name?: string;
  type?: string; // pdf, docx, xlsx, pptx, folder
  size?: number;
  sizeFormatted?: string;
  modifiedDate?: Date;
  modifiedDateFormatted?: string;
  path?: string;
  icon?: string;
  content?: any;
}

interface Upload {
  file?: File;
  progress?: number;
  status?: string;
  id?: string;
}

interface QuickAccessItem {
  icon: string;
  label: string;
  fileCount: number;
  totalSize: number;
  color: string;
  type: string;
}
@Component({
    selector: 'app-bibliotheque',
    standalone: true,
    imports: [CommonModule,ReactiveFormsModule, NgIf, NgFor, FormsModule],
    templateUrl: './bibliotheque.component.html',
    styleUrl: './bibliotheque.component.css'
})
export class BibliothequeComponent implements OnInit {
uploads: UploadFile[] = [];
  selectedFile: File | null = null;
  message = '';
  uploadProgress: number = -1; // -1 = rien à afficher
// Ajoute les vérifications dans
  MAX_SIZE_MB = 5;
ALLOWED_TYPES = ['image/jpeg', 'image/png', 'application/pdf'];
 files: FileItem[] = [];
  filteredFiles: FileItem[] = [];
  Uploads: Upload[] = [];
  searchQuery = '';
  sortBy = 'name'; // name, date, size
  sortOrder = 'asc'; // asc, desc
  selectedFiles: FileItem | null = null;
  userRole = 'Super User';

  quickAccessItems: QuickAccessItem[] = [
    {
      icon: 'folder',
      label: 'Dossiers',
      fileCount: 4510,
      totalSize: 0.1,
      color: 'orange',
      type: 'folder'
    },
    {
      icon: 'pdf',
      label: 'Fichiers PDF',
      fileCount: 212,
      totalSize: 5.0,
      color: 'red',
      type: 'pdf'
    },
    {
      icon: 'word',
      label: 'Documents Word',
      fileCount: 120,
      totalSize: 17.5,
      color: 'blue',
      type: 'docx'
    },
    {
      icon: 'excel',
      label: 'Fichiers Excel',
      fileCount: 22,
      totalSize: 3.1,
      color: 'green',
      type: 'xlsx'
    },
    {
      icon: 'ppt',
      label: 'Fichiers PowerPoint',
      fileCount: 22,
      totalSize: 3.1,
      color: 'orange',
      type: 'pptx'
    }
  ];


constructor(private http: HttpClient,
   private spinner: NgxSpinnerService,
    private modalService: ModalService
) {}



// onFilesSelected(event: any) {
//   this.uploads = [];
//   for (let file of event.target.files) {
//     // Vérifie le type
//     if (!this.ALLOWED_TYPES.includes(file.type)) {
//       alert(`⛔ Type non autorisé : ${file.name}`);
//       continue;
//     }

//     // Vérifie la taille (Mo)
//     if (file.size > this.MAX_SIZE_MB * 1024 * 1024) {
//       alert(`⛔ Fichier trop gros (> ${this.MAX_SIZE_MB} Mo) : ${file.name}`);
//       continue;
//     }

//     // Fichier accepté
//     this.uploads.push({ file, progress: 0 });
//   }
// }







 

  // uploadFiles() {
  //   this.uploads.forEach((upload, index) => {
  //     const formData = new FormData();
  //     formData.append('file', upload.file);

  //     this.http.post('http://localhost:8080/upload', formData, {
  //       reportProgress: true,
  //       observe: 'events',
  //       responseType: 'text'
  //     }).subscribe({
  //       next: (event) => {
  //         if (event.type === HttpEventType.UploadProgress && event.total) {
  //           upload.progress = Math.round(100 * (event.loaded / event.total));
  //         } else if (event.type === HttpEventType.Response) {
  //           upload.status = '✅ Terminé';
  //         }
  //       },
  //       error: () => {
  //         upload.status = '❌ Échec';
  //       }
  //     });
  //   });
  // }






  

  // uploadFile() {
  //   if (!this.selectedFile) return;

  //   const formData = new FormData();
  //   formData.append("file", this.selectedFile);

  //   this.http.post('http://localhost:8080/upload', formData, {
  //     reportProgress: true,
  //     observe: 'events',
  //     responseType: 'text'
  //   }).subscribe({
  //     next: (event) => {
  //       if (event.type === HttpEventType.UploadProgress && event.total) {
  //         this.uploadProgress = Math.round(100 * (event.loaded / event.total));
  //       } else if (event.type === HttpEventType.Response) {
  //         this.message = event.body ?? 'Fichier envoyé';
  //         this.uploadProgress = -1; // cacher la barre
  //       }
  //     },
  //     error: () => {
  //       this.message = "Erreur lors de l'envoi";
  //       this.uploadProgress = -1;
  //     }
  //   });
  // }







  
  

  ngOnInit(): void {
    this.loadFiles();
  }

  // Load files from backend
  loadFiles(): void {
    this.spinner.show();
    // this.fileService.getAll().subscribe({
    //   next: (data) => {
    //     this.files = data;
    //     this.filteredFiles = data;
    //     this.spinner.hide();
    //   },
    //   error: (error) => {
    //     this.spinner.hide();
    //     console.error('Error loading files:', error);
    //     this.modalService.openWarning('Error', 'Failed to load files');
    //   }
    // });

    // Demo data
    this.files = [
      {
        id: 1,
        name: 'Document.pdf',
        type: 'pdf',
        size: 1600000000,
        sizeFormatted: '1.6 Go',
        modifiedDate: new Date(Date.now() - 86400000),
        modifiedDateFormatted: 'Hier 16:28',
        icon: 'fa-file-pdf'
      },
      {
        id: 2,
        name: 'Fiches des tests',
        type: 'folder',
        size: 400000000,
        sizeFormatted: '400 Mo',
        modifiedDate: new Date('2024-07-06'),
        modifiedDateFormatted: '06/07/2024 16:28',
        icon: 'fa-folder'
      },
      {
        id: 3,
        name: 'Fichier.docx',
        type: 'docx',
        size: 14000000,
        sizeFormatted: '14 Mo',
        modifiedDate: new Date(),
        modifiedDateFormatted: 'Auj. 16:28',
        icon: 'fa-file-word'
      },
      {
        id: 4,
        name: 'Rapport.xlsx',
        type: 'xlsx',
        size: 2500000,
        sizeFormatted: '2.5 Mo',
        modifiedDate: new Date(Date.now() - 172800000),
        modifiedDateFormatted: 'Il y a 2 jours',
        icon: 'fa-file-excel'
      },
      {
        id: 5,
        name: 'Présentation.pptx',
        type: 'pptx',
        size: 8000000,
        sizeFormatted: '8 Mo',
        modifiedDate: new Date(Date.now() - 259200000),
        modifiedDateFormatted: 'Il y a 3 jours',
        icon: 'fa-file-powerpoint'
      }
    ];
    this.filteredFiles = [...this.files];
    this.spinner.hide();
  }

  // File selection for upload
  onFilesSelected(event: any): void {
    const files = event.target.files;
    if (files && files.length > 0) {
      for (let file of files) {
        this.Uploads.push({
          file: file,
          progress: 0,
          status: 'En attente',
          id: this.generateId()
        });
      }
    }
  }

  // Upload files
  uploadFiles(): void {
    if (this.uploads.length === 0) {
      this.modalService.openWarning('Attention', 'Veuillez sélectionner des fichiers');
      return;
    }

    this.uploads.forEach((Uploads, index) => {
      this.uploadFile(Uploads, index);
    });
  }

  // Upload single file
  private uploadFile(Uploads: Upload, index: number): void {
    const interval = setInterval(() => {
      Uploads.progress! += Math.random() * 30;
      if (Uploads.progress! >= 100) {
        Uploads.progress = 100;
        Uploads.status = 'Uploadé';
        clearInterval(interval);

        // Add file to list
        const newFile: FileItem = {
          id: Math.max(...this.files.map(f => f.id || 0)) + 1,
          name: Uploads.file!.name,
          type: this.getFileType(Uploads.file!.name),
          size: Uploads.file!.size,
          sizeFormatted: this.formatFileSize(Uploads.file!.size),
          modifiedDate: new Date(),
          modifiedDateFormatted: 'À l\'instant',
          icon: this.getFileIcon(Uploads.file!.name)
        };

        this.files.unshift(newFile);
        this.filteredFiles = [...this.files];

        // Remove from uploads after 2 seconds
        setTimeout(() => {
          this.uploads.splice(index, 1);
        }, 2000);

        this.modalService.openSuccessModal('Fichier uploadé avec succès!');
      }
    }, 500);
  }

  // Search files
  searchFiles(): void {
    if (!this.searchQuery.trim()) {
      this.filteredFiles = [...this.files];
      return;
    }

    const query = this.searchQuery.toLowerCase();
    this.filteredFiles = this.files.filter(file =>
      file.name?.toLowerCase().includes(query)
    );
  }

  // Sort files
  sortFiles(sortBy: string): void {
    this.sortBy = sortBy;
    this.sortOrder = this.sortOrder === 'asc' ? 'desc' : 'asc';

    this.filteredFiles.sort((a, b) => {
      let compareResult = 0;

      switch (sortBy) {
        case 'name':
          compareResult = (a.name || '').localeCompare(b.name || '');
          break;
        case 'date':
          compareResult = (a.modifiedDate?.getTime() || 0) - (b.modifiedDate?.getTime() || 0);
          break;
        case 'size':
          compareResult = (a.size || 0) - (b.size || 0);
          break;
      }

      return this.sortOrder === 'asc' ? compareResult : -compareResult;
    });
  }

  // Preview file
  previewFile(file: FileItem): void {
    this.selectedFiles = file;

    if (file.type === 'folder') {
      this.modalService.openSuccessModal(`Ouvrir le dossier: ${file.name}`);
      return;
    }

    // this.fileService.getFileContent(file.id).subscribe({
    //   next: (content) => {
    //     this.showPreview(file, content);
    //   },
    //   error: (error) => {
    //     this.modalService.openWarning('Erreur', 'Impossible de prévisualiser le fichier');
    //   }
    // });

    // Demo preview
    this.showPreview(file);
  }

  private showPreview(file: FileItem, content?: any): void {
    const modal = document.getElementById('previewModal') as HTMLElement;
    const previewArea = document.getElementById('previewArea');

    if (previewArea) {
      switch (file.type) {
        case 'pdf':
          previewArea.innerHTML = `<p>Aperçu PDF: ${file.name}</p><p class="text-muted">Le fichier PDF ne peut pas être affiché en prévisualisation</p>`;
          break;
        case 'docx':
          previewArea.innerHTML = `<p>Document Word: ${file.name}</p><p class="text-muted">Format: Microsoft Word</p>`;
          break;
        case 'xlsx':
          previewArea.innerHTML = `<p>Feuille de calcul: ${file.name}</p><p class="text-muted">Format: Microsoft Excel</p>`;
          break;
        case 'pptx':
          previewArea.innerHTML = `<p>Présentation: ${file.name}</p><p class="text-muted">Format: Microsoft PowerPoint</p>`;
          break;
        default:
          previewArea.innerHTML = `<p>Fichier: ${file.name}</p><p class="text-muted">Type: ${file.type}</p>`;
      }
    }

    if (modal) {
      modal.style.display = 'block';
    }
  }

  // Download file
  downloadFile(file: FileItem): void {
    this.spinner.show();
    // this.fileService.downloadFile(file.id).subscribe({
    //   next: (blob) => {
    //     const url = window.URL.createObjectURL(blob);
    //     const link = document.createElement('a');
    //     link.href = url;
    //     link.download = file.name || 'file';
    //     link.click();
    //     this.spinner.hide();
    //   },
    //   error: (error) => {
    //     this.spinner.hide();
    //     this.modalService.openWarning('Erreur', 'Impossible de télécharger le fichier');
    //   }
    // });

    // Demo download
    setTimeout(() => {
      this.spinner.hide();
      this.modalService.openSuccessModal(`Téléchargement de ${file.name} en cours...`);
    }, 1500);
  }

  // Delete file
  deleteFile(file: FileItem): void {
    if (!confirm(`Êtes-vous sûr de vouloir supprimer ${file.name}?`)) {
      return;
    }

    this.spinner.show();
    // this.fileService.delete(file.id).subscribe({
    //   next: () => {
    //     this.files = this.files.filter(f => f.id !== file.id);
    //     this.filteredFiles = this.filteredFiles.filter(f => f.id !== file.id);
    //     this.spinner.hide();
    //     this.modalService.openSuccessModal('Fichier supprimé avec succès!');
    //   },
    //   error: (error) => {
    //     this.spinner.hide();
    //     this.modalService.openWarning('Erreur', 'Impossible de supprimer le fichier');
    //   }
    // });

    // Demo delete
    this.files = this.files.filter(f => f.id !== file.id);
    this.filteredFiles = this.filteredFiles.filter(f => f.id !== file.id);
    this.spinner.hide();
    this.modalService.openSuccessModal('Fichier supprimé avec succès!');
  }

  // Create new folder
  createFolder(): void {
    const folderName = prompt('Entrez le nom du dossier:');
    if (!folderName) return;

    this.spinner.show();
    // this.fileService.createFolder(folderName).subscribe({
    //   next: (folder) => {
    //     this.files.unshift(folder);
    //     this.filteredFiles = [...this.files];
    //     this.spinner.hide();
    //     this.modalService.openSuccessModal('Dossier créé avec succès!');
    //   },
    //   error: (error) => {
    //     this.spinner.hide();
    //     this.modalService.openWarning('Erreur', 'Impossible de créer le dossier');
    //   }
    // });

    // Demo create folder
    const newFolder: FileItem = {
      id: Math.max(...this.files.map(f => f.id || 0)) + 1,
      name: folderName,
      type: 'folder',
      size: 0,
      sizeFormatted: '0 B',
      modifiedDate: new Date(),
      modifiedDateFormatted: 'À l\'instant',
      icon: 'fa-folder'
    };

    this.files.unshift(newFolder);
    this.filteredFiles = [...this.files];
    this.spinner.hide();
    this.modalService.openSuccessModal('Dossier créé avec succès!');
  }

  // Rename file
  renameFile(file: FileItem): void {
    const newName = prompt(`Nouveau nom pour ${file.name}:`);
    if (!newName || newName === file.name) return;

    this.spinner.show();
    // this.fileService.rename(file.id, newName).subscribe({
    //   next: () => {
    //     file.name = newName;
    //     this.spinner.hide();
    //     this.modalService.openSuccessModal('Fichier renommé avec succès!');
    //   },
    //   error: (error) => {
    //     this.spinner.hide();
    //     this.modalService.openWarning('Erreur', 'Impossible de renommer le fichier');
    //   }
    // });

    // Demo rename
    file.name = newName;
    this.spinner.hide();
    this.modalService.openSuccessModal('Fichier renommé avec succès!');
  }

  // Utility methods
  private getFileType(filename: string): string {
    const ext = filename.split('.').pop()?.toLowerCase();
    return ext || 'file';
  }

  private getFileIcon(filename: string): string {
    const type = this.getFileType(filename);
    const iconMap: { [key: string]: string } = {
      'pdf': 'fa-file-pdf',
      'docx': 'fa-file-word',
      'doc': 'fa-file-word',
      'xlsx': 'fa-file-excel',
      'xls': 'fa-file-excel',
      'pptx': 'fa-file-powerpoint',
      'ppt': 'fa-file-powerpoint',
      'txt': 'fa-file-alt',
      'zip': 'fa-file-archive',
      'jpg': 'fa-file-image',
      'png': 'fa-file-image',
      'gif': 'fa-file-image'
    };
    return iconMap[type] || 'fa-file';
  }

  public formatFileSize(bytes: number): string {
    if (bytes === 0) return '0 B';
    const k = 1024;
    const sizes = ['B', 'Ko', 'Mo', 'Go', 'To'];
    const i = Math.floor(Math.log(bytes) / Math.log(k));
    return Math.round((bytes / Math.pow(k, i)) * 100) / 100 + ' ' + sizes[i];
  }

  private generateId(): string {
    return Date.now().toString(36) + Math.random().toString(36).substr(2);
  }

  closePreviewModal(): void {
    const modal = document.getElementById('previewModal') as HTMLElement;
    if (modal) {
      modal.style.display = 'none';
    }
  }

  getSizeUnit(item: QuickAccessItem): string {
    return item.totalSize >= 1 ? 'To' : 'Go';
  }

  getSizeValue(item: QuickAccessItem): number {
    return item.totalSize >= 1 ? item.totalSize : item.totalSize * 1000;
  }

  public hasUploadInProgress(): boolean {
  if (!this.uploads || this.uploads.length === 0) {
    return false;
  }
  return this.uploads.some(u => u.progress > 0);
}

}




