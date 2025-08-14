import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment.development';
interface product {
    id: number ;
    name: string ;
    category: string ;
    brand: string ;
    description: string ;
    price: string ;
}

@Component({
  selector: 'app-products',
  imports: [],
  templateUrl: './products.html',
  styleUrl: './products.css'
})
export class Products {
$product: any;

  constructor(private http: HttpClient){}

  productList: product[] = []

    ngOnInit(): void {
    this.http.get<product[]>(environment.PRODUCT_API)
      .subscribe({
        next: (data) => {
          this.productList = data;
        },
        error: (err) => {
          console.error('Error fetching products:', err);
        }
      });

    console.log(this.productList)
  }

  
}
