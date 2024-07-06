import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Venta } from './venta';

@Injectable({
  providedIn: 'root'
})
export class VentaService {

  private urlApi = 'http://localhost:8080/api/v1/venta/detalles';

  constructor(private httpClient: HttpClient) { }

  getAllVentas():Observable<Venta[]>{
    // Obtener el token del sessionStorage
    const token = sessionStorage.getItem('token');
    // Si el token existe, se incluye en los headers de la petición
    const headers = token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;

    return this.httpClient.get<Venta[]>(this.urlApi, {headers});
  }

}
