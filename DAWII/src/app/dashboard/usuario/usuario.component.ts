import { Component, OnInit, ViewChild } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { MatTableDataSource } from '@angular/material/table';
import { UsuarioService } from './usuario.service';
import { Usuario } from './usuario';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-usuario',
  standalone: true,
  imports: [MaterialModule ],
  templateUrl: './usuario.component.html',
  styleUrl: './usuario.component.css'
})
export class UsuarioComponent implements OnInit {
  displayedColumns: string[] = ['idusuario', 'nomusuario', 'email', 'nombres', 'apellidos', 'actions'];
  dataSource = new MatTableDataSource<Usuario>([]);

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private usuarioService: UsuarioService,  private route: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    this.usuarioService.obtenerUsuarios().subscribe((usuarios: Usuario[]) => {
      this.dataSource.data = usuarios;
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  editar(usuario: Usuario) {
    // Lógica para editar usuario
  }

  eliminar(usuario: Usuario) {
    // Lógica para eliminar usuario
  }

  nuevoUsuario() {
    // Lógica para eliminar usuario
  }


  onNavigatePostDetail(usuarioId: string): void {
    this.router.navigate([usuarioId], {relativeTo: this.route});
  }

  onNavigateCreatePost(): void{
    this.router.navigate(['nuevo'], {relativeTo: this.route});
  }

}