import { Component, signal } from '@angular/core';

interface product {
  
}
@Component({
  selector: 'app-root',
  imports: [],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('smart-pos-frontend');

}
