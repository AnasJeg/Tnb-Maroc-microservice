import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Terrain} from "../models/terrain.model";
import {map, Observable} from "rxjs";
import {Categorie} from "../models/categorie.model";
import {Redevable} from "../models/redevable.model";

@Injectable({
  providedIn: 'root'
})
export class TerrainService {
  apiUrlusers = "http://localhost:8090/api/users";
  apiUrlterrain = "http://localhost:8090/api/terrain";
  apiUrlC = "http://localhost:8090/api/categorie";

  constructor(private http: HttpClient) {
  }



  findAll(): Observable<Terrain[]> {
    return this.http.get<any>(`${this.apiUrlterrain}/`).pipe(
      map(response => response.content as Terrain[])
    );
  }


  findAllByCin(cin: string): Observable<Terrain[]> {
    return this.http.get<Terrain[]>(`${this.apiUrlterrain}/redevable`, { params: { cin } });
  }

  getTerrainsByRedevableCin(cin: string, isPaid: string): Observable<Terrain[]> {
    return this.http.get<Terrain[]>(`${this.apiUrlterrain}/redevable`, { params: { cin, isPaid } });
  }
  
  getCategories(): Observable<Categorie[]> {
    return this.http.get<any>(`${this.apiUrlC}/`).pipe(
      map(response => response.content as Categorie[])
    );
  }


  getUsers(): Observable<Redevable[]> {
    return this.http.get<any>(`${this.apiUrlusers}/`).pipe(
      map(response => response.content as Redevable[])
    );
  }


  saveTerrain(terrain: Terrain): Observable<Terrain> {
    return this.http.post<Terrain>(`${this.apiUrlterrain}/save`, terrain);
  }

  deleteTerrain(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrlterrain}/delete/${id}`);
  }




}
