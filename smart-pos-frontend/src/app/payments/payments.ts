import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';

interface Payment {
  id: number;
  paymentDate: string;
  status: string;
  transactionId: string;
  amount: number;
}

@Component({
  selector: 'app-payments',
  standalone: true,          // <-- make it standalone
  imports: [CommonModule, HttpClientModule],  // <-- import CommonModule
  templateUrl: './payments.html',
  styleUrls: ['./payments.css']
})
export class Payments {
  paymentList: Payment[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<Payment[]>('http://localhost:8060/payments')
      .subscribe({
        next: (data) => this.paymentList = data,
        error: (err) => console.error('Error fetching payments:', err)
      });
  }
}
