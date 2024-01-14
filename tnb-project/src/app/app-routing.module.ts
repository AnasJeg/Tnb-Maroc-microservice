import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AdminHomePageComponent} from "./admin-home-page/admin-home-page.component";
import {ClientHomePageComponent} from "./client-home-page/client-home-page.component";
import {TerrainComponent} from "./terrain/terrain.component";

const routes: Routes = [
  {path: 'home' ,component:AdminHomePageComponent},
  {path: 'terrain' ,component:TerrainComponent},
  {path: 'tax' ,component:ClientHomePageComponent},
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
