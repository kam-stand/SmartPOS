import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule, DecimalPipe } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
interface Product {
  id: number;
  name: string;
  category: string;
  brand: string;
  description: string;
  price: number;
}

interface Cart {
  purchaseOrderItems: { [productId: number]: number };
}

interface Payment {
  email: string;
  cardNumber: string;
  cardHolderName: string;
  expiryMonth: string;
  expiryYear: string;
  cvv: string;
  amt: number;
}

interface Order {
  cart: Cart;
  payment: Payment;
}

@Component({
  selector: 'app-products',
  imports: [DecimalPipe, FormsModule, CommonModule],
  templateUrl: './products.html',
  styleUrls: ['./products.css']
})
export class Products {
  productList: Product[] = [];
  cart: Map<Product, number> = new Map();

  // Payment form fields
  email = '';
  cardNumber = '';
  cardHolderName = '';
  expiryMonth = '';
  expiryYear = '';
  cvv = '';

  constructor(private http: HttpClient) {}

  productTrackBy(index: number, item: [Product, number]): number {
  return item[0].id; // track by product ID
}

  ngOnInit(): void {
    this.http.get<Product[]>('http://localhost:8060/products')
      .subscribe({
        next: (data) => this.productList = data,
        error: (err) => console.error('Error fetching products:', err)
      });
  }

  // CART LOGIC
  addToCart(product: Product) {
    if (this.cart.has(product)) {
      this.cart.set(product, this.cart.get(product)! + 1);
    } else {
      this.cart.set(product, 1);
    }
  }

  removeFromCart(product: Product) {
    if (this.cart.has(product)) {
      const qty = this.cart.get(product)! - 1;
      if (qty > 0) {
        this.cart.set(product, qty);
      } else {
        this.cart.delete(product);
      }
    }
  }

  get cartItems() {
    return Array.from(this.cart.entries()); // [ [product, quantity], ... ]
  }

  calculateCartTotal(): number {
    let total = 0;
    this.cart.forEach((quantity, product) => {
      total += product.price * quantity;
    });
    return total;
  }

  printCartItems() {
    this.cart.forEach((value, key) => {
      const total = key.price * value;
      console.log(`Name: ${key.name}, Price: ${key.price}, Qty: ${value}, Total: ${total}`);
    });
  }

  // CHECKOUT
  checkout(form: NgForm) {
  if (this.cart.size === 0) {
    alert('Cart is empty!');
    return;
  }

    

    const purchaseOrderItems: { [key: number]: number } = {};
    this.cart.forEach((quantity, product) => {
      purchaseOrderItems[product.id] = quantity;
    });

    const cart: Cart = { purchaseOrderItems };
    const payment: Payment = {
      email: this.email,
      cardNumber: this.cardNumber,
      cardHolderName: this.cardHolderName,
      expiryMonth: this.expiryMonth,
      expiryYear: this.expiryYear,
      cvv: this.cvv,
      amt: this.calculateCartTotal()
    };

    const order: Order = { cart, payment };
    this.http.post('http://localhost:8060/orders', order, { responseType: 'text' })
  .subscribe({
    next: (res) => {
      console.log('Order placed successfully', res);
      alert(res);
      form.reset()
      this.cart.clear();
    },
    error: (err) => console.error('Error placing order', err)
  });

  }
}
