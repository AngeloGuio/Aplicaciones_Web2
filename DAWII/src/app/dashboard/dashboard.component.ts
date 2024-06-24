import { Component } from '@angular/core';
import { ActivatedRoute, Router, RouterOutlet } from '@angular/router';
import { AuthService } from '../auth/auth.service';
import { MaterialModule } from '../material/material.module';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterOutlet, MaterialModule, CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})

export class DashboardComponent {

  constructor(private router: Router, 
    private route: ActivatedRoute,
    private authService: AuthService
  ){

  }

  public estaLogeado(): boolean {
    return this.authService.isLogged();
  }

  public navigateToLogin(): void {
    this.router.navigate(['/login']);
  }

  salir(): void{
    this.authService.logout();
  }

  irPortada():void{
    this.router.navigate([""], {relativeTo: this.route})
  }
  irContacto():void{
    this.router.navigate(["contacto"], {relativeTo: this.route})
  }
  irNosotros():void{
    this.router.navigate(["nosotros"], {relativeTo: this.route})
  }
  irModUsuario():void{
    this.router.navigate(["usuario"], {relativeTo: this.route})
  }
}
