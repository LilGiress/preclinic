import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-invoice',
  standalone: true,
  imports: [],
  templateUrl: './invoice.component.html',
  styleUrl: './invoice.component.css'
})
export class InvoiceComponent {
  invoiceForm: FormGroup;

  constructor(private fb: FormBuilder,
     //private invoiceService: InvoiceService
    ) {
    this.invoiceForm = this.fb.group({
      patient: ['', Validators.required],
      department: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      patientAddress: [''],
      billingAddress: [''],
      invoiceDate: ['', Validators.required],
      dueDate: ['', Validators.required],
      tax: [0, Validators.required],
      discount: [0, Validators.required],
      items: this.fb.array([]),
    });
    this.addItem(); // Initialize with one item
  }

  get items(): FormArray {
    return this.invoiceForm.get('items') as FormArray;
  }

  addItem() {
    this.items.push(
      this.fb.group({
        itemName: ['', Validators.required],
        description: [''],
        unitCost: [0, Validators.required],
        quantity: [1, Validators.required],
        amount: [{ value: 0, disabled: true }],
      })
    );
  }

  removeItem(index: number) {
    this.items.removeAt(index);
  }

  calculateAmount(index: number) {
    const item = this.items.at(index);
    const unitCost = item.get('unitCost')?.value || 0;
    const quantity = item.get('quantity')?.value || 0;
    item.get('amount')?.setValue(unitCost * quantity);
  }

  submit() {
    const invoice = this.invoiceForm.getRawValue();
   /* this.invoiceService.createInvoice(invoice).subscribe((response) => {
      console.log('Invoice created:', response);
    });*/
  }
}
