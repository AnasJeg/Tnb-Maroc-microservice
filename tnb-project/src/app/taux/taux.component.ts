import { Component, OnInit } from '@angular/core';
import { DialogService } from 'primeng/dynamicdialog';
import { MessageService } from 'primeng/api';
import { TauxService } from '../core/service/taux.service';
import { CategorieService } from '../core/service/categorie.service';
import { Taux } from '../core/models/taux.model';
import { Categorie } from '../core/models/categorie.model';
import { PaginationValue } from '../core/models/pagination.model';
import { Pagination } from '../core/models/request.model';

@Component({
  selector: 'app-taux',
  templateUrl: './taux.component.html',
  styleUrls: ['./taux.component.css'],
  providers: [DialogService, MessageService]
})
export class TauxComponent implements OnInit {
  tauxs: Taux[] = [];
  displaySaveDialog: boolean = false;
  newTaux: Taux = {} as Taux;
  categories: Categorie[] = [];

  constructor(
    private tauxService: TauxService,
    private categorieService: CategorieService,
    public dialogService: DialogService,
    public messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.loadData();
  }

  loadData(): void {
    this.categorieService.findAll().subscribe(data => this.categories = data || []);
    this.loadTauxs();
  }

  loadTauxs(): void {
    const pagination: Pagination = {
      size: 6,
      page: 0
    };

    this.tauxService.findAll(pagination).subscribe(
      (data: PaginationValue<Taux>) => {
        this.tauxs = data.content || [];
      },
      error => {
        console.error('Error fetching tauxs:', error);
      }
    );
  }

  openSaveDialog(): void {
    this.newTaux = {} as Taux;
    this.displaySaveDialog = true;
  }

  saveTaux(): void {
    this.tauxService.create(this.newTaux).subscribe(
      () => {
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Taux added successfully!' });
        this.displaySaveDialog = false;
        this.loadTauxs(); 
      },
      error => {
        console.error('Error saving taux:', error);
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Failed to add taux!' });
      }
    );
  }
}
