import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { Router } from '@angular/router';
import { AuthService } from '../core/auth/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  items: MenuItem[] = [];

  constructor(private router: Router, private authService: AuthService) {}

  navigateTo(route: string) {
    this.router.navigate([route]);
  }

  logout() {
    this.authService.signOut();
  }


  ngOnInit() {

    const userRole = this.authService.getRole();

    if (userRole === 'ADMIN') {
         this.items = [
      {
        label: 'admin_Home',
        icon: 'pi pi-fw pi-slack',
        command: () => this.navigateTo('/admin_home'),

      },
      {
        label: 'Terrains',
        icon: 'pi pi-fw pi-slack',
        command: () => this.navigateTo('/terrain'),
      },
      {
        label: 'Categories',
        icon: 'pi pi-fw pi-slack',
        command: () => this.navigateTo('/categories'),
      },
      // {
      //   label: 'Client Taxes',
      //   icon: 'pi pi-fw pi-ticket',
      //   command: () => this.navigateTo('/tax'),

      // },
      {
        label: 'Clients',
        icon: 'pi pi-fw pi-ticket',
        command: () => this.navigateTo('/redevable'),

      },
      {
        label: 'Taxes',
        icon: 'pi pi-fw pi-ticket',
        command: () => this.navigateTo('/taxes'),
      },
      {
        label: 'Taux',
        icon: 'pi pi-fw pi-ticket',
        command: () => this.navigateTo('/taux'),
      },
      {
        label: 'Logout',
        icon: 'pi pi-fw pi-sign-out',
        command: () => this.logout(),
      },

    ];
    } else if (userRole === 'USER') {
      this.items = [];
    } else {
      console.error('Unknown role:', userRole);
    }
  }
}




  // ngOnInit() {
  //   this.items = [
  //     {
  //       label: 'admin_Home',
  //       icon: 'pi pi-fw pi-slack',
  //       command: () => this.navigateTo('/admin_home'),

  //     },
  //     {
  //       label: 'Terrains',
  //       icon: 'pi pi-fw pi-slack',
  //       command: () => this.navigateTo('/terrain'),
  //     },
  //     {
  //       label: 'Categories',
  //       icon: 'pi pi-fw pi-slack',
  //       command: () => this.navigateTo('/categories'),
  //     },
  //     {
  //       label: 'Client Taxes',
  //       icon: 'pi pi-fw pi-ticket',
  //       command: () => this.navigateTo('/tax'),

  //     },
  //     {
  //       label: 'Clients',
  //       icon: 'pi pi-fw pi-ticket',
  //       command: () => this.navigateTo('/redevable'),

  //     },
  //     {
  //       label: 'Taxes',
  //       icon: 'pi pi-fw pi-ticket',
  //       command: () => this.navigateTo('/taxes'),
  //     },
  //     {
  //       label: 'Taux',
  //       icon: 'pi pi-fw pi-ticket',
  //       command: () => this.navigateTo('/taux'),
  //     },

  //   ];
  // }