import { CommonModule, NgFor, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { ModalService } from '../../../shared/service/modal.service';
declare var $: any;
interface PatientInfo {
  id?: number;
  firstName?: string;
  lastName?: string;
  dateOfBirth?: Date;
  age?: number;
  address?: string;
  city?: string;
  country?: string;
  avatar?: string;
}

interface Appointment {
  id?: number;
  doctorName?: string;
  specialization?: string;
  appointmentDate?: Date;
  appointmentTime?: string;
  bookingDate?: Date;
  amount?: number;
  followUpDate?: Date;
  status?: string; // Confirm, Pending, Cancelled
  doctorImage?: string;
}

interface Prescription {
  id?: number;
  date?: Date;
  name?: string;
  doctorName?: string;
  specialization?: string;
  doctorImage?: string;
}

interface MedicalRecord {
  id?: string;
  date?: Date;
  description?: string;
  attachment?: string;
  createdBy?: string;
  specialization?: string;
  doctorImage?: string;
}

interface Invoice {
  id?: string;
  doctorName?: string;
  specialization?: string;
  amount?: number;
  paidOn?: Date;
  doctorImage?: string;
}



 
@Component({
    selector: 'app-patient-dashboard',
    imports: [CommonModule,NgFor,NgIf],
    templateUrl: './patient-dashboard.component.html',
    styleUrl: './patient-dashboard.component.css'
})
export class PatientDashboardComponent {



 // Patient Info
  patientInfo: PatientInfo = {
    firstName: 'Richard',
    lastName: 'Wilson',
    dateOfBirth: new Date('1983-07-24'),
    age: 38,
    address: '1545 Dorsey Ln NE',
    city: 'Newyork',
    country: 'USA',
    avatar: 'assets/img/patients/patient.jpg'
  };
  // Tab Data
  appointments: Appointment[] = [];
  prescriptions: Prescription[] = [];
  medicalRecords: MedicalRecord[] = [];
  invoices: Invoice[] = [];

  // UI State
  activeTab = 'appointments';
  selectedAppointment: Appointment | null = null;
  selectedPrescription: Prescription | null = null;
  selectedRecord: MedicalRecord | null = null;
  selectedInvoice: Invoice | null = null;

  constructor(
    private spinner: NgxSpinnerService,
    private modalService: ModalService
    // private patientService: PatientService,
    // private appointmentService: AppointmentService,
    // private prescriptionService: PrescriptionService,
    // private medicalRecordService: MedicalRecordService,
    // private invoiceService: InvoiceService
  ) {}

  ngOnInit(): void {
    this.loadDashboardData();
  }

  // Load all dashboard data
  loadDashboardData(): void {
    this.spinner.show();
    this.loadPatientInfo();
    this.loadAppointments();
    this.loadPrescriptions();
    this.loadMedicalRecords();
    this.loadInvoices();
    this.spinner.hide();
  }

  // Load patient information
  loadPatientInfo(): void {
    // this.patientService.getPatientInfo().subscribe({
    //   next: (data) => {
    //     this.patientInfo = data;
    //   },
    //   error: (error) => console.error('Error loading patient info:', error)
    // });
  }

  // Load appointments
  loadAppointments(): void {
    // this.appointmentService.getPatientAppointments().subscribe({
    //   next: (data) => {
    //     this.appointments = data;
    //   },
    //   error: (error) => {
    //     console.error('Error loading appointments:', error);
    //     this.modalService.openWarning('Error', 'Failed to load appointments');
    //   }
    // });

    // Demo data
    this.appointments = [
      {
        id: 1,
        doctorName: 'Dr. Ruby Perrin',
        specialization: 'Dental',
        appointmentDate: new Date('2024-11-14'),
        appointmentTime: '10:00 AM',
        bookingDate: new Date('2024-11-12'),
        amount: 160,
        followUpDate: new Date('2024-11-16'),
        status: 'Confirm',
        doctorImage: 'assets/img/doctors/doctor-thumb-01.jpg'
      },
      {
        id: 2,
        doctorName: 'Dr. Darren Elder',
        specialization: 'Dental',
        appointmentDate: new Date('2024-11-12'),
        appointmentTime: '8:00 PM',
        bookingDate: new Date('2024-11-12'),
        amount: 250,
        followUpDate: new Date('2024-11-14'),
        status: 'Confirm',
        doctorImage: 'assets/img/doctors/doctor-thumb-02.jpg'
      },
      {
        id: 3,
        doctorName: 'Dr. Deborah Angel',
        specialization: 'Cardiology',
        appointmentDate: new Date('2024-11-11'),
        appointmentTime: '11:00 AM',
        bookingDate: new Date('2024-11-10'),
        amount: 400,
        followUpDate: new Date('2024-11-13'),
        status: 'Cancelled',
        doctorImage: 'assets/img/doctors/doctor-thumb-03.jpg'
      },
      {
        id: 4,
        doctorName: 'Dr. Sofia Brient',
        specialization: 'Urology',
        appointmentDate: new Date('2024-11-10'),
        appointmentTime: '3:00 PM',
        bookingDate: new Date('2024-11-10'),
        amount: 350,
        followUpDate: new Date('2024-11-12'),
        status: 'Pending',
        doctorImage: 'assets/img/doctors/doctor-thumb-04.jpg'
      },
      {
        id: 5,
        doctorName: 'Dr. Marvin Campbell',
        specialization: 'Ophthalmology',
        appointmentDate: new Date('2024-11-09'),
        appointmentTime: '7:00 PM',
        bookingDate: new Date('2024-11-08'),
        amount: 75,
        followUpDate: new Date('2024-11-11'),
        status: 'Confirm',
        doctorImage: 'assets/img/doctors/doctor-thumb-05.jpg'
      }
    ];
  }

  // Load prescriptions
  loadPrescriptions(): void {
    // this.prescriptionService.getPatientPrescriptions().subscribe({
    //   next: (data) => {
    //     this.prescriptions = data;
    //   },
    //   error: (error) => console.error('Error loading prescriptions:', error)
    // });

    // Demo data
    this.prescriptions = [
      {
        id: 1,
        date: new Date('2024-11-14'),
        name: 'Prescription 1',
        doctorName: 'Dr. Ruby Perrin',
        specialization: 'Dental',
        doctorImage: 'assets/img/doctors/doctor-thumb-01.jpg'
      },
      {
        id: 2,
        date: new Date('2024-11-13'),
        name: 'Prescription 2',
        doctorName: 'Dr. Darren Elder',
        specialization: 'Dental',
        doctorImage: 'assets/img/doctors/doctor-thumb-02.jpg'
      },
      {
        id: 3,
        date: new Date('2024-11-12'),
        name: 'Prescription 3',
        doctorName: 'Dr. Deborah Angel',
        specialization: 'Cardiology',
        doctorImage: 'assets/img/doctors/doctor-thumb-03.jpg'
      },
      {
        id: 4,
        date: new Date('2024-11-11'),
        name: 'Prescription 4',
        doctorName: 'Dr. Sofia Brient',
        specialization: 'Urology',
        doctorImage: 'assets/img/doctors/doctor-thumb-04.jpg'
      },
      {
        id: 5,
        date: new Date('2024-11-10'),
        name: 'Prescription 5',
        doctorName: 'Dr. Marvin Campbell',
        specialization: 'Ophthalmology',
        doctorImage: 'assets/img/doctors/doctor-thumb-05.jpg'
      }
    ];
  }

  // Load medical records
  loadMedicalRecords(): void {
    // this.medicalRecordService.getPatientRecords().subscribe({
    //   next: (data) => {
    //     this.medicalRecords = data;
    //   },
    //   error: (error) => console.error('Error loading medical records:', error)
    // });

    // Demo data
    this.medicalRecords = [
      {
        id: '#MR-0010',
        date: new Date('2024-11-14'),
        description: 'Dental Filling',
        attachment: 'dental-test.pdf',
        createdBy: 'Dr. Ruby Perrin',
        specialization: 'Dental',
        doctorImage: 'assets/img/doctors/doctor-thumb-01.jpg'
      },
      {
        id: '#MR-0009',
        date: new Date('2024-11-13'),
        description: 'Teeth Cleaning',
        attachment: 'dental-test.pdf',
        createdBy: 'Dr. Darren Elder',
        specialization: 'Dental',
        doctorImage: 'assets/img/doctors/doctor-thumb-02.jpg'
      },
      {
        id: '#MR-0008',
        date: new Date('2024-11-12'),
        description: 'General Checkup',
        attachment: 'cardio-test.pdf',
        createdBy: 'Dr. Deborah Angel',
        specialization: 'Cardiology',
        doctorImage: 'assets/img/doctors/doctor-thumb-03.jpg'
      },
      {
        id: '#MR-0007',
        date: new Date('2024-11-11'),
        description: 'General Test',
        attachment: 'general-test.pdf',
        createdBy: 'Dr. Sofia Brient',
        specialization: 'Urology',
        doctorImage: 'assets/img/doctors/doctor-thumb-04.jpg'
      },
      {
        id: '#MR-0006',
        date: new Date('2024-11-10'),
        description: 'Eye Test',
        attachment: 'eye-test.pdf',
        createdBy: 'Dr. Marvin Campbell',
        specialization: 'Ophthalmology',
        doctorImage: 'assets/img/doctors/doctor-thumb-05.jpg'
      }
    ];
  }

  // Load invoices
  loadInvoices(): void {
    // this.invoiceService.getPatientInvoices().subscribe({
    //   next: (data) => {
    //     this.invoices = data;
    //   },
    //   error: (error) => console.error('Error loading invoices:', error)
    // });

    // Demo data
    this.invoices = [
      {
        id: '#INV-0010',
        doctorName: 'Dr. Ruby Perrin',
        specialization: 'Dental',
        amount: 450,
        paidOn: new Date('2024-11-14'),
        doctorImage: 'assets/img/doctors/doctor-thumb-01.jpg'
      },
      {
        id: '#INV-0009',
        doctorName: 'Dr. Darren Elder',
        specialization: 'Dental',
        amount: 300,
        paidOn: new Date('2024-11-13'),
        doctorImage: 'assets/img/doctors/doctor-thumb-02.jpg'
      },
      {
        id: '#INV-0008',
        doctorName: 'Dr. Deborah Angel',
        specialization: 'Cardiology',
        amount: 150,
        paidOn: new Date('2024-11-12'),
        doctorImage: 'assets/img/doctors/doctor-thumb-03.jpg'
      },
      {
        id: '#INV-0007',
        doctorName: 'Dr. Sofia Brient',
        specialization: 'Urology',
        amount: 50,
        paidOn: new Date('2024-11-11'),
        doctorImage: 'assets/img/doctors/doctor-thumb-04.jpg'
      },
      {
        id: '#INV-0006',
        doctorName: 'Dr. Marvin Campbell',
        specialization: 'Ophthalmology',
        amount: 600,
        paidOn: new Date('2024-11-10'),
        doctorImage: 'assets/img/doctors/doctor-thumb-05.jpg'
      }
    ];
  }

  // Appointment actions
  viewAppointmentDetails(appointment: Appointment): void {
    this.selectedAppointment = appointment;
    this.modalService.openSuccessModal(`Appointment with ${appointment.doctorName} - ${appointment.appointmentDate}`);
  }

  rescheduleAppointment(appointment: Appointment): void {
    this.selectedAppointment = appointment;
    this.modalService.openSuccessModal(`Reschedule appointment with ${appointment.doctorName}`);
  }

  cancelAppointment(appointment: Appointment): void {
    if (!confirm('Are you sure you want to cancel this appointment?')) {
      return;
    }

    this.spinner.show();
    appointment.status = 'Cancelled';
    this.spinner.hide();
    this.modalService.openSuccessModal('Appointment cancelled successfully!');
  }

  // Prescription actions
  viewPrescriptionDetails(prescription: Prescription): void {
    this.selectedPrescription = prescription;
    this.modalService.openSuccessModal(`Prescription from ${prescription.doctorName}`);
  }

  downloadPrescription(prescription: Prescription): void {
    this.modalService.openSuccessModal(`Downloading prescription from ${prescription.doctorName}`);
  }

  // Medical record actions
  viewMedicalRecord(record: MedicalRecord): void {
    this.selectedRecord = record;
    this.modalService.openSuccessModal(`Medical Record: ${record.description}`);
  }

  downloadMedicalRecord(record: MedicalRecord): void {
    this.modalService.openSuccessModal(`Downloading: ${record.attachment}`);
  }

  // Invoice actions
  viewInvoice(invoice: Invoice): void {
    this.selectedInvoice = invoice;
    this.modalService.openSuccessModal(`Invoice ${invoice.id}`);
  }

  downloadInvoice(invoice: Invoice): void {
    this.modalService.openSuccessModal(`Downloading invoice ${invoice.id}`);
  }

  // Tab switching
  switchTab(tab: string): void {
    this.activeTab = tab;
  }

  // Status badge styling
  getStatusBadgeClass(status?: string): string {
    switch (status) {
      case 'Confirm':
        return 'bg-success-light';
      case 'Pending':
        return 'bg-warning-light';
      case 'Cancelled':
        return 'bg-danger-light';
      default:
        return 'bg-info-light';
    }
  }

  // Utility methods
  formatDate(date?: Date): string {
    if (!date) return '';
    return new Date(date).toLocaleDateString('en-US', {
      year: 'numeric',
      month: 'short',
      day: 'numeric'
    });
  }

  formatCurrency(amount?: number): string {
    if (!amount) return '$0';
    return `$${amount.toLocaleString()}`;
  }

  getFullName(): string {
    return `${this.patientInfo.firstName} ${this.patientInfo.lastName}`.trim();
  }

  getAge(): number {
    if (!this.patientInfo.dateOfBirth) return 0;
    const today = new Date();
    const birthDate = new Date(this.patientInfo.dateOfBirth);
    let age = today.getFullYear() - birthDate.getFullYear();
    const monthDiff = today.getMonth() - birthDate.getMonth();
    if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDate.getDate())) {
      age--;
    }
    return age;
  }
}
