import { InvoiceItem } from "./invoiceItem";

export interface Invoice {
    id?: number;
    patient: string;
    department: string;
    email: string;
    patientAddress: string;
    billingAddress: string;
    invoiceDate: Date;
    dueDate: Date;
    tax: number;
    discount: number;
    total: number;
    items: InvoiceItem[];
  }