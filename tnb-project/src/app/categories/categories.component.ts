import { Component } from '@angular/core';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent {
  displaySaveDialog : boolean = false;

  openSaveDialog(): void {
    this.displaySaveDialog = true;
  }
}
