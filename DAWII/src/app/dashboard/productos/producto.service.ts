import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Producto } from './producto';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  constructor(private httpClient: HttpClient) { }

  getAllProducts():Observable<Producto[]>{
    return this.httpClient.get<Producto[]>("http://localhost:8080/api/v1/producto");
  }
}
