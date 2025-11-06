import { CommonModule } from '@angular/common';
import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ModalService} from "../../service/modal.service";
import {Observable} from "rxjs";

@Component({
    selector: 'app-delete-modal',
    imports: [CommonModule],
    templateUrl: './delete-modal.component.html',
    styleUrl: './delete-modal.component.css'
})
export class DeleteModalComponent implements OnInit {
  @Input() itemName: string = '';
  @Input() message: string = '';
  @Output() confirmDelete = new EventEmitter<void>();
  @Output() cancelDelete = new EventEmitter<void>();
  state$!: Observable<any>;
  constructor(private readonly modalService: ModalService) { }
  image='assets/preclinic/img/sent.png';

  ngOnInit() {
    this.state$ = this.modalService.deleteModalState$;
  }
  confirm() {
    this.modalService.confirmDelete();
    this.confirmDelete.emit();
  }

  closeModal() {
    this.modalService.cancelDelete();
    this.cancelDelete.emit();
  }

}
