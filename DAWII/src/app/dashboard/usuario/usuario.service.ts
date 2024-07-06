import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from './usuario';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private urlApi = 'http://localhost:8080/api/v1/usuario';

  constructor(private http: HttpClient) {}

  obtenerUsuarios(): Observable<Usuario[]> {
    // Obtener el token del sessionStorage
    const token = sessionStorage.getItem('token');
    // Si el token existe, se incluye en los headers de la petición
    const headers = token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;

    return this.http.get<Usuario[]>(this.urlApi, { headers });
  }


  getUsuarioById(id: number): Observable<Usuario>{
    // Obtener el token del sessionStorage
    const token = sessionStorage.getItem('token');
    // Si el token existe, se incluye en los headers de la petición
    const headers = token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;

    return this.http.get<Usuario>(this.urlApi+"/"+id, {headers});
  }

  createUsuario(usuario: Usuario):Observable<Usuario>{
    // Obtener el token del sessionStorage
    const token = sessionStorage.getItem('token');
    // Si el token existe, se incluye en los headers de la petición
    const headers = token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;

    return this.http.post<Usuario>(this.urlApi, usuario, { headers });
  }

  updateUsuario(usuario: Usuario):Observable<Usuario>{
    // Obtener el token del sessionStorage
    const token = sessionStorage.getItem('token');
    // Si el token existe, se incluye en los headers de la petición
    const headers = token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;
    console.log(usuario)
    return this.http.put<Usuario>(this.urlApi +"/"+ usuario.idusuario , usuario, { headers })
  }


}
