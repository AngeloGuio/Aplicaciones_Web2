import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RegistroVentaDto } from './RegistroVentaDto';

@Injectable({
  providedIn: 'root'
})
export class FacturacionService {
  private apiUrl = 'http://localhost:8080/api/v1/venta';

  constructor(private http: HttpClient) {}

  registrarVenta(registroVentaDto: RegistroVentaDto): Observable<any> {
    // Obtener el token del sessionStorage
    const token = sessionStorage.getItem('token');
    // Si el token existe, se incluye en los headers de la petición
    const headers = token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;

    console.log(registroVentaDto)
    return this.http.post<any>(`${this.apiUrl}/registrar`, registroVentaDto, {headers});
  }
}
