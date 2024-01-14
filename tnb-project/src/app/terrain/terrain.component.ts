import { Component } from '@angular/core';

@Component({
  selector: 'app-terrain',
  templateUrl: './terrain.component.html',
  styleUrls: ['./terrain.component.css']
})
export class TerrainComponent {


  displaySaveDialog : boolean = false;

  openSaveDialog(): void {
    this.displaySaveDialog = true;
  }

}
