import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductoDetalleService {
  private urlApi = 'http://localhost:8080/api/v1/';

  constructor(private httpClient: HttpClient) { }

  getAllProveedor():Observable<any[]>{
     // Obtener el token del sessionStorage
     const token = sessionStorage.getItem('token');
     // Si el token existe, se incluye en los headers de la petición
     const headers = token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;

    return this.httpClient.get<any[]>(this.urlApi+"proveedor",{headers});
  }

  obtenerTipoProducto():Observable<any[]>{
     // Obtener el token del sessionStorage
     const token = sessionStorage.getItem('token');
     // Si el token existe, se incluye en los headers de la petición
     const headers = token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;

    return this.httpClient.get<any[]>(this.urlApi+"tipoproducto",{headers});
  }

  obtenerAnimales():Observable<any[]>{
     // Obtener el token del sessionStorage
     const token = sessionStorage.getItem('token');
     // Si el token existe, se incluye en los headers de la petición
     const headers = token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;

    return this.httpClient.get<any[]>(this.urlApi+"animal",{headers});
  }

  obtenerEstados():Observable<any[]>{
     // Obtener el token del sessionStorage
     const token = sessionStorage.getItem('token');
     // Si el token existe, se incluye en los headers de la petición
     const headers = token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;

    return this.httpClient.get<any[]>(this.urlApi+"estado",{headers});
  }
}
