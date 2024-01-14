import { Injectable } from '@angular/core';
import { DaoService } from '../utils/dao.service';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../auth/auth.service';
import { Redevable } from '../models/redevable.model';

@Injectable({
  providedIn: 'root'
})
export class RedevableService extends DaoService<Redevable> {

  constructor(public override http: HttpClient) {
    super('users/', http);
  }
}
