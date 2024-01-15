import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AdminHomePageComponent} from "./admin-home-page/admin-home-page.component";
import {ClientHomePageComponent} from "./client-home-page/client-home-page.component";
import {TerrainComponent} from "./terrain/terrain.component";
import {CategoriesComponent} from "./categories/categories.component";
import { RedevableComponent } from './redevable/redevable.component';
import { TaxeComponent } from './taxe/taxe.component';
import { TauxComponent } from './taux/taux.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'admin_home', component: AdminHomePageComponent },
  { path: 'terrain', component: TerrainComponent },
  { path: 'tax', component: ClientHomePageComponent },
  { path: 'categories', component: CategoriesComponent },
  { path: 'redevable', component: RedevableComponent },
  { path: 'taxes', component: TaxeComponent },
  { path: 'taux', component: TauxComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
