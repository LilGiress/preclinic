import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ModalService {

  constructor() { }
  private readonly modalSubject = new BehaviorSubject<any>(null);
  private readonly successModalSubject = new BehaviorSubject<any>(null);
  // BehaviorSubject pour contrôler l'état du modal
  private readonly warningSubject = new BehaviorSubject<{ message: string; title: string } | null>(null);
   public successCallback: (() => void) | null = null;


  modalState$ = this.modalSubject.asObservable();
  successModalState$ = this.successModalSubject.asObservable();
   // Observable pour les composants qui veulent s'abonner aux changements du modal
   warning$ = this.warningSubject.asObservable();

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
}
