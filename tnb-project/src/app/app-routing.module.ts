import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AdminHomePageComponent} from "./admin-home-page/admin-home-page.component";
import {ClientHomePageComponent} from "./client-home-page/client-home-page.component";
import {TerrainComponent} from "./terrain/terrain.component";
import {CategoriesComponent} from "./categories/categories.component";

const routes: Routes = [
  {path: 'admin_home' ,component:AdminHomePageComponent},
  {path: 'terrain' ,component:TerrainComponent},
  {path: 'tax' ,component:ClientHomePageComponent},
  {path: 'categories' ,component:CategoriesComponent},
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
