import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ModalService } from '../../shared/service/modal.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { Patient } from '../../models/patient';
import { CommonModule } from '@angular/common';
declare var $: any;
@Component({
    selector: 'app-patients',
    imports: [CommonModule,ReactiveFormsModule,FormsModule],
    templateUrl: './patients.component.html',
    styleUrl: './patients.component.css'
})
export class PatientsComponent implements OnInit {
    patientForm!: FormGroup;
  updateForm!: FormGroup;
  patients: Patient[] = [];
  filteredPatients: Patient[] = [];
  selectedPatient: Patient | null = null;
  submitted = false;
  updateSubmitted = false;
  message = '';
  searchQuery = '';
  avatarPreview: string | null = null;

  bloodTypes = ['O+', 'O-', 'A+', 'A-', 'B+', 'B-', 'AB+', 'AB-'];
  genders = ['Male', 'Female', 'Other'];

  constructor(
    private fb: FormBuilder,
    private spinner: NgxSpinnerService,
    private modalService: ModalService
    // private patientService: PatientService
  ) {
    this.initializeForms();
  }

  ngOnInit(): void {
    this.loadPatients();
  }

  // Initialize Reactive Forms
  initializeForms(): void {
    this.patientForm = this.fb.group({
      firstName: ['', [Validators.required, Validators.minLength(2)]],
      lastName: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required, Validators.pattern(/^\d{8,}$/)]],
      dateOfBirth: ['', Validators.required],
      gender: ['', Validators.required],
      address: ['', Validators.required],
      city: ['', Validators.required],
      state: [''],
      postalCode: [''],
      country: ['', Validators.required],
      bloodType: [''],
      emergencyContact: [''],
      emergencyPhone: ['', Validators.pattern(/^\d{8,}$/)],
      medicalHistory: [''],
      status: ['Active', Validators.required]
    });

    this.updateForm = this.fb.group({
      id: [''],
      firstName: ['', [Validators.required, Validators.minLength(2)]],
      lastName: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required, Validators.pattern(/^\d{8,}$/)]],
      dateOfBirth: ['', Validators.required],
      gender: ['', Validators.required],
      address: ['', Validators.required],
      city: ['', Validators.required],
      state: [''],
      postalCode: [''],
      country: ['', Validators.required],
      bloodType: [''],
      emergencyContact: [''],
      emergencyPhone: ['', Validators.pattern(/^\d{8,}$/)],
      medicalHistory: [''],
      status: ['Active', Validators.required]
    });
  }

  // LOAD - Fetch all patients
  loadPatients(): void {
    this.spinner.show();
    // this.patientService.getAll().subscribe({
    //   next: (data) => {
    //     this.patients = data;
    //     this.filteredPatients = data;
    //     this.spinner.hide();
    //   },
    //   error: (error) => {
    //     this.spinner.hide();
    //     console.error('Error loading patients:', error);
    //     this.modalService.openWarning('Error', 'Failed to load patients');
    //   }
    // });

    // Demo data
    this.patients = [
      { id: 1, firstName: 'Jennifer', lastName: 'Robinson', age: 35, address: '1545 Dorsey Ln NE, Leland, NC, 28451', phone: '(207) 808 8863', email: 'jenniferrobinson@example.com', avatar: 'assets/img/users.jpg', status: 'Active' },
      { id: 2, firstName: 'Terry', lastName: 'Baker', age: 63, address: '555 Front St #APT 2H, Hempstead, NY, 11550', phone: '(376) 150 6975', email: 'terrybaker@example.com', avatar: 'assets/img/users.jpg', status: 'Active' },
      { id: 3, firstName: 'Kyle', lastName: 'Bowman', age: 7, address: '5060 Fairways Cir #APT 207, Vero Beach, FL, 32967', phone: '(981) 756 6128', email: 'kylebowman@example.com', avatar: 'assets/img/users.jpg', status: 'Active' },
      { id: 4, firstName: 'Marie', lastName: 'Howard', age: 22, address: '3501 New Haven Ave #152, Columbia, MO, 65201', phone: '(634) 09 3833', email: 'mariehoward@example.com', avatar: 'assets/img/users.jpg', status: 'Active' },
      { id: 5, firstName: 'Joshua', lastName: 'Guzman', age: 34, address: '4712 Spring Creek Dr, Bonita Springs, FL, 34134', phone: '(407) 554 4146', email: 'joshuaguzman@example.com', avatar: 'assets/img/users.jpg', status: 'Active' },
      { id: 6, firstName: 'Julia', lastName: 'Sims', age: 27, address: '517 Walker Dr, Houma, LA, 70364, United States', phone: '(680) 432 2662', email: 'juliasims@example.com', avatar: 'assets/img/users.jpg', status: 'Active' },
      { id: 7, firstName: 'Linda', lastName: 'Carpenter', age: 24, address: '2226 Victory Garden Ln, Tallahassee, FL, 32301', phone: '(218) 661 8316', email: 'lindacarpenter@example.com', avatar: 'assets/img/users.jpg', status: 'Active' },
      { id: 8, firstName: 'Melissa', lastName: 'Burton', age: 35, address: '3321 N 26th St, Milwaukee, WI, 53206', phone: '(192) 494 8073', email: 'melissaburton@example.com', avatar: 'assets/img/users.jpg', status: 'Active' },
      { id: 9, firstName: 'Patrick', lastName: 'Knight', age: 21, address: 'Po Box 3336, Commerce, TX, 75429', phone: '(785) 580 4514', email: 'patrickknight@example.com', avatar: 'assets/img/users.jpg', status: 'Active' },
      { id: 10, firstName: 'Denise', lastName: 'Stevens', age: 7, address: '1603 Old York Rd, Abington, PA, 19001', phone: '(836) 257 1379', email: 'denisestevens@example.com', avatar: 'assets/img/users.jpg', status: 'Active' },
      { id: 11, firstName: 'Judy', lastName: 'Clark', age: 22, address: '4093 Woodside Circle, Pensacola, FL, 32514', phone: '(359) 969 3594', email: 'judy.clark@example.com', avatar: 'assets/img/users.jpg', status: 'Active' },
      { id: 12, firstName: 'Dennis', lastName: 'Salazar', age: 34, address: '891 Juniper Drive, Saginaw, MI, 48603', phone: '(933) 137 6201', email: 'dennissalazar@example.com', avatar: 'assets/img/users.jpg', status: 'Active' },
      { id: 13, firstName: 'Charles', lastName: 'Ortega', age: 32, address: '3169 Birch Street, El Paso, TX, 79915', phone: '(380) 141 1885', email: 'charlesortega@example.com', avatar: 'assets/img/users.jpg', status: 'Active' },
      { id: 14, firstName: 'Sandra', lastName: 'Mendez', age: 24, address: '2535 Linden Avenue, Orlando, FL, 32789', phone: '(797) 506 1265', email: 'sandramendez@example.com', avatar: 'assets/img/users.jpg', status: 'Active' }
    ];
    this.filteredPatients = this.patients;
    this.spinner.hide();
  }

  // CREATE - Add new patient
  createPatient(): void {
    this.submitted = true;

    if (this.patientForm.invalid) {
      return;
    }

    this.spinner.show();
    const newPatient: Patient = {
      firstName: this.patientForm.value.firstName,
      lastName: this.patientForm.value.lastName,
      email: this.patientForm.value.email,
      phone: this.patientForm.value.phone,
      dateOfBirth: this.patientForm.value.dateOfBirth,
      gender: this.patientForm.value.gender,
      address: this.patientForm.value.address,
      city: this.patientForm.value.city,
      state: this.patientForm.value.state,
      postalCode: this.patientForm.value.postalCode,
      country: this.patientForm.value.country,
      bloodType: this.patientForm.value.bloodType,
      emergencyContact: this.patientForm.value.emergencyContact,
      emergencyPhone: this.patientForm.value.emergencyPhone,
      medicalHistory: this.patientForm.value.medicalHistory,
      avatar: this.avatarPreview || 'assets/img/users.jpg',
      status: this.patientForm.value.status,
      age: this.calculateAge(this.patientForm.value.dateOfBirth)
    };

    // this.patientService.create(newPatient).subscribe({
    //   next: (response) => {
    //     this.patients.push(response);
    //     this.filteredPatients = this.patients;
    //     this.resetCreateForm();
    //     this.closeModal('addPatientModal');
    //     this.spinner.hide();
    //     this.modalService.openSuccessModal('Patient created successfully!');
    //   },
    //   error: (error) => {
    //     this.spinner.hide();
    //     this.message = error.error?.message || 'Error creating patient';
    //     this.modalService.openWarning('Error', this.message);
    //   }
    // });

    // Demo
    newPatient.id = Math.max(...this.patients.map(p => p.id || 0)) + 1;
    this.patients.push(newPatient);
    this.filteredPatients = this.patients;
    this.resetCreateForm();
    this.closeModal('addPatientModal');
    this.spinner.hide();
    this.modalService.openSuccessModal('Patient created successfully!');
  }

  // UPDATE - Edit existing patient
  openUpdateModal(patient: Patient): void {
    this.selectedPatient = patient;
    this.updateForm.patchValue({
      id: patient.id,
      firstName: patient.firstName,
      lastName: patient.lastName,
      email: patient.email,
      phone: patient.phone,
      dateOfBirth: this.formatDateForInput(patient.dateOfBirth),
      gender: patient.gender,
      address: patient.address,
      city: patient.city,
      state: patient.state,
      postalCode: patient.postalCode,
      country: patient.country,
      bloodType: patient.bloodType,
      emergencyContact: patient.emergencyContact,
      emergencyPhone: patient.emergencyPhone,
      medicalHistory: patient.medicalHistory,
      status: patient.status
    });
    this.updateSubmitted = false;
    this.avatarPreview = patient.avatar || null;
    $('#updatePatientModal').modal('show');
  }

  updatePatient(): void {
    this.updateSubmitted = true;

    if (this.updateForm.invalid) {
      return;
    }

    this.spinner.show();
    const updatedPatient: Patient = {
      id: this.updateForm.value.id,
      firstName: this.updateForm.value.firstName,
      lastName: this.updateForm.value.lastName,
      email: this.updateForm.value.email,
      phone: this.updateForm.value.phone,
      dateOfBirth: this.updateForm.value.dateOfBirth,
      gender: this.updateForm.value.gender,
      address: this.updateForm.value.address,
      city: this.updateForm.value.city,
      state: this.updateForm.value.state,
      postalCode: this.updateForm.value.postalCode,
      country: this.updateForm.value.country,
      bloodType: this.updateForm.value.bloodType,
      emergencyContact: this.updateForm.value.emergencyContact,
      emergencyPhone: this.updateForm.value.emergencyPhone,
      medicalHistory: this.updateForm.value.medicalHistory,
      avatar: this.avatarPreview || this.selectedPatient?.avatar,
      status: this.updateForm.value.status,
      age: this.calculateAge(this.updateForm.value.dateOfBirth)
    };

    // this.patientService.update(updatedPatient.id!, updatedPatient).subscribe({
    //   next: () => {
    //     const index = this.patients.findIndex(p => p.id === updatedPatient.id);
    //     if (index > -1) {
    //       this.patients[index] = updatedPatient;
    //       this.filteredPatients = this.patients;
    //     }
    //     this.resetUpdateForm();
    //     this.closeModal('updatePatientModal');
    //     this.spinner.hide();
    //     this.modalService.openSuccessModal('Patient updated successfully!');
    //   },
    //   error: (error) => {
    //     this.spinner.hide();
    //     this.message = error.error?.message || 'Error updating patient';
    //     this.modalService.openWarning('Error', this.message);
    //   }
    // });

    // Demo
    const index = this.patients.findIndex(p => p.id === updatedPatient.id);
    if (index > -1) {
      this.patients[index] = updatedPatient;
      this.filteredPatients = this.patients;
    }
    this.resetUpdateForm();
    this.closeModal('updatePatientModal');
    this.spinner.hide();
    this.modalService.openSuccessModal('Patient updated successfully!');
  }

  // DELETE - Remove patient
  deletePatient(id: number | undefined): void {
    if (!id || !confirm('Are you sure you want to delete this patient?')) {
      return;
    }

    this.spinner.show();
    // this.patientService.delete(id).subscribe({
    //   next: () => {
    //     this.patients = this.patients.filter(p => p.id !== id);
    //     this.filteredPatients = this.patients;
    //     this.closeModal('deletePatientModal');
    //     this.spinner.hide();
    //     this.modalService.openSuccessModal('Patient deleted successfully!');
    //   },
    //   error: (error) => {
    //     this.spinner.hide();
    //     this.message = error.error?.message || 'Error deleting patient';
    //     this.modalService.openWarning('Error', this.message);
    //   }
    // });

    // Demo
    this.patients = this.patients.filter(p => p.id !== id);
    this.filteredPatients = this.patients;
    this.closeModal('deletePatientModal');
    this.spinner.hide();
    this.modalService.openSuccessModal('Patient deleted successfully!');
  }

  // Search patients
  searchPatients(): void {
    if (!this.searchQuery.trim()) {
      this.filteredPatients = this.patients;
      return;
    }

    const query = this.searchQuery.toLowerCase();
    this.filteredPatients = this.patients.filter(p =>
      p.firstName?.toLowerCase().includes(query) ||
      p.lastName?.toLowerCase().includes(query) ||
      p.email?.toLowerCase().includes(query) ||
      p.phone?.toLowerCase().includes(query)
    );
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

  // Calculate age from DOB
  calculateAge(dateOfBirth: any): number {
    if (!dateOfBirth) return 0;
    const today = new Date();
    const birthDate = new Date(dateOfBirth);
    let age = today.getFullYear() - birthDate.getFullYear();
    const monthDiff = today.getMonth() - birthDate.getMonth();
    if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDate.getDate())) {
      age--;
    }
    return age;
  }

  // Utility methods
  resetCreateForm(): void {
    this.patientForm.reset({ status: 'Active', gender: '', country: '' });
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
    $('#addPatientModal').modal('show');
  }

  openDeleteModal(patient: Patient): void {
    this.selectedPatient = patient;
    $('#deletePatientModal').modal('show');
  }

  formatDateForInput(date: any): string {
    if (!date) return '';
    const d = new Date(date);
    return d.toISOString().split('T')[0];
  }

  getFullName(patient: Patient): string {
    return `${patient.firstName} ${patient.lastName}`.trim();
  }

  // Getters for form validation
  get f() {
    return this.patientForm.controls;
  }

  get uf() {
    return this.updateForm.controls;
  }

}
