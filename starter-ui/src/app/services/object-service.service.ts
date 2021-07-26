import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GenericObject } from '../models/generic-object';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ObjectService {

  private itemsUrl: string;

  constructor(private http: HttpClient) { this.itemsUrl = 'http://localhost:8080/api/objects'; }

  public findAll(): Observable<GenericObject[]> {
    return this.http.get<GenericObject[]>(this.itemsUrl);
  }

  public save(item: GenericObject) {
    return this.http.post<GenericObject>(this.itemsUrl, item);
  }
}
