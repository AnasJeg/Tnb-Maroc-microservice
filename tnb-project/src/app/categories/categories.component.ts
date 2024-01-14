import { Component } from '@angular/core';
import {Categorie} from "../core/models/categorie.model";
import {CategorieService} from "../core/service/categorie.service";

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent {
  categories: Categorie[] = [];
  displaySaveDialog  = false;
  displayUpdateDialog = false;


  openSaveDialog(): void {
    this.displaySaveDialog = true;
  }


  newCategorie: Categorie = {
    id: 0,
    label: '',
    description :'',
  };
  selectedCategorie: Categorie = { id: 0, label: '',description: '' };


  constructor(private categorieService: CategorieService) {}

  ngOnInit(): void {
    this.loadCategories();
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

  /************************ Save Dialog  ***********************/



  openUpdateDialog(categorie: Categorie): void {
    // Set the selected category for update
    this.selectedCategorie = { ...categorie };
    // Open the update dialog
    this.displayUpdateDialog = true;
  }


  /************************ Save cat ***********************/


  saveCategorie(): void {
    this.categorieService.saveCategory(this.newCategorie).subscribe(
      (savedCategorie) => {
        console.log("saved cat",savedCategorie);
        this.displaySaveDialog = false;
        this.loadCategories();
      },
      (error) => {
        console.error('Error saving cat:', error);
      }
    );
  }

  /************************ Delete cat ***********************/

  daleteCategorie(id: number): void {
    this.categorieService.deleteCategorie(id).subscribe(
      () => {
        this.loadCategories();
      },
      (error) => {
        console.log("id",id);
        console.error('Error deleting cat:', error);
      }
    );
  }

}
