
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { NgxSpinnerService } from 'ngx-spinner';
import { ModalService } from '../../services/modal.service';
import { AuthService } from '../../services/auth/auth.service';
import { IUser } from '../../models/user';
import { EmployeService } from '../../services/Employe/employe.service';
declare var $: any;
@Component({
    selector: 'app-employees',
    imports: [CommonModule, ReactiveFormsModule],
    templateUrl: './employees.component.html',
    styleUrl: './employees.component.css'
})
export class EmployeesComponent implements OnInit {
   
   employeeForm!: FormGroup;
  updateForm!: FormGroup;
  employees: IUser[] = [];
  selectedEmployee: IUser | null = null;
  submitted = false;
  updateSubmitted = false;
  message = '';
        
   

      constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private spinner: NgxSpinnerService,
    private modalService: ModalService,
    private employeeService: EmployeService
  ) {
    this.initializeForms();
  }

   ngOnInit(): void {
    this.loadEmployees();
  }



  // Initialize Reactive Forms
  initializeForms(): void {
    this.employeeForm = this.fb.group({
      firstname: ['', [Validators.required, Validators.minLength(2)]],
      lastname: ['', [Validators.required, Validators.minLength(2)]],
      username: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
      enabled: [true],
      roles: [[]]
    }, { validators: this.passwordMatchValidator });

    this.updateForm = this.fb.group({
      id: [''],
      firstname: ['', [Validators.required, Validators.minLength(2)]],
      lastname: ['', [Validators.required, Validators.minLength(2)]],
      username: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      enabled: [true],
      roles: [[]],
                password: [''],
                confirmPassword: [''],
                employeeId: ['', Validators.required],
                joiningDate: ['', Validators.required],
                phone: [''],
                role: [''],
                status: ['active']
    });
  }


   
          // LOAD - Fetch all employees
  loadEmployees(): void {
    this.spinner.show();
    this.employeeService.getAllUsers().subscribe({
      next: (data) => {
        this.employees = data;
        this.spinner.hide();
      },
      error: (error) => {
        this.spinner.hide();
        console.error('Error loading employees:', error);
        this.modalService.openWarning('Error', 'Failed to load employees');
      }
    });
  }


   // CREATE - Add new employee
  createEmployee(): void {
    this.submitted = true;

    if (this.employeeForm.invalid) {
      if (this.employeeForm.hasError('passwordMismatch')) {
        this.modalService.openWarning('Error', 'Passwords do not match');
      }
      return;
    }

    this.spinner.show();
    const newEmployee: IUser = {
      firstname: this.employeeForm.value.firstname,
      lastname: this.employeeForm.value.lastname,
      username: this.employeeForm.value.username,
      email: this.employeeForm.value.email,
      enabled: this.employeeForm.value.enabled,
      roles: this.employeeForm.value.roles
    };

    this.employeeService.createUser(newEmployee).subscribe({
      next: (response) => {
        this.employees.push(response);
        this.resetCreateForm();
        this.closeModal('createModal');
        this.spinner.hide();
        this.modalService.openSuccessModal('Employee created successfully!');
      },
      error: (error) => {
        this.spinner.hide();
        this.message = error.error?.message || 'Error creating employee';
        this.modalService.openWarning('Error', this.message);
        console.error('Error creating employee:', error);
      }
    });
  }


  
  // UPDATE - Edit existing employee
  openUpdateModal(employee: IUser): void {
    this.selectedEmployee = employee;
    this.updateForm.patchValue({
      id: employee.id,
      firstname: employee.firstname,
      lastname: employee.lastname,
      username: employee.username,
      email: employee.email,
      enabled: employee.enabled,
      roles: employee.roles
    });
    this.updateSubmitted = false;
    $('#updateEmployeeModal').modal('show');
  }

  updateEmployee(): void {
    this.updateSubmitted = true;

    if (this.updateForm.invalid) {
      return;
    }

    this.spinner.show();
    const updatedEmployee: IUser = {
      id: this.updateForm.value.id,
      firstname: this.updateForm.value.firstname,
      lastname: this.updateForm.value.lastname,
      username: this.updateForm.value.username,
      email: this.updateForm.value.email,
      enabled: this.updateForm.value.enabled,
      roles: this.updateForm.value.roles
    };

    this.employeeService.updateUser(updatedEmployee.id!, updatedEmployee).subscribe({
      next: () => {
        const index = this.employees.findIndex(e => e.id === updatedEmployee.id);
        if (index > -1) {
          this.employees[index] = updatedEmployee;
        }
        this.resetUpdateForm();
        this.closeModal('updateEmployeeModal');
        this.spinner.hide();
        this.modalService.openSuccessModal('Employee updated successfully!');
      },
      error: (error) => {
        this.spinner.hide();
        this.message = error.error?.message || 'Error updating employee';
        this.modalService.openWarning('Error', this.message);
        console.error('Error updating employee:', error);
      }
    });
  }



  // DELETE - Remove employee
  deleteEmployee(id: number | undefined): void {
    if (!id || !confirm('Are you sure you want to delete this employee?')) {
      return;
    }

    this.spinner.show();
    this.employeeService.deleteUser(id).subscribe({
      next: () => {
        this.employees = this.employees.filter(e => e.id !== id);
        this.closeModal('deleteEmployeeModal');
        this.spinner.hide();
        this.modalService.openSuccessModal('Employee deleted successfully!');
      },
      error: (error) => {
        this.spinner.hide();
        this.message = error.error?.message || 'Error deleting employee';
        this.modalService.openWarning('Error', this.message);
        console.error('Error deleting employee:', error);
      }
    });
  }

  // Utility methods
  resetCreateForm(): void {
    this.employeeForm.reset();
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
    $('#createModal').modal('show');
  }

  openDeleteModal(employee: IUser): void {
    this.selectedEmployee = employee;
    $('#deleteEmployeeModal').modal('show');
  }

  // Getters for form validation
  get f() {
    return this.employeeForm.controls;
  }

  get uf() {
    return this.updateForm.controls;
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

    
}
