import { Component, signal } from '@angular/core';
import { Products } from './products/products';
import { Orders } from "./orders/orders";
import { FormsModule } from '@angular/forms';
import { Inventory } from "./inventory/inventory";
import { Payments } from './payments/payments';


@Component({
  selector: 'app-root',
  imports: [Products, Orders, FormsModule, Inventory, Payments],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('smart-pos-frontend');

  view:string = "default";

  changeView(option:string){
    
    this.view = option;
  console.log("the view is: " + this.view)
    
  }

}
