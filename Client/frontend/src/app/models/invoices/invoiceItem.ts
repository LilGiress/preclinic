export interface InvoiceItem {
    id?: number;
    itemName: string;
    description: string;
    unitCost: number;
    quantity: number;
    amount: number;
  }