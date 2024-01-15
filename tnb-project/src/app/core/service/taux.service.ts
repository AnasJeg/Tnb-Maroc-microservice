import { Injectable } from '@angular/core';
import { DaoService } from '../utils/dao.service';
import { HttpClient } from '@angular/common/http';
import { Taux } from '../models/taux.model';

@Injectable({
  providedIn: 'root'
})
export class TauxService  extends DaoService<Taux> {

  constructor(public override http: HttpClient) {
    super('taux/', http);
  }
}
