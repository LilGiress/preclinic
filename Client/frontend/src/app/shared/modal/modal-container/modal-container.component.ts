import { Component, OnInit } from '@angular/core';
import { ModalService } from '../../service/modal.service';
import { SuccessModalComponent } from "../success-modal/success-modal.component";
import { DeleteModalComponent } from "../delete-modal/delete-modal.component";
import { WarningModalComponent } from "../warning-modal/warning-modal.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-modal-container',
  standalone: true,
  imports: [CommonModule,SuccessModalComponent, DeleteModalComponent, WarningModalComponent],
  templateUrl: './modal-container.component.html',
  styleUrl: './modal-container.component.css'
})
export class ModalContainerComponent implements OnInit {
  modalData: any = null;
  successModalData: any = null;
  message: string = '';
  title: string = '';
  showWarningModal: boolean = false;

  constructor(public modalService: ModalService) {}

  ngOnInit() {
    // S'abonner au service pour écouter l'état des modals
    this.modalService.modalState$.subscribe(data => {
      this.modalData = data;
    });
    this.modalService.successModalState$.subscribe(data => {
      this.successModalData = data;
    });

     // S'abonner à l'état du modal via le service
     this.modalService.warning$.subscribe(data => {
      if (data) {
        this.message = data.message;
        this.title = data.title;
        this.showWarningModal = true;
      } else {
        this.showWarningModal = false;
      }
    });
  }

  // Méthodes pour fermer les modals
  closeDeleteModal() {
    this.modalService.closeModal();
  }

  closeSuccessModal() {
    this.modalService.closeSuccessModal();
  }
   // Méthode pour fermer le modal
   closeWarningModal() {
    this.modalService.closeWarning();
  }

}
