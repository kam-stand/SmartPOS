import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment.development';
import { DecimalPipe } from '@angular/common';
interface product {
    id: number ;
    name: string ;
    category: string ;
    brand: string ;
    description: string ;
    price: number ;
}

interface cart {
  product_id: Number;
  quantity: Number;
}

@Component({
  selector: 'app-products',
  imports: [DecimalPipe],
  templateUrl: './products.html',
  styleUrl: './products.css'
})
export class Products {
$product: any;

  constructor(private http: HttpClient){}

  productList: product[] = []
  
    ngOnInit(): void {
    this.http.get<product[]>('http://localhost:8060/products')
      .subscribe({
        next: (data) => {
          this.productList = data;
        },
        error: (err) => {
          console.error('Error fetching products:', err);
        }
      });

  }


  cart: Map<product, number> = new Map();

  addToCart(product: product) {
    if (this.cart.has(product)) {
      this.cart.set(product, this.cart.get(product)! + 1);
    } else {
      this.cart.set(product, 1);
    }
  }


removeFromCart(product: product) {
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
  return Array.from(this.cart.entries()); // [ [product_id, quantity], ... ]
}

printCartItems = () =>{
  this.cart.forEach((value, key) => {
      const total:Number = key.price * value;
      console.log(`Name: ${key.name}, Per: ${key.price} Qty: ${value}, Total: ${total}`);
  })
}


  calculateCartTotal(): number {
    let total = 0; //
    this.cart.forEach((quantity, product) => { //
      total += product.price * quantity; //
    });
    return total; //
  }

    checkout() {
    alert('Proceeding to checkout!');
    // In a real application, you would navigate to a checkout page,
    // send cart data to a backend, etc.
  }
  

}
