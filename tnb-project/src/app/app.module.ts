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
import {HttpClientModule} from "@angular/common/http";
import {DropdownModule} from "primeng/dropdown";
import {ToastModule} from "primeng/toast";
import {FileUploadModule} from "primeng/fileupload";
import {CardModule} from "primeng/card";
import {DividerModule} from "primeng/divider";
import {ImageModule} from "primeng/image";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {InputSwitchModule} from "primeng/inputswitch";
import {CalendarModule} from "primeng/calendar";
import { CategoriesComponent } from './categories/categories.component';
import { LoginComponent } from './login/login.component';
import {CheckboxModule} from "primeng/checkbox";
import {RippleModule} from "primeng/ripple";
import { RedevableComponent } from './redevable/redevable.component';
import { TaxeComponent } from './taxe/taxe.component';
import { TauxComponent } from './taux/taux.component';


@NgModule({
  declarations: [
    AppComponent,
    AdminHomePageComponent,
    ClientHomePageComponent,
    NavbarComponent,
    TerrainComponent,
    CategoriesComponent,
    LoginComponent,
    RedevableComponent,
    TaxeComponent,
    TauxComponent
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
        BrowserAnimationsModule,
        ButtonModule,
        InputSwitchModule,
        ImageModule,
        DividerModule,
        CardModule,
        HttpClientModule,
        DialogModule,
        MenubarModule,
        AppRoutingModule,

        DropdownModule,
        ToastModule,
        FileUploadModule,
        CalendarModule,
        CheckboxModule,
        RippleModule
    ],
  providers: [MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
