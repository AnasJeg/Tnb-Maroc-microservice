import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminHomePageComponent } from './admin-home-page/admin-home-page.component';
import { ClientHomePageComponent } from './client-home-page/client-home-page.component';
import { NavbarComponent } from './navbar/navbar.component';
import {MenubarModule} from "primeng/menubar";
import { TerrainComponent } from './terrain/terrain.component';
import {TableModule} from "primeng/table";
import {TagModule} from "primeng/tag";
import {ButtonModule} from "primeng/button";
import {InputTextModule} from "primeng/inputtext";
import {DialogModule} from "primeng/dialog";
import {FormsModule} from "@angular/forms";
import {MessageService} from "primeng/api";

@NgModule({
  declarations: [
    AppComponent,
    AdminHomePageComponent,
    ClientHomePageComponent,
    NavbarComponent,
    TerrainComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    MenubarModule,
    TableModule,
    TagModule,
    ButtonModule,
    InputTextModule,
    DialogModule
  ],
  providers: [MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
