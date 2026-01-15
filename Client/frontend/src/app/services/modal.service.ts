import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import {callback} from "chart.js/helpers";
interface DeleteModalData {
  message: string;
  callback?: () => void;
}
@Injectable({
  providedIn: 'root'
})
export class ModalService {

  constructor() { }
  private readonly modalSubject = new BehaviorSubject<any>(null);
  private readonly successModalSubject = new BehaviorSubject<any>(null);
  // BehaviorSubject pour contrôler l'état du modal
  private readonly warningSubject = new BehaviorSubject<{ message: string; title: string } | null>(null);
  private readonly informationSubject = new BehaviorSubject<any>(null);
  private readonly deleteModalSubject = new BehaviorSubject<{ message: string } | null>(null);


   public successCallback: (() => void) | null = null;
   public informationCallback: (() => void) | null = null;
  public deleteCallback: (() => void) | null = null;


  modalState$ = this.modalSubject.asObservable();
  successModalState$ = this.successModalSubject.asObservable();
   // Observable pour les composants qui veulent s'abonner aux changements du modal
   warning$ = this.warningSubject.asObservable();
   infoModalState$ = this.warningSubject.asObservable();
  deleteModalState$ = this.deleteModalSubject.asObservable();

  openModal(itemName: string) {
    this.modalSubject.next({ itemName });
  }

  closeModal() {
    this.modalSubject.next(null);
  }

  openSuccessModal(message: string, callback?: () => void) {
     this.successCallback = callback ?? null;
    this.successModalSubject.next({ message });
  }

  closeSuccessModal() {
    this.successModalSubject.next(null);
  }

  // Ouvre le modal avec un message et un délai pour le fermer automatiquement
  openWarning(message: string, title: string = 'Attention', delay: number = 3000) {
    this.warningSubject.next({ message, title });

    // Ferme automatiquement après un délai
    setTimeout(() => {
      this.closeWarning();
    }, delay);
  }

  // Ferme le modal
  closeWarning() {
    this.warningSubject.next(null);
  }

  info(message: string, callback?: () => void) {
    this.informationCallback = callback ?? null;
    this.informationSubject.next({ message });

  }

  closeInfo() {
    this.warningSubject.next(null);
  }
  openDeleteModal(message:string,callback?:() => void){
      this.deleteCallback = callback?? null;
      this.deleteModalSubject.next({ message });
    }

  closeDeleteModal() {
    this.deleteModalSubject.next(null);
    this.deleteCallback = null;
  }

  cancelDelete() {
    this.deleteModalSubject.next(null);
    this.deleteCallback = null;
  }


  confirmDelete() {
    if (this.deleteCallback) {
      this.deleteCallback();   //
      this.deleteCallback = null;
    }
    this.deleteModalSubject.next(null);
  }


}
