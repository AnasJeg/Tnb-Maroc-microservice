import { Component, OnInit } from '@angular/core';
import { Taxe } from '../core/models/taxe.model';
import { TaxeService } from '../core/service/taxe.service';
import { Pagination } from '../core/models/request.model';
import { PaginationValue } from '../core/models/pagination.model';

@Component({
  selector: 'app-taxe',
  templateUrl: './taxe.component.html',
  styleUrls: ['./taxe.component.css']
})
export class TaxeComponent implements OnInit {
  taxes: Taxe[] = [];

  constructor(private taxeService: TaxeService) {}
  displaySaveDialog : boolean = false;

  openSaveDialog(): void {
    this.displaySaveDialog = true;
  }
  ngOnInit(): void {
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

  deleteTaxe(id: number): void {
    this.taxeService.delete_by_id(id).subscribe(
      () => {
        this.refreshData();
      },
      error => {
        console.error('Error deleting taxe:', error);
      }
    );
  }

  updateTaxe(id: number): void {
    console.log('Update taxe with id:', id);
  }

  private refreshData(): void {
    this.taxeService.findAll({
      size: 6,
      page: 0
    }).subscribe(
      (data: PaginationValue<Taxe>) => {
        this.taxes = data.content || [];
      },
      error => {
        console.error('Error fetching taxes after refresh:', error);
      }
    );
  }
}
