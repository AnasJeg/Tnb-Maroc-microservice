import { Component } from '@angular/core';
import {MenuItem} from "primeng/api";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  items: MenuItem[] = [];

  constructor(private router: Router) {}

  navigateTo(route: string) {
    this.router.navigate([route]);
  }


  ngOnInit() {
    this.items = [
      {
        label: 'Terrains',
        icon: 'pi pi-fw pi-slack',
        command: () => this.navigateTo('/terrain'),
      },
      {
        label: 'Tax',
        icon: 'pi pi-fw pi-ticket',
        command: () => this.navigateTo('/tax'),

      },
      {
        label: 'admin dash',
        icon: 'pi pi-fw pi-ticket',
        command: () => this.navigateTo('/home'),

      },

    ];
  }
}
