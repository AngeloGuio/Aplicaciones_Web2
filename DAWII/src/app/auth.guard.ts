import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth/auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  // Si el usuario está autenticado y la ruta es '/login', redirigir a '/'
  if (authService.isLogged() && state.url.startsWith('/login')) {
    router.navigateByUrl('/');
    return false;
  }

  // Si el usuario no está autenticado y la ruta no es '/login', redirigir a 'login'
  if (!authService.isLogged() && !state.url.startsWith('/login')) {
    router.navigate(['login'], { queryParams: { blockedPage: state.url } });
    return false;
  }

  // En cualquier otro caso, permitir la navegación
  return true;
};
