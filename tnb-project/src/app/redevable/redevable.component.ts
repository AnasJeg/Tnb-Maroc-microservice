import { Component, OnInit } from '@angular/core';
import { PaginationValue } from '../core/models/pagination.model';
import { Redevable } from '../core/models/redevable.model';
import { RedevableService } from '../core/service/redevable.service';
import { Pagination } from '../core/models/request.model';


@Component({
  selector: 'app-redevable',
  templateUrl: './redevable.component.html',
  styleUrls: ['./redevable.component.css']
})
export class RedevableComponent implements OnInit {
  redevables: Redevable[] = [];

  constructor(private redevableService: RedevableService) {}


  ngOnInit(): void {
    const pagination: Pagination = {
      size: 6,
      page: 0 
    };
  
    this.redevableService.findAll(pagination).subscribe(
      (data: PaginationValue<Redevable>) => {
        this.redevables = data.content || [];  
        console.log("reeeed====>",this.redevables)
      },
      error => {
        console.error('Error fetching redevables:', error);
      }
    );
  }
  

  deleteRedevable(id: number): void {
    
    
    this.redevableService.delete_by_id(id).subscribe(
      () => {
        
        this.refreshData();
      },
      error => {
        console.error('Error deleting redevable:', error);
      }
    );
  }

  updateRedevable(id: number): void {
    
    
    console.log('Update redevable with id:', id);
  }

  private refreshData(): void {
    
    this.redevableService.findAll({
      size: 6,
      page: 0
    }).subscribe(
      (data: PaginationValue<Redevable>) => {
        this.redevables = data.content || [];  
      },
      error => {
        console.error('Error fetching redevables after refresh:', error);
      }
    );
  }
  
}