import { HttpClient, HttpHeaders } from '@angular/common/http';
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
    // Obtener el token del sessionStorage
    const token = sessionStorage.getItem('token');
    // Si el token existe, se incluye en los headers de la petición
    const headers = token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;

    return this.httpClient.get<Producto>(this.urlApi+"/"+id, {headers});
  }

  createProducto(producto: Producto):Observable<Producto>{
    // Obtener el token del sessionStorage
    const token = sessionStorage.getItem('token');
    // Si el token existe, se incluye en los headers de la petición
    const headers = token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;

    return this.httpClient.post<Producto>(this.urlApi, producto, {headers});
  }

  updateProducto(producto: Producto):Observable<Producto>{
    // Obtener el token del sessionStorage
    const token = sessionStorage.getItem('token');
    // Si el token existe, se incluye en los headers de la petición
    const headers = token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;

    return this.httpClient.put<Producto>(this.urlApi+"/"+producto.idproducto, producto, {headers})
  }

}
