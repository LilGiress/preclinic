import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Holiday } from '../../models/holidays';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HolidayService } from '../../services/holiday.service';
import { CommonModule } from '@angular/common';
import { ModalService } from '../../services/modal.service';
import { NgxSpinnerService } from 'ngx-spinner';
declare var $: any; // For jQuery
@Component({
    selector: 'app-holidays',
    imports: [CommonModule,ReactiveFormsModule],
    templateUrl: './holidays.component.html',
    styleUrl: './holidays.component.css'
})
export class HolidaysComponent implements OnInit {
loadingHolidays: any;
    holidays: Holiday[] = [];
  createForm!: FormGroup;
  updateForm!: FormGroup;
  selectedHoliday: Holiday | null = null;
  submitted = false;
  message='';
  loading=false;
   

    @ViewChild('createModal') createModal!: ElementRef;
  @ViewChild('updateModal') updateModal!: ElementRef;

  constructor(
    private fb: FormBuilder,
    private holidayService: HolidayService,
     private readonly modalService:ModalService,
    private readonly spinner:NgxSpinnerService,
  ) {
    this.initializeForms();
  }

  ngOnInit(): void {
    this.loadHolidays();
  }

     // Initialize Reactive Forms
  initializeForms(): void {
    this.createForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      date: ['', [Validators.required, this.futureDateValidator.bind(this)]]
    });

    this.updateForm = this.fb.group({
      id: [''],
      name: ['', [Validators.required, Validators.minLength(3)]],
      date: ['', [Validators.required]]
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

      // LOAD - Fetch all holidays
  loadHolidays(): void {
    this.holidayService.getAll().subscribe({
      next: (data) => {
        this.holidays = data;
      },
      error: (error) => {
        console.error('Error loading holidays:', error);
        //alert('Error loading holidays');
      }
    });
  }

// CREATE - Add new holiday
  createHoliday(): void {
    this.submitted = true;

    if (this.createForm.invalid) {
      //alert('Please fill in all required fields correctly');
      return;
    }
    this.spinner.show();
    const newHoliday: Holiday = {
      name: this.createForm.value.name,
      date: new Date(this.createForm.value.date)
    };

    this.holidayService.create(newHoliday).subscribe({
      next: (response) => {
        this.holidays.push(response);
        this.resetCreateForm();
        this.closeModal('createModal');
        this.spinner.hide();
        this.modalService.openSuccessModal('Holiday created successfully!',)
        
      },
      error: (error) => {
        this.spinner.hide();
        this.message= error.error;
        this.modalService.openWarning('Error creating holiday:',this.message);
        console.error('Error creating holiday:', error);
        
      }
    });
  }

     // UPDATE - Edit existing holiday
  openUpdateModal(holiday: Holiday): void {
    this.selectedHoliday = holiday;
    this.updateForm.patchValue({
      id: holiday.id,
      name: holiday.name,
      date: this.formatDateForInput(holiday.date)
    });
    this.submitted = false;
    $('#updateholidaysModal').modal('show');
  }

 updateHoliday(): void {
    this.submitted = true;

    if (this.updateForm.invalid) {
     // alert('Please fill in all required fields correctly');
      return;
    }

    const updatedHoliday: Holiday = {
      id: this.updateForm.value.id,
      name: this.updateForm.value.name,
      date: new Date(this.updateForm.value.date)
    };

    this.holidayService.update(updatedHoliday.id!, updatedHoliday).subscribe({
      next: () => {
        const index = this.holidays.findIndex(h => h.id === updatedHoliday.id);
        if (index > -1) {
          this.holidays[index] = updatedHoliday;
        }
        this.resetUpdateForm();
        this.closeModal('updateholidaysModal');
        //alert('Holiday updated successfully!');
      },
      error: (error) => {
        console.error('Error updating holiday:', error);
       // alert('Error updating holiday');
      }
    });
  }


  // DELETE - Remove holiday
  deleteHoliday(id: number | undefined): void {
    if (!id || !confirm('Are you sure you want to delete this holiday?')) {
      return;
    }

    this.holidayService.delete(id).subscribe({
      next: () => {
        this.holidays = this.holidays.filter(h => h.id !== id);
        this.closeModal('delete_holiday');
       // alert('Holiday deleted successfully!');
      },
      error: (error) => {
        console.error('Error deleting holiday:', error);
       // alert('Error deleting holiday');
      }
    });
  }

 // Utility methods
  resetCreateForm(): void {
    this.createForm.reset();
    this.submitted = false;
  }

  resetUpdateForm(): void {
    this.updateForm.reset();
    this.submitted = false;
  }

  closeModal(modalId: string): void {
    $(`#${modalId}`).modal('hide');
  }

  formatDateForInput(date: any): string {
    if (!date) return '';
    const d = new Date(date);
    return d.toISOString().split('T')[0];
  }

  getDayOfWeek(date: any): string {
    const d = new Date(date);
    const days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
    return days[d.getDay()];
  }

     getFormattedDate(date: any): string {
    if (!date) return '';
    return new Date(date).toLocaleDateString('en-US', {
      year: 'numeric',
      month: 'short',
      day: 'numeric'
    });
  }

  // Getters for form validation messages
  get f() {
    return this.createForm.controls;
  }

  get uf() {
    return this.updateForm.controls;
  }


  isPastDate(date: any): boolean {
  if (!date) return false;
  const d = new Date(date);
  const today = new Date();
  today.setHours(0, 0, 0, 0);
  return d < today;
}
fetchHolidaysFromCalendar(): void {
    this.spinner.show();
    this.holidayService.getAll().subscribe({
        next: (data) => {
            this.holidays = data;
            this.spinner.hide();
            this.modalService.openSuccessModal('Holidays refreshed successfully!');
        },
        error: (error) => {
            this.spinner.hide();
            console.error('Error fetching holidays from calendar:', error);
            this.modalService.openWarning('Error', 'Failed to refresh holidays from calendar');
        }
    });
}


// Add this to your holidays.component.ts

/**
 * Fetch holidays automatically for the current year from an external API
 * This function retrieves public holidays based on the country code
 */
fetchHolidaysAutomatically(): void {
  this.spinner.show();
  const currentYear = new Date().getFullYear();
  const countryCode = 'TN'; // Change to your country code (TN for Tunisia, FR for France, etc.)

  this.holidayService.getHolidaysByYear(currentYear, countryCode).subscribe({
    next: (data) => {
      // Map external API data to your Holiday model
      const mappedHolidays = data.map((holiday: any) => ({
        name: holiday.name,
        date: new Date(holiday.date),
        description: holiday.description || ''
      }));

      // Save holidays to your backend
      this.saveHolidaysToDatabase(mappedHolidays);
    },
    error: (error) => {
      this.spinner.hide();
      console.error('Error fetching holidays:', error);
      this.modalService.openWarning('Error', 'Failed to fetch holidays automatically');
    }
  });
}

/**
 * Save fetched holidays to database
 */
private saveHolidaysToDatabase(holidays: Holiday[]): void {
  this.holidayService.bulkCreate(holidays).subscribe({
    next: () => {
      this.spinner.hide();
      this.loadHolidays(); // Reload the list
      this.modalService.openSuccessModal(`${holidays.length} holidays added successfully!`);
    },
    error: (error) => {
      this.spinner.hide();
      console.error('Error saving holidays:', error);
      this.modalService.openWarning('Error', 'Failed to save holidays');
    }
  });
}

/**
 * Fetch holidays for multiple years
 */
fetchHolidaysForMultipleYears(startYear: number, endYear: number, countryCode: string = 'TN'): void {
  this.spinner.show();
  const allHolidays: Holiday[] = [];
  let completedRequests = 0;

  for (let year = startYear; year <= endYear; year++) {
    this.holidayService.getHolidaysByYear(year, countryCode).subscribe({
      next: (data) => {
        const mappedHolidays = data.map((holiday: any) => ({
          name: holiday.name,
          date: new Date(holiday.date),
          description: holiday.description || ''
        }));
        allHolidays.push(...mappedHolidays);
        completedRequests++;

        // When all requests are complete, save to database
        if (completedRequests === (endYear - startYear + 1)) {
          this.saveHolidaysToDatabase(allHolidays);
        }
      },
      error: (error) => {
        console.error(`Error fetching holidays for year ${year}:`, error);
        completedRequests++;

        if (completedRequests === (endYear - startYear + 1)) {
          this.spinner.hide();
          this.modalService.openWarning('Partial Error', 'Some years failed to load');
        }
      }
    });
  }
}

/**
 * Auto-sync holidays every time component loads
 */
autoSyncHolidays(): void {
  const currentYear = new Date().getFullYear();
  const countryCode = 'TN';

  // Check if holidays for current year already exist
  this.holidayService.getHolidaysByYear(currentYear, countryCode).subscribe({
    next: (data) => {
      if (!data || data.length === 0) {
        this.fetchHolidaysAutomatically();
      }
    }
  });
}
}
