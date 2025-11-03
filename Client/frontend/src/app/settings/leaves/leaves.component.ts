import {Component, OnInit} from '@angular/core';
import {LeaveService} from "../../services/leave.service";
import {ModalService} from "../../shared/service/modal.service";
import {NgxSpinnerService} from "ngx-spinner";

@Component({
    selector: 'app-leaves',
    imports: [],
    templateUrl: './leaves.component.html',
    styleUrl: './leaves.component.css'
})
export class LeavesComponent implements OnInit {


    ngOnInit(): void {

    }

    constructor(
      private leaves: LeaveService,
      private readonly modalService:ModalService,
      private readonly spinner:NgxSpinnerService,
    ) {

    }

}
