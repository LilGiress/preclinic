import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { NgxSpinnerService } from 'ngx-spinner';
import { ModalService } from '../../shared/service/modal.service';
import { Doctor } from '../../models/doctors/doctor';
declare var $: any;
@Component({
    selector: 'app-doctors',
    imports: [CommonModule,ReactiveFormsModule,FormsModule],
    templateUrl: './doctors.component.html',
    styleUrl: './doctors.component.css'
})
export class DoctorsComponent {


    doctorForm!: FormGroup;
  updateForm!: FormGroup;
  doctors: Doctor[] = [];
  selectedDoctor: Doctor | null = null;
  submitted = false;
  updateSubmitted = false;
  message = '';
  avatarPreview: string | null = null;

  genders = ['Male', 'Female'];
  countries = ['USA', 'United Kingdom', 'Canada', 'Australia'];
  states = ['California', 'Alaska', 'Alabama', 'New York', 'Texas'];
  specializations = [
    'Gynecologist',
    'Psychiatrist',
    'Cardiologist',
    'Urologist',
    'Ophthalmologist',
    'Dentist',
    'Oncologist',
    'Neurologist',
    'General Surgery',
    'Radiologist',
    'Pediatrics',
    'Physical Therapist'
  ];

  constructor(
    private fb: FormBuilder,
    private spinner: NgxSpinnerService,
    private modalService: ModalService
    // private doctorService: DoctorService
  ) {
    this.initializeForms();
  }

  ngOnInit(): void {
    this.loadDoctors();
  }

  // Initialize Reactive Forms
  initializeForms(): void {
    this.doctorForm = this.fb.group({
      firstName: ['', [Validators.required, Validators.minLength(2)]],
      lastName: ['', Validators.minLength(2)],
      username: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
      dateOfBirth: [''],
      gender: ['', Validators.required],
      phone: ['', [Validators.required, Validators.pattern(/^\d{8,}$/)]],
      address: [''],
      country: ['', Validators.required],
      city: [''],
      state: [''],
      postalCode: [''],
      avatar: [''],
      biography: [''],
      specialization: ['', Validators.required],
      status: ['Active', Validators.required]
    }, { validators: this.passwordMatchValidator });

    this.updateForm = this.fb.group({
      id: [''],
      firstName: ['', [Validators.required, Validators.minLength(2)]],
      lastName: ['', Validators.minLength(2)],
      username: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      password: [''],
      confirmPassword: [''],
      dateOfBirth: [''],
      gender: ['', Validators.required],
      phone: ['', [Validators.required, Validators.pattern(/^\d{8,}$/)]],
      address: [''],
      country: ['', Validators.required],
      city: [''],
      state: [''],
      postalCode: [''],
      avatar: [''],
      biography: [''],
      specialization: ['', Validators.required],
      status: ['Active', Validators.required]
    }, { validators: this.updatePasswordValidator });
  }

  // Custom validator for password matching
  passwordMatchValidator(group: FormGroup): { [key: string]: any } | null {
    const password = group.get('password')?.value;
    const confirmPassword = group.get('confirmPassword')?.value;

    if (password && confirmPassword && password !== confirmPassword) {
      return { passwordMismatch: true };
    }
    return null;
  }

  // Custom validator for update password (password is optional)
  updatePasswordValidator(group: FormGroup): { [key: string]: any } | null {
    const password = group.get('password')?.value;
    const confirmPassword = group.get('confirmPassword')?.value;

    if (password || confirmPassword) {
      if (password !== confirmPassword) {
        return { passwordMismatch: true };
      }
    }
    return null;
  }

  // LOAD - Fetch all doctors
  loadDoctors(): void {
    this.spinner.show();
    // this.doctorService.getAll().subscribe({
    //   next: (data) => {
    //     this.doctors = data;
    //     this.spinner.hide();
    //   },
    //   error: (error) => {
    //     this.spinner.hide();
    //     console.error('Error loading doctors:', error);
    //     this.modalService.openWarning('Error', 'Failed to load doctors');
    //   }
    // });

    // Demo data
    this.doctors = [
      {
          id: 1,
          firstName: 'Cristina',
          lastName: 'Groves',
          specialization: 'Gynecologist',
          status: 'Active',
          phoneNumber: ''
      },
      {
          id: 2,
          firstName: 'Marie',
          lastName: 'Wells',
          specialization: 'Psychiatrist',
          status: 'Active',
          phoneNumber: ''
      },
      {
          id: 3,
          firstName: 'Henry',
          lastName: 'Daniels',
          specialization: 'Cardiologist',
          status: 'Active',
          phoneNumber: ''
      }
    ];
    this.spinner.hide();
  }

  // CREATE - Add new doctor
  createDoctor(): void {
    this.submitted = true;

    if (this.doctorForm.invalid) {
      if (this.doctorForm.hasError('passwordMismatch')) {
        this.modalService.openWarning('Error', 'Passwords do not match');
      }
      return;
    }

    this.spinner.show();
    const newDoctor: Doctor = {
        firstName: this.doctorForm.value.firstName,
        lastName: this.doctorForm.value.lastName,
        username: this.doctorForm.value.username,
        email: this.doctorForm.value.email,
        password: this.doctorForm.value.password,
        dateOfBirth: this.doctorForm.value.dateOfBirth,
        gender: this.doctorForm.value.gender,
        phone: this.doctorForm.value.phone,
        address: this.doctorForm.value.address,
        country: this.doctorForm.value.country,
        city: this.doctorForm.value.city,
        state: this.doctorForm.value.state,
        postalCode: this.doctorForm.value.postalCode,
        avatar: this.avatarPreview || 'assets/img/user.jpg',
        biography: this.doctorForm.value.biography,
        specialization: this.doctorForm.value.specialization,
        status: this.doctorForm.value.status,
        phoneNumber: ''
    };

    // this.doctorService.create(newDoctor).subscribe({
    //   next: (response) => {
    //     this.doctors.push(response);
    //     this.resetCreateForm();
    //     this.closeModal('exampleModal');
    //     this.spinner.hide();
    //     this.modalService.openSuccessModal('Doctor created successfully!');
    //   },
    //   error: (error) => {
    //     this.spinner.hide();
    //     this.message = error.error?.message || 'Error creating doctor';
    //     this.modalService.openWarning('Error', this.message);
    //   }
    // });

    // Demo
    newDoctor.id = Math.max(...this.doctors.map(d => d.id || 0)) + 1;
    this.doctors.push(newDoctor);
    this.resetCreateForm();
    this.closeModal('exampleModal');
    this.spinner.hide();
    this.modalService.openSuccessModal('Doctor created successfully!');
  }

  // UPDATE - Edit existing doctor
  openUpdateModal(doctor: Doctor): void {
    this.selectedDoctor = doctor;
    this.updateForm.patchValue({
      id: doctor.id,
      firstName: doctor.firstName,
      lastName: doctor.lastName,
      username: doctor.username,
      email: doctor.email,
      dateOfBirth: this.formatDateForInput(doctor.dateOfBirth),
      gender: doctor.gender,
      phone: doctor.phone,
      address: doctor.address,
      country: doctor.country,
      city: doctor.city,
      state: doctor.state,
      postalCode: doctor.postalCode,
      biography: doctor.biography,
      specialization: doctor.specialization,
      status: doctor.status
    });
    this.updateSubmitted = false;
    this.avatarPreview = doctor.avatar || null;
    $('#updatedoctorModal').modal('show');
  }

  updateDoctor(): void {
    this.updateSubmitted = true;

    if (this.updateForm.invalid) {
      if (this.updateForm.hasError('passwordMismatch')) {
        this.modalService.openWarning('Error', 'Passwords do not match');
      }
      return;
    }

    this.spinner.show();
    const updatedDoctor: Doctor = {
        id: this.updateForm.value.id,
        firstName: this.updateForm.value.firstName,
        lastName: this.updateForm.value.lastName,
        username: this.updateForm.value.username,
        email: this.updateForm.value.email,
        password: this.updateForm.value.password || undefined,
        dateOfBirth: this.updateForm.value.dateOfBirth,
        gender: this.updateForm.value.gender,
        phone: this.updateForm.value.phone,
        address: this.updateForm.value.address,
        country: this.updateForm.value.country,
        city: this.updateForm.value.city,
        state: this.updateForm.value.state,
        postalCode: this.updateForm.value.postalCode,
        avatar: this.avatarPreview || this.selectedDoctor?.avatar,
        biography: this.updateForm.value.biography,
        specialization: this.updateForm.value.specialization,
        status: this.updateForm.value.status,
        phoneNumber: ''
    };

    // this.doctorService.update(updatedDoctor.id!, updatedDoctor).subscribe({
    //   next: () => {
    //     const index = this.doctors.findIndex(d => d.id === updatedDoctor.id);
    //     if (index > -1) {
    //       this.doctors[index] = updatedDoctor;
    //     }
    //     this.resetUpdateForm();
    //     this.closeModal('updatedoctorModal');
    //     this.spinner.hide();
    //     this.modalService.openSuccessModal('Doctor updated successfully!');
    //   },
    //   error: (error) => {
    //     this.spinner.hide();
    //     this.message = error.error?.message || 'Error updating doctor';
    //     this.modalService.openWarning('Error', this.message);
    //   }
    // });

    // Demo
    const index = this.doctors.findIndex(d => d.id === updatedDoctor.id);
    if (index > -1) {
      this.doctors[index] = updatedDoctor;
    }
    this.resetUpdateForm();
    this.closeModal('updatedoctorModal');
    this.spinner.hide();
    this.modalService.openSuccessModal('Doctor updated successfully!');
  }

  // DELETE - Remove doctor
  deleteDoctor(id: number | undefined): void {
    if (!id || !confirm('Are you sure you want to delete this doctor?')) {
      return;
    }

    this.spinner.show();
    // this.doctorService.delete(id).subscribe({
    //   next: () => {
    //     this.doctors = this.doctors.filter(d => d.id !== id);
    //     this.closeModal('delete_doctor');
    //     this.spinner.hide();
    //     this.modalService.openSuccessModal('Doctor deleted successfully!');
    //   },
    //   error: (error) => {
    //     this.spinner.hide();
    //     this.message = error.error?.message || 'Error deleting doctor';
    //     this.modalService.openWarning('Error', this.message);
    //   }
    // });

    // Demo
    this.doctors = this.doctors.filter(d => d.id !== id);
    this.closeModal('delete_doctor');
    this.spinner.hide();
    this.modalService.openSuccessModal('Doctor deleted successfully!');
  }

  // File upload handler
  onAvatarSelected(event: any): void {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.avatarPreview = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  }

  // Utility methods
  resetCreateForm(): void {
    this.doctorForm.reset({ status: 'Active', gender: '', country: '', specialization: '' });
    this.submitted = false;
    this.avatarPreview = null;
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

  openDeleteModal(doctor: Doctor): void {
    this.selectedDoctor = doctor;
    $('#delete_doctor').modal('show');
  }

    formatDateForInput(date: any): string {
    if (!date) return '';
    const d = new Date(date);
    return d.toISOString().split('T')[0];
  }

  getFullName(doctor: Doctor): string {
    return `${doctor.firstName} ${doctor.lastName}`.trim();
  }

  // Getters for form validation
  get f() {
    return this.doctorForm.controls;
  }

  get uf() {
    return this.updateForm.controls;
  }
}
