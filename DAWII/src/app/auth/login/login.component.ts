import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { MaterialModule } from '../../material/material.module';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [MaterialModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  constructor(
    private router: Router,
    private authService: AuthService
  ) { }
  
  navigateToHome(event: Event): void {
    event.preventDefault(); // Previene la recarga de la página
    this.router.navigate(['/']); // Navega a la ruta principal
  }
  
  submit(usuario: HTMLInputElement, password: HTMLInputElement): void {
    this.authService.login(usuario.value, password.value).subscribe({
      next: (response) => {
        console.log(response)
        // Guarda el token y otros datos necesarios en sessionStorage o localStorage
        sessionStorage.setItem('token', response.token);
        sessionStorage.setItem('isLogged', 'true');
        sessionStorage.setItem('usuario', response.nomusuario);
        // Redirige al usuario a la página principal o dashboard
        this.router.navigateByUrl('/');
      },
      error: (err) => {
        // Maneja el error de autenticación aquí
        alert(err.error.message);
      }
    });
  }

}
