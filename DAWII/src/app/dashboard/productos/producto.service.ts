import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Producto } from './producto';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  private urlApi = 'http://localhost:8080/api/v1/producto';

  constructor(private httpClient: HttpClient) { }

  getAllProducts():Observable<Producto[]>{
    return this.httpClient.get<Producto[]>(this.urlApi);
  }

  getProductoById(id: number): Observable<Producto>{
    return this.httpClient.get<Producto>(this.urlApi+"/"+id);
  }

  createProducto(producto: Producto):Observable<Producto>{
    return this.httpClient.post<Producto>(this.urlApi, producto);
  }

  updateProducto(producto: Producto):Observable<Producto>{
    return this.httpClient.put<Producto>(this.urlApi+"/"+producto.idproducto, producto)
  }

}
