import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Categorie} from "../models/categorie.model";

@Injectable({
  providedIn: 'root'
})
export class CategorieService {

  apiUrl="http://localhost:8090/api/categorie";


  constructor(private http:HttpClient) { }



  findAll(): Observable<Categorie[]> {
    return this.http.get<any>(`${this.apiUrl}/`).pipe(
      map(response => response.content as Categorie[])
    );
  }




  saveCategory(categorie: Categorie): Observable<Categorie> {
    return this.http.post<Categorie>(`${this.apiUrl}/save`, categorie);
  }



  deleteCategorie(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }

  updateCategorie(id: number, updatedCategorie: { label: string }): Observable<Categorie> {
    return this.http.put<Categorie>(`${this.apiUrl}/id/${id}`, updatedCategorie);
  }
}
