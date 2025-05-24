import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  private baseUrl = 'http://localhost:8080/api/files/profile';

  constructor(private http: HttpClient) {}

  uploadProfilePhoto(file: File, userId: number, role: string) {
    const formData = new FormData();
    formData.append("file", file);
    return this.http.post(`${this.baseUrl}/upload/${userId}/${role}`, formData);
  }

  getProfilePhotoUrl(userId: number, role: string): string {
    return `${this.baseUrl}/${role}/${userId}.jpg`;
  }

  uploadDoctorFile(file: File, doctorId: number, category: string) {
    const formData = new FormData();
    formData.append("file", file);
    return this.http.post(`${this.baseUrl}/upload/doctor/${doctorId}/${category}`, formData);
  }

  listDoctorFiles(doctorId: number, category: string) {
    return this.http.get<string[]>(`${this.baseUrl}/list/doctor/${doctorId}/${category}`);
  }

  downloadFile(fileId: number) {
    return this.http.get(`${this.baseUrl}/download/${fileId}`, { responseType: 'blob' });
  }

  createFolder(userId: number, role: string, folderName: string) {
    return this.http.post(`${this.baseUrl}/create/${userId}/${role}`, { folderName });
  }

  listFolders(userId: number, role: string) {
    return this.http.get<string[]>(`${this.baseUrl}/list/${userId}/${role}`);
  }

  deleteFolder(userId: number, role: string, folderName: string) {
    return this.http.delete(`${this.baseUrl}/delete/${userId}/${role}?folderName=${folderName}`);
  }
}
