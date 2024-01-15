import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import { environment } from 'src/environments/environment';
import { AuthService } from '../auth/auth.service';
import { Pagination } from '../models/request.model';
import { PaginationValue } from '../models/pagination.model';
import { Taxe } from '../models/taxe.model';


@Injectable({
    providedIn: 'root'
})
export abstract class DaoService<T> {
    protected API = environment.URL;

    protected constructor(API: string,
                          public http: HttpClient) {
        this.API += API;
    }


    create(d: T): Observable<T> {
        return this.http.post<T>(this.API + 'save', d);
    }

    update(d: T): Observable<T> {
        return this.http.put<T>(this.API + 'update', d);
    }


    delete_by_id(id: number): Observable<T> {
        return this.http.delete<T>(this.API + `delete?id=${id}`);
    }

    getById(id: any): Observable<T> {
        return this.http.get<T>(this.API + `get?id=${id}`);
    }

    getByCin(id: any): Observable<T> {
        return this.http.get<T>(this.API + `by?cin=${id}`);
    }

    findAll(pageable: Pagination): Observable<PaginationValue<T>> {
        return this.http.get<PaginationValue<T>>(this.API , {
            params: {
                'page': pageable.page,
                'size': pageable.size
            }
        });
    }


    getTerrainsByRedevableCin(cin: string, isPaid: boolean): Observable<T[]> {
        return this.http.get<T[]>(`${this.API}/all`, {
          params: {
            'cin': cin,
            'isPaid': isPaid.toString()
          }
        });
      }

      getTaxeTnbsByTerrain(nom: string): Observable<Taxe[]> {
        const url = `${this.API}/byTerrain/${nom}`;
        return this.http.get<Taxe[]>(url);
    }
    
    calculateTaxeTNB(cin: string, terrainNom: string, taxeTnbId: number): Observable<number> {
        const url = `${this.API}/calculate/${cin}/${terrainNom}/${taxeTnbId}`;
        return this.http.get<number>(url);
    }
    
    payTaxeTNB(cin: string, terrainNom: string, taxeTnbId: number): Observable<void> {
        const url = `${this.API}/pay/${cin}/${terrainNom}/${taxeTnbId}`;
        return this.http.get<void>(url);
    }
    
}

