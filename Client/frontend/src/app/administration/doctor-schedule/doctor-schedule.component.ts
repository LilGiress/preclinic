import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ModalService } from '../../services/modal.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { DoctorSchedule } from '../../models/doctors/doctorSchedule';
import { CommonModule } from '@angular/common';
declare var $: any;
@Component({
    selector: 'app-doctor-schedule',
    imports: [CommonModule,ReactiveFormsModule,FormsModule],
    templateUrl: './doctor-schedule.component.html',
    styleUrl: './doctor-schedule.component.css'
})
export class DoctorScheduleComponent implements OnInit {
    scheduleForm!: FormGroup;
  updateForm!: FormGroup;
  schedules: DoctorSchedule[] = [];
  selectedSchedule: DoctorSchedule | null = null;
  submitted = false;
  updateSubmitted = false;
  message = '';
  doctors: any[] = [];
  departments: any[] = [];
  
  days = [
    'Sunday',
    'Monday',
    'Tuesday',
    'Wednesday',
    'Thursday',
    'Friday',
    'Saturday'
  ];

  constructor(
    private fb: FormBuilder,
    private spinner: NgxSpinnerService,
    private modalService: ModalService,
    // private scheduleService: DoctorScheduleService,
    // private doctorService: DoctorService,
    // private departmentService: DepartmentService
  ) {
    this.initializeForms();
  }

  ngOnInit(): void {
    this.loadSchedules();
    this.loadDoctors();
    this.loadDepartments();
  }

  // Initialize Reactive Forms
  initializeForms(): void {
    this.scheduleForm = this.fb.group({
      doctorId: ['', Validators.required],
      departmentId: ['', Validators.required],
      availableDays: [[], [Validators.required, this.daysValidator.bind(this)]],
      startTime: ['', Validators.required],
      endTime: ['', Validators.required],
      message: [''],
      status: ['Active', Validators.required]
    }, { validators: this.timeValidator });

    this.updateForm = this.fb.group({
      id: [''],
      doctorId: ['', Validators.required],
      departmentId: ['', Validators.required],
      availableDays: [[], [Validators.required, this.daysValidator.bind(this)]],
      startTime: ['', Validators.required],
      endTime: ['', Validators.required],
      message: [''],
      status: ['Active', Validators.required]
    }, { validators: this.timeValidator });
  }

  // Custom validator for days selection
  daysValidator(control: any): any {
    if (!control.value || control.value.length === 0) {
      return { daysRequired: true };
    }
    return null;
  }

  // Custom validator for time range
  timeValidator(group: FormGroup): { [key: string]: any } | null {
    const startTime = group.get('startTime')?.value;
    const endTime = group.get('endTime')?.value;

    if (startTime && endTime) {
      const start = this.timeToMinutes(startTime);
      const end = this.timeToMinutes(endTime);

      if (start >= end) {
        return { invalidTimeRange: true };
      }
    }
    return null;
  }

  // Convert time string to minutes for comparison
  private timeToMinutes(time: string): number {
    const [hours, minutes] = time.split(':').map(Number);
    return hours * 60 + minutes;
  }

  // LOAD - Fetch all schedules
  loadSchedules(): void {
    this.spinner.show();
    // this.scheduleService.getAll().subscribe({
    //   next: (data) => {
    //     this.schedules = data;
    //     this.spinner.hide();
    //   },
    //   error: (error) => {
    //     this.spinner.hide();
    //     console.error('Error loading schedules:', error);
    //     this.modalService.openWarning('Error', 'Failed to load schedules');
    //   }
    // });

    // Demo data
    this.schedules = [
      {
        id: 1,
        doctorName: 'Henry Daniels',
        departmentName: 'Cardiology',
        availableDays: ['Sunday', 'Monday', 'Tuesday'],
        startTime: '10:00',
        endTime: '19:00',
        status: 'Active'
      },
      {
        id: 2,
        doctorName: 'Marie Wells',
        departmentName: 'Neurology',
        availableDays: ['Monday', 'Wednesday', 'Friday'],
        startTime: '09:00',
        endTime: '17:00',
        status: 'Active'
      }
    ];
    this.spinner.hide();
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

  // CREATE - Add new schedule
  createSchedule(): void {
    this.submitted = true;

    if (this.scheduleForm.invalid) {
      if (this.scheduleForm.hasError('invalidTimeRange')) {
        this.modalService.openWarning('Error', 'End time must be after start time');
      }
      if (this.scheduleForm.hasError('daysRequired')) {
        this.modalService.openWarning('Error', 'Please select at least one day');
      }
      return;
    }

    this.spinner.show();
    const newSchedule: DoctorSchedule = {
      doctorId: this.scheduleForm.value.doctorId,
      departmentId: this.scheduleForm.value.departmentId,
      availableDays: this.scheduleForm.value.availableDays,
      startTime: this.scheduleForm.value.startTime,
      endTime: this.scheduleForm.value.endTime,
      message: this.scheduleForm.value.message,
      status: this.scheduleForm.value.status
    };

    // this.scheduleService.create(newSchedule).subscribe({
    //   next: (response) => {
    //     this.schedules.push(response);
    //     this.resetCreateForm();
    //     this.closeModal('exampleModal');
    //     this.spinner.hide();
    //     this.modalService.openSuccessModal('Schedule created successfully!');
    //   },
    //   error: (error) => {
    //     this.spinner.hide();
    //     this.message = error.error?.message || 'Error creating schedule';
    //     this.modalService.openWarning('Error', this.message);
    //   }
    // });

    // Demo
    newSchedule.id = Math.max(...this.schedules.map(s => s.id || 0)) + 1;
    this.schedules.push(newSchedule);
    this.resetCreateForm();
    this.closeModal('exampleModal');
    this.spinner.hide();
    this.modalService.openSuccessModal('Schedule created successfully!');
  }

  // UPDATE - Edit existing schedule
  openUpdateModal(schedule: DoctorSchedule): void {
    this.selectedSchedule = schedule;
    this.updateForm.patchValue({
      id: schedule.id,
      doctorId: schedule.doctorId,
      departmentId: schedule.departmentId,
      availableDays: schedule.availableDays,
      startTime: schedule.startTime,
      endTime: schedule.endTime,
      message: schedule.message,
      status: schedule.status
    });
    this.updateSubmitted = false;
    $('#updateScheduleModal').modal('show');
  }

  updateSchedule(): void {
    this.updateSubmitted = true;

    if (this.updateForm.invalid) {
      if (this.updateForm.hasError('invalidTimeRange')) {
        this.modalService.openWarning('Error', 'End time must be after start time');
      }
      return;
    }

    this.spinner.show();
    const updatedSchedule: DoctorSchedule = {
      id: this.updateForm.value.id,
      doctorId: this.updateForm.value.doctorId,
      departmentId: this.updateForm.value.departmentId,
      availableDays: this.updateForm.value.availableDays,
      startTime: this.updateForm.value.startTime,
      endTime: this.updateForm.value.endTime,
      message: this.updateForm.value.message,
      status: this.updateForm.value.status
    };

    // this.scheduleService.update(updatedSchedule.id!, updatedSchedule).subscribe({
    //   next: () => {
    //     const index = this.schedules.findIndex(s => s.id === updatedSchedule.id);
    //     if (index > -1) {
    //       this.schedules[index] = updatedSchedule;
    //     }
    //     this.resetUpdateForm();
    //     this.closeModal('updateScheduleModal');
    //     this.spinner.hide();
    //     this.modalService.openSuccessModal('Schedule updated successfully!');
    //   },
    //   error: (error) => {
    //     this.spinner.hide();
    //     this.message = error.error?.message || 'Error updating schedule';
    //     this.modalService.openWarning('Error', this.message);
    //   }
    // });

    // Demo
    const index = this.schedules.findIndex(s => s.id === updatedSchedule.id);
    if (index > -1) {
      this.schedules[index] = updatedSchedule;
    }
    this.resetUpdateForm();
    this.closeModal('updateScheduleModal');
    this.spinner.hide();
    this.modalService.openSuccessModal('Schedule updated successfully!');
  }

  // DELETE - Remove schedule
  deleteSchedule(id: number | undefined): void {
    if (!id || !confirm('Are you sure you want to delete this schedule?')) {
      return;
    }

    this.spinner.show();
    // this.scheduleService.delete(id).subscribe({
    //   next: () => {
    //     this.schedules = this.schedules.filter(s => s.id !== id);
    //     this.closeModal('deleteScheduleModal');
    //     this.spinner.hide();
    //     this.modalService.openSuccessModal('Schedule deleted successfully!');
    //   },
    //   error: (error) => {
    //     this.spinner.hide();
    //     this.message = error.error?.message || 'Error deleting schedule';
    //     this.modalService.openWarning('Error', this.message);
    //   }
    // });

    // Demo
    this.schedules = this.schedules.filter(s => s.id !== id);
    this.closeModal('deleteScheduleModal');
    this.spinner.hide();
    this.modalService.openSuccessModal('Schedule deleted successfully!');
  }

  // Utility methods
  resetCreateForm(): void {
    this.scheduleForm.reset({ status: 'Active', availableDays: [] });
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

  openDeleteModal(schedule: DoctorSchedule): void {
    this.selectedSchedule = schedule;
    $('#deleteScheduleModal').modal('show');
  }

  formatTime(time: string): string {
    if (!time) return '';
    const [hours, minutes] = time.split(':');
    const hour = parseInt(hours, 10);
    const ampm = hour >= 12 ? 'PM' : 'AM';
    const displayHour = hour % 12 || 12;
    return `${displayHour}:${minutes} ${ampm}`;
  }

  getAvailableDaysDisplay(days: string[]): string {
    return days?.join(', ') || '';
  }

  // Getters for form validation
  get f() {
    return this.scheduleForm.controls;
  }

  get uf() {
    return this.updateForm.controls;
  }

  // Helper for day selection
  isDaySelected(day: string, formControl: string): boolean {
    const form = formControl === 'create' ? this.scheduleForm : this.updateForm;
    const selectedDays = form.get('availableDays')?.value || [];
    return selectedDays.includes(day);
  }

  toggleDay(day: string, formControl: string): void {
    const form = formControl === 'create' ? this.scheduleForm : this.updateForm;
    const control = form.get('availableDays');
    let selectedDays = control?.value || [];

    if (selectedDays.includes(day)) {
      selectedDays = selectedDays.filter((d: string) => d !== day);
    } else {
      selectedDays.push(day);
    }

    control?.setValue(selectedDays);
  }

}
