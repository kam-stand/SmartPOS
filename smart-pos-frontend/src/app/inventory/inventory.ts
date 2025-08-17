import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface inventory{
  id : number;
  quantity: number
}

@Component({
  selector: 'app-inventory',
  imports: [],
  templateUrl: './inventory.html',
  styleUrl: './inventory.css'
})
export class Inventory {


  constructor(private http: HttpClient) {}

  inventoryList : inventory[] = [];

  ngOnInit() : void {

    this.http.get<inventory[]>('http://localhost:8060/inventory')
    .subscribe({
      next: (data) => {
        this.inventoryList = data;
        console.log(data.length)
        console.log(this.inventoryList)
      },
      error: (err) => {
        console.error('Error Fetching the inventory items', err);
      }
    })

  }


}
