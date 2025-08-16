import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
interface Transaction {
  id: number;
  transactionId: string;
  status: string;
  message: string;
  items: string;
}

@Component({
  selector: 'app-orders',
  imports: [CommonModule],
  templateUrl: './orders.html',
  styleUrls: ['./orders.css']
})
export class Orders {
  transactionItems: Transaction[] = [];
  loading = true;
  errorMessage = '';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<Transaction[]>('http://localhost:8060/orders/transactions')
      .subscribe({
        next: (data) => {
          this.transactionItems = data;
          this.loading = false;
        },
        error: (err) => {
          console.error('Error fetching transactions:', err);
          this.errorMessage = 'Could not load transactions.';
          this.loading = false;
        }
      });
  }
}
