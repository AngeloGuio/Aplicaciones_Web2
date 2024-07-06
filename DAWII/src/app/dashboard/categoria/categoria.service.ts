import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Categoria } from './categoria';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  private urlApi = 'http://localhost:8080/api/v1/category';

  constructor(private httpClient: HttpClient) { }

  getAllCategory():Observable<Categoria[]>{
    // Obtener el token del sessionStorage
    const token = sessionStorage.getItem('token');
    // Si el token existe, se incluye en los headers de la petición
    const headers = token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;

    return this.httpClient.get<Categoria[]>(this.urlApi, { headers });
  }
}
