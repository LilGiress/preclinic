import { CommonModule, NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Appointment } from '../../../models/appointement/appointment';
import { NgxSpinnerService } from 'ngx-spinner';
import { ModalService } from '../../../services/modal.service';


interface DoctorStats {
  totalPatients?: number;
  todayPatients?: number;
  appointments?: number;
}
@Component({
    selector: 'app-doctor-dashboard',
    imports: [CommonModule,NgFor,NgIf],
    templateUrl: './doctor-dashboard.component.html',
    styleUrl: './doctor-dashboard.component.css'
})
export class DoctorDashboardComponent implements OnInit {

     doctorName = 'Dr. Darren Elder';
  doctorSpecialization = 'BDS, MDS - Oral & Maxillofacial Surgery';
  doctorImage = 'assets/img/doctors/doctor-thumb-02.jpg';
  
  stats: DoctorStats = {
    totalPatients: 1500,
    todayPatients: 160,
    appointments: 85
  };

  upcomingAppointments: Appointment[] = [];
  todayAppointments: Appointment[] = [];
  selectedAppointment: Appointment | null = null;
  activeTab = 'upcoming';
currentDate: Date|undefined;

  constructor(
    private spinner: NgxSpinnerService,
    private modalService: ModalService
    // private appointmentService: AppointmentService,
    // private doctorService: DoctorService
  ) {}

  ngOnInit(): void {
    this.loadDashboardData();
  }

  // Load all dashboard data
  loadDashboardData(): void {
    this.spinner.show();
    this.loadStats();
    this.loadUpcomingAppointments();
    this.loadTodayAppointments();
    this.spinner.hide();
  }

  // Load dashboard statistics
  loadStats(): void {
    // this.doctorService.getDoctorStats().subscribe({
    //   next: (data) => {
    //     this.stats = data;
    //   },
    //   error: (error) => {
    //     console.error('Error loading stats:', error);
    //   }
    // });

    // Demo stats (already set in property initialization)
  }

  // Load upcoming appointments
  loadUpcomingAppointments(): void {
    // this.appointmentService.getUpcomingAppointments().subscribe({
    //   next: (data) => {
    //     this.upcomingAppointments = data;
    //   },
    //   error: (error) => {
    //     console.error('Error loading upcoming appointments:', error);
    //     this.modalService.openWarning('Error', 'Failed to load upcoming appointments');
    //   }
    // });

    // Demo data
    this.upcomingAppointments = [
      {
        id: 1,
        patientName: 'Richard Wilson',
        patientCode: '#PT0016',
        patientImage: 'assets/img/patients/patient.jpg',
        appointmentDate: new Date('2024-12-11'),
        appointmentTime: '10:00 AM',
        purpose: 'General',
        type: 'New Patient',
        paidAmount: 150,
        status: 'Pending'
      },
      {
        id: 2,
        patientName: 'Charlene Reed',
        patientCode: '#PT0001',
        patientImage: 'assets/img/patients/patient1.jpg',
        appointmentDate: new Date('2024-12-03'),
        appointmentTime: '11:00 AM',
        purpose: 'General',
        type: 'Old Patient',
        paidAmount: 200,
        status: 'Pending'
      },
      {
        id: 3,
        patientName: 'Travis Trimble',
        patientCode: '#PT0002',
        patientImage: 'assets/img/patients/patient2.jpg',
        appointmentDate: new Date('2024-12-01'),
        appointmentTime: '1:00 PM',
        purpose: 'General',
        type: 'New Patient',
        paidAmount: 75,
        status: 'Pending'
      },
      {
        id: 4,
        patientName: 'Carl Kelly',
        patientCode: '#PT0003',
        patientImage: 'assets/img/patients/patient3.jpg',
        appointmentDate: new Date('2024-10-30'),
        appointmentTime: '9:00 AM',
        purpose: 'General',
        type: 'Old Patient',
        paidAmount: 100,
        status: 'Pending'
      },
      {
        id: 5,
        patientName: 'Michelle Fairfax',
        patientCode: '#PT0004',
        patientImage: 'assets/img/patients/patient4.jpg',
        appointmentDate: new Date('2024-10-28'),
        appointmentTime: '6:00 PM',
        purpose: 'General',
        type: 'New Patient',
        paidAmount: 350,
        status: 'Pending'
      },
      {
        id: 6,
        patientName: 'Gina Moore',
        patientCode: '#PT0005',
        patientImage: 'assets/img/patients/patient5.jpg',
        appointmentDate: new Date('2024-10-27'),
        appointmentTime: '8:00 AM',
        purpose: 'General',
        type: 'Old Patient',
        paidAmount: 250,
        status: 'Pending'
      }
    ];
  }

  // Load today's appointments
  loadTodayAppointments(): void {
    // this.appointmentService.getTodayAppointments().subscribe({
    //   next: (data) => {
    //     this.todayAppointments = data;
    //   },
    //   error: (error) => {
    //     console.error('Error loading today appointments:', error);
    //     this.modalService.openWarning('Error', 'Failed to load today appointments');
    //   }
    // });

    // Demo data
    this.todayAppointments = [
      {
        id: 7,
        patientName: 'Elsie Gilley',
        patientCode: '#PT0006',
        patientImage: 'assets/img/patients/patient6.jpg',
        appointmentDate: new Date(),
        appointmentTime: '6:00 PM',
        purpose: 'Fever',
        type: 'Old Patient',
        paidAmount: 300,
        status: 'Pending'
      },
      {
        id: 8,
        patientName: 'Joan Gardner',
        patientCode: '#PT0006',
        patientImage: 'assets/img/patients/patient7.jpg',
        appointmentDate: new Date(),
        appointmentTime: '5:00 PM',
        purpose: 'General',
        type: 'Old Patient',
        paidAmount: 100,
        status: 'Pending'
      },
      {
        id: 9,
        patientName: 'Daniel Griffing',
        patientCode: '#PT0007',
        patientImage: 'assets/img/patients/patient8.jpg',
        appointmentDate: new Date(),
        appointmentTime: '3:00 PM',
        purpose: 'General',
        type: 'New Patient',
        paidAmount: 75,
        status: 'Pending'
      },
      {
        id: 10,
        patientName: 'Walter Roberson',
        patientCode: '#PT0008',
        patientImage: 'assets/img/patients/patient9.jpg',
        appointmentDate: new Date(),
        appointmentTime: '1:00 PM',
        purpose: 'General',
        type: 'Old Patient',
        paidAmount: 350,
        status: 'Pending'
      },
      {
        id: 11,
        patientName: 'Robert Rhodes',
        patientCode: '#PT0010',
        patientImage: 'assets/img/patients/patient10.jpg',
        appointmentDate: new Date(),
        appointmentTime: '10:00 AM',
        purpose: 'General',
        type: 'New Patient',
        paidAmount: 175,
        status: 'Pending'
      },
      {
        id: 12,
        patientName: 'Harry Williams',
        patientCode: '#PT0011',
        patientImage: 'assets/img/patients/patient11.jpg',
        appointmentDate: new Date(),
        appointmentTime: '11:00 AM',
        purpose: 'General',
        type: 'New Patient',
        paidAmount: 450,
        status: 'Pending'
      }
    ];
  }

  // Accept appointment
  acceptAppointment(appointment: Appointment): void {
    if (!appointment.id) return;

    this.spinner.show();
    // this.appointmentService.acceptAppointment(appointment.id).subscribe({
    //   next: () => {
    //     appointment.status = 'Accepted';
    //     this.spinner.hide();
    //     this.modalService.openSuccessModal('Appointment accepted successfully!');
    //   },
    //   error: (error) => {
    //     this.spinner.hide();
    //     this.modalService.openWarning('Error', 'Failed to accept appointment');
    //   }
    // });

    // Demo
    appointment.status = 'Accepted';
    this.spinner.hide();
    this.modalService.openSuccessModal('Appointment accepted successfully!');
  }

  // Cancel appointment
  cancelAppointment(appointment: Appointment): void {
    if (!appointment.id) return;

    if (!confirm('Are you sure you want to cancel this appointment?')) {
      return;
    }

    this.spinner.show();
    // this.appointmentService.cancelAppointment(appointment.id).subscribe({
    //   next: () => {
    //     appointment.status = 'Cancelled';
    //     this.spinner.hide();
    //     this.modalService.openSuccessModal('Appointment cancelled successfully!');
    //   },
    //   error: (error) => {
    //     this.spinner.hide();
    //     this.modalService.openWarning('Error', 'Failed to cancel appointment');
    //   }
    // });

    // Demo
    appointment.status = 'Cancelled';
    this.spinner.hide();
    this.modalService.openSuccessModal('Appointment cancelled successfully!');
  }

  // View appointment details
  viewAppointment(appointment: Appointment): void {
    this.selectedAppointment = appointment;
    this.modalService.openSuccessModal(`Viewing appointment for ${appointment.patientName}`);
  }

  // Get appointment status badge class
  getStatusBadgeClass(status?: string): string {
    switch (status) {
      case 'Accepted':
        return 'badge-success';
      case 'Cancelled':
        return 'badge-danger';
      case 'Completed':
        return 'badge-info';
      default:
        return 'badge-warning';
    }
  }

  // Get appointment list based on tab
  getAppointmentList(): Appointment[] {
    return this.activeTab === 'upcoming' 
      ? this.upcomingAppointments 
      : this.todayAppointments;
  }

  // Switch tabs
  switchTab(tab: string): void {
    this.activeTab = tab;
  }

  // Refresh data
  refreshData(): void {
    this.loadDashboardData();
    this.modalService.openSuccessModal('Dashboard refreshed!');
  }

  // Format currency
  formatCurrency(amount?: number): string {
    if (!amount) return '$0';
    return `$${amount.toLocaleString()}`;
  }

  // Format date
  formatDate(date?: Date): string {
    if (!date) return '';
    const d = new Date(date);
    const options: Intl.DateTimeFormatOptions = { 
      year: 'numeric', 
      month: 'short', 
      day: 'numeric' 
    };
    return d.toLocaleDateString('en-US', options);
  }
}
