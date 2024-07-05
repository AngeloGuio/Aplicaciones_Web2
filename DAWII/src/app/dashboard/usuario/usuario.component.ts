import { Component, OnInit, ViewChild } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { MatTableDataSource } from '@angular/material/table';
import { UsuarioService } from './usuario.service';
import { Usuario } from './usuario';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { EditarUsuarioModalComponent } from './editar-usuario-modal/editar-usuario-modal.component';
import { MatDialog } from '@angular/material/dialog';

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

  constructor(private usuarioService: UsuarioService, public dialog: MatDialog) {}

  ngOnInit() {
    this.usuarioService.obtenerUsuarios().subscribe((usuarios: Usuario[]) => {
      this.dataSource.data = usuarios;
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  editar(usuario: Usuario) {
    const dialogRef = this.dialog.open(EditarUsuarioModalComponent, {
      width: '500px',
      data: { ...usuario } // Pasa una copia del usuario para editar
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        // Actualizar el usuario en la lista local (opcional)
        const index = this.dataSource.data.findIndex(u => u.idusuario === result.idusuario);
        if (index !== -1) {
          this.dataSource.data[index] = result;
          this.dataSource.data = [...this.dataSource.data]; // Actualiza la referencia para que Angular detecte cambios
        }

        // Puedes recargar la lista si lo deseas
        // this.cargarUsuarios();
      }
    })};

  eliminar(usuario: Usuario) {
    // Lógica para eliminar usuario
  }

  nuevoUsuario() {
    // Lógica para eliminar usuario
  }
}