import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    constructor() {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // Obtener el token del sessionStorage
        const token = sessionStorage.getItem('token');
        console.log(token)
        // Si existe el token, clonar la petición y agregar el header de autorización
        if (token) {
            // Clonar la petición y agregar el header de autorización
            const authReq = request.clone({
              headers: request.headers.set('Authorization', `Bearer ${token}`)
            });
            return next.handle(authReq);
        }
        // Si no hay token, pasar la petición sin modificar
        return next.handle(request);
    }
}
