import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-admin-home-page',
  templateUrl: './admin-home-page.component.html',
  styleUrls: ['./admin-home-page.component.css']
})
export class AdminHomePageComponent {
  date1: Date | undefined;
  formGroup: FormGroup | undefined;

  displaySaveDialog : boolean = false;

  openSaveDialog(): void {
    this.displaySaveDialog = true;
  }


}
