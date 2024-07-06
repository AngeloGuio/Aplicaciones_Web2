import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TipopagoService {

  private apiUrl = 'http://localhost:8080/api/v1/tipoPago';

  constructor(private http: HttpClient) {}

  obtenerTiposPago(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}
