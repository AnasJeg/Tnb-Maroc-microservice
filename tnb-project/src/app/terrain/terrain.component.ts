import { Component } from '@angular/core';
import {Terrain} from "../core/models/terrain.model";
import {Categorie} from "../core/models/categorie.model";
import {CategorieService} from "../core/service/categorie.service";
import {TerrainService} from "../core/service/terrain.service";
import {map, Observable} from "rxjs";
import {Redevable} from "../core/models/redevable.model";
import {RedevableService} from "../core/service/redevable.service";

@Component({
  selector: 'app-terrain',
  templateUrl: './terrain.component.html',
  styleUrls: ['./terrain.component.css']
})
export class TerrainComponent {
  categories: Categorie[] = [];
  terrains: Terrain[] = [];
  redevable: Redevable[] = [];

  displaySaveDialog : boolean = false;

  openSaveDialog(): void {

    this.displaySaveDialog = true;
  }

  constructor(private terrainService: TerrainService ,private categoriService : CategorieService, private redevableService :RedevableService) {}



  ngOnInit(): void {
    this.loadTerrains();
    this.loadCategories();
    this.loadUsers()
  }

  loadTerrains(): void {
    this.terrainService.findAll().subscribe(
      (data) => {
        if (Array.isArray(data)) {
          this.terrains = data;
        } else {
          console.error('Invalid data format:', data);
        }
      },
      (error) => {
        console.error('Error fetching categories:', error);
      }
    );
  }

  // saveTerrain(): void {
  //   this.terrainService.saveTerrain(this.newTerrain).subscribe(
  //     (savedTerrain) => {
  //       console.log('Terrain saved successfully:', savedTerrain);
  //       this.displaySaveDialog = false;
  //       this.loadTerrains(); // Reload terrains after saving
  //     },
  //     (error) => {
  //       console.error('Error saving terrain:', error);
  //     }
  //   );
  // }

  saveTerrain(): void {
    console.log(this.newTerrain);

    this.terrainService.saveTerrain(this.newTerrain).subscribe(
      (savedTerrain) => {
        console.log('Terrain saved successfully:', savedTerrain);
        this.displaySaveDialog = false;
        this.loadTerrains();
      },
      (error) => {
        console.error('Error saving terrain:', error);
      }
    );
  }


  loadCategories(): void {
    this.categoriService.findAll().subscribe(
      (data) => {
        if (Array.isArray(data)) {
          this.categories = data;
          console.log("cats",data)
        } else {
          console.error('Invalid data format:', data);
        }
      },
      (error) => {
        console.error('Error fetching categories:', error);
      }
    );
  }

 loadUsers(): void {
    this.terrainService.getUsers().subscribe(
      (data ) => {
        if (Array.isArray(data)) {
          this.redevable = data;
          console.log("redevable",data)
        } else {
          console.error('Invalid data format:', data);
        }
      },
      (error) => {
        console.error('Error fetching categories:', error);
      }
    );
  }


  newTerrain: Terrain = {
    id: 0,
    nom: '',
    surface: 0,
    // categorie: {} as Categorie,
    // redevable: {} as Redevable  ,
    categorie: { id: 0 },
    redevable: { id: 0 },
  };

}
