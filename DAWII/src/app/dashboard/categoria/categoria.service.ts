import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Categoria } from './categoria';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  private urlApi = 'http://localhost:8080/api/v1/tipoproducto';

  constructor(private httpClient: HttpClient) { }

  getAllCategory():Observable<Categoria[]>{
    const token = sessionStorage.getItem('token');
    const headers = token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;

    return this.httpClient.get<Categoria[]>(this.urlApi, { headers });
  }

  getCategoryById(id: number): Observable<Categoria>{
    const token = sessionStorage.getItem('token');
    const headers = token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;

    return this.httpClient.get<Categoria>(this.urlApi+"/"+id, {headers});
  }

  createCategory(categoria: Categoria):Observable<any>{
    const token = sessionStorage.getItem('token');
    const headers = token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;

    return this.httpClient.post<Categoria>(this.urlApi, categoria, { headers });
  }

  updateCategory(categoria: Categoria):Observable<any>{
    const token = sessionStorage.getItem('token');
    const headers = token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;
    console.log(categoria)
    return this.httpClient.put<Categoria>(this.urlApi +"/"+ categoria.idtipopro , categoria, { headers })
  }

}
