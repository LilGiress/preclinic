import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { NgxSpinnerService } from 'ngx-spinner';
import { ModalService } from '../../shared/service/modal.service';
import { Appointment } from '../../models/appointement/appointment';
import { CommonModule, NgFor, NgIf } from '@angular/common';
declare var $: any;
@Component({
    selector: 'app-appointments',
    imports: [CommonModule,ReactiveFormsModule,NgFor,NgIf],
    templateUrl: './appointments.component.html',
    styleUrl: './appointments.component.css'
})
export class AppointmentsComponent implements OnInit {

appointmentForm!: FormGroup;
  updateForm!: FormGroup;
  appointments: Appointment[] = [];
  selectedAppointment: Appointment | null = null;
  submitted = false;
  updateSubmitted = false;
  message = '';
  patients: any[] = [];
  doctors: any[] = [];
  departments: any[] = [];

  constructor(
    private fb: FormBuilder,
    private spinner: NgxSpinnerService,
    private modalService: ModalService,
    // private appointmentService: AppointmentService,
    // private patientService: PatientService,
    // private doctorService: DoctorService,
    // private departmentService: DepartmentService
  ) {
    this.initializeForms();
  }

  ngOnInit(): void {
    this.loadAppointments();
    this.loadPatients();
    this.loadDoctors();
    this.loadDepartments();
  }

  // Initialize Reactive Forms
  initializeForms(): void {
    this.appointmentForm = this.fb.group({
      patientId: ['', Validators.required],
      departmentId: ['', Validators.required],
      doctorId: ['', Validators.required],
      appointmentDate: ['', [Validators.required, this.futureDateValidator.bind(this)]],
      appointmentTime: ['', Validators.required],
      patientEmail: ['', [Validators.required, Validators.email]],
      patientPhone: ['', [Validators.required, Validators.pattern(/^\d{8,}$/)]],
      message: [''],
      status: ['Active', Validators.required]
    });

    this.updateForm = this.fb.group({
      id: [''],
      patientId: ['', Validators.required],
      departmentId: ['', Validators.required],
      doctorId: ['', Validators.required],
      appointmentDate: ['', Validators.required],
      appointmentTime: ['', Validators.required],
      patientEmail: ['', [Validators.required, Validators.email]],
      patientPhone: ['', [Validators.required, Validators.pattern(/^\d{8,}$/)]],
      message: [''],
      status: ['Active', Validators.required]
    });
  }

  // Custom validator to prevent past dates
  futureDateValidator(control: any): any {
    if (!control.value) {
      return null;
    }
    const inputDate = new Date(control.value);
    const today = new Date();
    today.setHours(0, 0, 0, 0);

    if (inputDate < today) {
      return { pastDate: true };
    }
    return null;
  }

  // LOAD - Fetch all appointments
  loadAppointments(): void {
    this.spinner.show();
    // this.appointmentService.getAll().subscribe({
    //   next: (data) => {
    //     this.appointments = data;
    //     this.spinner.hide();
    //   },
    //   error: (error) => {
    //     this.spinner.hide();
    //     console.error('Error loading appointments:', error);
    //     this.modalService.openWarning('Error', 'Failed to load appointments');
    //   }
    // });
    
    // Demo data
    this.appointments = [
      {
        id: 1,
        appointmentId: 'APT0001',
        patientName: 'Denise Stevens',
        age: 35,
        doctorName: 'Henry Daniels',
        departmentName: 'Cardiology',
        appointmentDate: new Date('2024-12-30'),
        appointmentTime: '10:00am - 11:00am',
        status: 'Inactive'
      },
      {
        id: 2,
        appointmentId: 'APT0002',
        patientName: 'Denise Stevens',
        age: 35,
        doctorName: 'Henry Daniels',
        departmentName: 'Cardiology',
        appointmentDate: new Date('2024-12-30'),
        appointmentTime: '10:00am - 11:00am',
        status: 'Active'
      }
    ];
    this.spinner.hide();
  }

  // Load patients
  loadPatients(): void {
    // this.patientService.getAll().subscribe({
    //   next: (data) => {
    //     this.patients = data;
    //   },
    //   error: (error) => console.error('Error loading patients:', error)
    // });
  }

  // Load doctors
  loadDoctors(): void {
    // this.doctorService.getAll().subscribe({
    //   next: (data) => {
    //     this.doctors = data;
    //   },
    //   error: (error) => console.error('Error loading doctors:', error)
    // });
  }

  // Load departments
  loadDepartments(): void {
    // this.departmentService.getAll().subscribe({
    //   next: (data) => {
    //     this.departments = data;
    //   },
    //   error: (error) => console.error('Error loading departments:', error)
    // });
  }

  // CREATE - Add new appointment
  createAppointment(): void {
    this.submitted = true;

    if (this.appointmentForm.invalid) {
      return;
    }

    this.spinner.show();
    const newAppointment: Appointment = {
      patientId: this.appointmentForm.value.patientId,
      departmentId: this.appointmentForm.value.departmentId,
      doctorId: this.appointmentForm.value.doctorId,
      appointmentDate: new Date(this.appointmentForm.value.appointmentDate),
      appointmentTime: this.appointmentForm.value.appointmentTime,
      patientEmail: this.appointmentForm.value.patientEmail,
      patientPhone: this.appointmentForm.value.patientPhone,
      message: this.appointmentForm.value.message,
      status: this.appointmentForm.value.status
    };

    // this.appointmentService.create(newAppointment).subscribe({
    //   next: (response) => {
    //     this.appointments.push(response);
    //     this.resetCreateForm();
    //     this.closeModal('exampleModal');
    //     this.spinner.hide();
    //     this.modalService.openSuccessModal('Appointment created successfully!');
    //   },
    //   error: (error) => {
    //     this.spinner.hide();
    //     this.message = error.error?.message || 'Error creating appointment';
    //     this.modalService.openWarning('Error', this.message);
    //   }
    // });

    // Demo
    this.appointments.push(newAppointment);
    this.resetCreateForm();
    this.closeModal('exampleModal');
    this.spinner.hide();
    this.modalService.openSuccessModal('Appointment created successfully!');
  }


// UPDATE - Edit existing appointment
  openUpdateModal(appointment: Appointment): void {
    this.selectedAppointment = appointment;
    this.updateForm.patchValue({
      id: appointment.id,
      patientId: appointment.patientId,
      departmentId: appointment.departmentId,
      doctorId: appointment.doctorId,
      appointmentDate: this.formatDateForInput(appointment.appointmentDate),
      appointmentTime: appointment.appointmentTime,
      patientEmail: appointment.patientEmail,
      patientPhone: appointment.patientPhone,
      message: appointment.message,
      status: appointment.status
    });
    this.updateSubmitted = false;
    $('#updateAppointmentModal').modal('show');
  }

  updateAppointment(): void {
    this.updateSubmitted = true;

    if (this.updateForm.invalid) {
      return;
    }

    this.spinner.show();
    const updatedAppointment: Appointment = {
      id: this.updateForm.value.id,
      patientId: this.updateForm.value.patientId,
      departmentId: this.updateForm.value.departmentId,
      doctorId: this.updateForm.value.doctorId,
      appointmentDate: new Date(this.updateForm.value.appointmentDate),
      appointmentTime: this.updateForm.value.appointmentTime,
      patientEmail: this.updateForm.value.patientEmail,
      patientPhone: this.updateForm.value.patientPhone,
      message: this.updateForm.value.message,
      status: this.updateForm.value.status
    };

    // this.appointmentService.update(updatedAppointment.id!, updatedAppointment).subscribe({
    //   next: () => {
    //     const index = this.appointments.findIndex(a => a.id === updatedAppointment.id);
    //     if (index > -1) {
    //       this.appointments[index] = updatedAppointment;
    //     }
    //     this.resetUpdateForm();
    //     this.closeModal('updateAppointmentModal');
    //     this.spinner.hide();
    //     this.modalService.openSuccessModal('Appointment updated successfully!');
    //   },
    //   error: (error) => {
    //     this.spinner.hide();
    //     this.message = error.error?.message || 'Error updating appointment';
    //     this.modalService.openWarning('Error', this.message);
    //   }
    // });

    // Demo
    const index = this.appointments.findIndex(a => a.id === updatedAppointment.id);
    if (index > -1) {
      this.appointments[index] = updatedAppointment;
    }
    this.resetUpdateForm();
    this.closeModal('updateAppointmentModal');
    this.spinner.hide();
    this.modalService.openSuccessModal('Appointment updated successfully!');
  }

  // DELETE - Remove appointment
  deleteAppointment(id: number | undefined): void {
    if (!id || !confirm('Are you sure you want to delete this appointment?')) {
      return;
    }

    this.spinner.show();
    // this.appointmentService.delete(id).subscribe({
    //   next: () => {
    //     this.appointments = this.appointments.filter(a => a.id !== id);
    //     this.closeModal('deleteAppointmentModal');
    //     this.spinner.hide();
    //     this.modalService.openSuccessModal('Appointment deleted successfully!');
    //   },
    //   error: (error) => {
    //     this.spinner.hide();
    //     this.message = error.error?.message || 'Error deleting appointment';
    //     this.modalService.openWarning('Error', this.message);
    //   }
    // });

    // Demo
    this.appointments = this.appointments.filter(a => a.id !== id);
    this.closeModal('deleteAppointmentModal');
    this.spinner.hide();
    this.modalService.openSuccessModal('Appointment deleted successfully!');
  }


  // Utility methods
  resetCreateForm(): void {
    this.appointmentForm.reset({ status: 'Active' });
    this.submitted = false;
  }

    resetUpdateForm(): void {
    this.updateForm.reset();
    this.updateSubmitted = false;
  }

  closeModal(modalId: string): void {
    $(`#${modalId}`).modal('hide');
  }

  openCreateModal(): void {
    this.resetCreateForm();
    $('#exampleModal').modal('show');
  }

  openDeleteModal(appointment: Appointment): void {
    this.selectedAppointment = appointment;
    $('#deleteAppointmentModal').modal('show');
  }

      formatDateForInput(date: any): string {
    if (!date) return '';
    const d = new Date(date);
    return d.toISOString().split('T')[0];
  }

  getFormattedDate(date: any): string {
    if (!date) return '';
    return new Date(date).toLocaleDateString('en-US', {
      year: 'numeric',
      month: 'short',
      day: 'numeric'
    });
  }

  // Getters for form validation
  get f() {
    return this.appointmentForm.controls;
  }

  get uf() {
    return this.updateForm.controls;
  }

}
