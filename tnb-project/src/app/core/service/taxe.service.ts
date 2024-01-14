import { Injectable } from '@angular/core';
import { Taxe } from '../models/taxe.model';
import { DaoService } from '../utils/dao.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class TaxeService extends DaoService<Taxe> {

  constructor(public override http: HttpClient) {
    super('taxe/', http);
  }
}