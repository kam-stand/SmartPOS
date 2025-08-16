import { Component, signal } from '@angular/core';
import { Products } from './products/products';
import { Orders } from "./orders/orders";
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-root',
  imports: [Products, Orders, FormsModule],
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
