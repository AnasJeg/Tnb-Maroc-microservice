import { Component, OnInit } from '@angular/core';
import { DialogService } from 'primeng/dynamicdialog';
import { MessageService } from 'primeng/api';
import { RedevableService } from '../core/service/redevable.service';
import { TerrainService } from '../core/service/terrain.service';
import { TauxService } from '../core/service/taux.service';
import { PaginationValue } from '../core/models/pagination.model';
import { Taxe } from '../core/models/taxe.model';
import { Redevable } from '../core/models/redevable.model';
import { Terrain } from '../core/models/terrain.model';
import { Taux } from '../core/models/taux.model';
import { TaxeService } from '../core/service/taxe.service';
import { Pagination } from '../core/models/request.model';

@Component({
  selector: 'app-taxe',
  templateUrl: './taxe.component.html',
  styleUrls: ['./taxe.component.css'],
  providers: [DialogService, MessageService]
})
export class TaxeComponent implements OnInit {
  taxes: Taxe[] = [];
  displaySaveDialog: boolean = false;
  newTaxe: Taxe = {} as Taxe;
  redevables: Redevable[] = [];
  terrains: Terrain[] = [];
  tauxs: Taux[] = [];

  constructor(
    private taxeService: TaxeService,
    private redevableService: RedevableService,
    private terrainService: TerrainService,
    private tauxService: TauxService,
    public dialogService: DialogService,
    public messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.loadData();
  }

  loadData(): void {
    
    this.redevableService.findAll({ size: 10, page: 0 }).subscribe(data => this.redevables = data.content || []);
    this.terrainService.findAll().subscribe(data => this.terrains = data || []);
    this.tauxService.findAll({ size: 10, page: 0 }).subscribe(data => this.tauxs = data.content || []);

    
    
    this.loadTaxes();
  }

  loadTaxes(): void {
    const pagination: Pagination = {
      size: 6,
      page: 0
    };

    this.taxeService.findAll(pagination).subscribe(
      (data: PaginationValue<Taxe>) => {
        this.taxes = data.content || [];
      },
      error => {
        console.error('Error fetching taxes:', error);
      }
    );
  }

  openSaveDialog(): void {
    if (this.redevables.length > 0 && this.terrains.length > 0 && this.tauxs.length > 0) {
      this.newTaxe = {} as Taxe;
      this.newTaxe.taux = this.tauxs[0];
      this.displaySaveDialog = true;
      console.log(this.redevables);
      console.log(this.terrains);
      console.log(this.tauxs);
  } else {
      console.error('Error opening save dialog: Data not loaded');
  }
  }

  saveTaxe(): void {  
    this.taxeService.create(this.newTaxe).subscribe(
      () => {
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Taxe added successfully!' });
        this.displaySaveDialog = false;
        this.loadTaxes(); 
      },
      error => {
        console.error('Error saving taxe:', error);
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Failed to add taxe!' });
      }
    );
  }
}
