import { Component, Inject } from '@angular/core';
import { MaterialModule } from '../../../material/material.module';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Usuario } from '../usuario';
import { UsuarioService } from '../usuario.service';

@Component({
  selector: 'app-editar-usuario-modal',
  standalone: true,
  imports: [MaterialModule],
  templateUrl: './editar-usuario-modal.component.html',
  styleUrl: './editar-usuario-modal.component.css'
})
export class EditarUsuarioModalComponent {

  constructor(
    public dialogRef: MatDialogRef<EditarUsuarioModalComponent>,
    @Inject(MAT_DIALOG_DATA) public usuario: Usuario, private usuarioService: UsuarioService
  ) {}

  cancelar(): void {
    this.dialogRef.close();
  }

  guardar(): void {
    this.usuarioService.actualizarUsuario(this.usuario.idusuario, this.usuario)
      .subscribe(
        response => {
          console.log('Usuario actualizado:', response);
          this.dialogRef.close(response); // Cierra el diálogo y retorna el usuario actualizado
        },
        error => {
          console.error('Error al actualizar usuario:', error);
          // Aquí puedes manejar el error adecuadamente
        }
      );
  }
}
