import { Component, OnInit } from '@angular/core';
import { TerrainService } from '../core/service/terrain.service';
import { Terrain } from '../core/models/terrain.model';
import { AuthService } from '../core/auth/auth.service';
import { Categorie } from '../core/models/categorie.model';
import { CategorieService } from '../core/service/categorie.service';
import { RedevableService } from '../core/service/redevable.service';
import { TaxeService } from '../core/service/taxe.service';

@Component({
  selector: 'app-client-home-page',
  templateUrl: './client-home-page.component.html',
  styleUrls: ['./client-home-page.component.css']
})
export class ClientHomePageComponent implements OnInit {
  userCIN: string | null | undefined;
  terrains: Terrain[] = [];
  filteredTerrains: Terrain[] = [];
  categories: Categorie[] = [];
  isPaidFilter: boolean = false; // Set default value to false
  newTerrain: Terrain = {
    id: 0,
    nom: '',
    surface: 0,
    categorie: { id: 0 },
    redevable: { id: 0 },
  };
  displaySaveDialog: boolean = false;

  constructor(
    private terrainService: TerrainService,
    private auth: AuthService,
    private categorieService: CategorieService,
    private redevableService: RedevableService,
    private taxeService: TaxeService
  ) {}

  ngOnInit(): void {
    this.userCIN = this.auth.getUserCIN();

    if (this.userCIN) {
      this.terrainService.findAllByCin(this.userCIN).subscribe(
        (terrains: Terrain[]) => {
          this.terrains = terrains;
        },
        (error) => {
          console.error('Error fetching terrains:', error);
        }
      );
    }
    this.loadCategories();
    this.loadTerrainsFilt();
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

  loadTerrainsFilt(): void {
    const isPaidFilterString = this.isPaidFilter !== null ? this.isPaidFilter.toString() : 'false';
    
    this.terrainService.getTerrainsByRedevableCin(this.userCIN!, isPaidFilterString).subscribe(
      (terrains: Terrain[]) => {
        this.filteredTerrains = terrains;
      },
      (error) => {
        console.error('Error fetching terrains:', error);
      }
    );
  }
  
  logout(): void {
    this.auth.signOut();
  }
  filterTerrains(isPaid: boolean): void {
    this.isPaidFilter = isPaid;
    this.loadTerrainsFilt();
  }

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
    this.categorieService.findAll().subscribe(
      (data) => {
        if (Array.isArray(data)) {
          this.categories = data;
        } else {
          console.error('Invalid data format:', data);
        }
      },
      (error) => {
        console.error('Error fetching categories:', error);
      }
    );
  }

  openSaveDialog(): void {
    const userCIN = this.auth.getUserCIN();

    if (userCIN) {
      this.redevableService.getByCin(userCIN).subscribe(
        (redevable) => {
          this.newTerrain.redevable = redevable;
          this.displaySaveDialog = true;
        },
        (error) => {
          console.error('Error fetching redevable:', error);
        }
      );
    }
  }
}
