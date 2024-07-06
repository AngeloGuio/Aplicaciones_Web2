import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, UntypedFormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Usuario } from '../usuario';
import { MaterialModule } from '../../../material/material.module';
import { UsuarioService } from '../usuario.service';
import Notiflix from 'notiflix';

enum FormType{
  Crear = 0,
  Actualizar = 1
}

@Component({
  selector: 'app-usuario-detalle',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, MaterialModule],
  templateUrl: './usuario-detalle.component.html',
  styleUrl: './usuario-detalle.component.css'
})
export class UsuarioDetalleComponent {
  FormType = FormType;
  
  postId: string | null = ''
  postForm!: FormGroup
  formType!: FormType
  formTitulo!: string
  constructor(
    private router: ActivatedRoute,
    private usuarioService: UsuarioService,
    private route: Router
  ){
  }

  ngOnInit(): void {
    this.postId = this.router.snapshot.paramMap.get('id');
    this.postForm = this.formulario();
    if (this.postId !== 'nuevo') {
      this.formTitulo = "EDITAR USUARIO";
      this.formType = FormType.Actualizar;
      this.cargarPost(Number(this.postId));
      this.postForm.get('nomusuario')?.disable(); // Deshabilita el campo para la edición
      this.postForm.removeControl('password'); // Elimina el campo password para la edición
    } else {
      this.formTitulo = "NUEVO USUARIO";
      this.formType = FormType.Crear;
      this.postForm.get('nomusuario')?.enable(); // Habilita el campo para la creación
    }
  }
  
  formulario(): UntypedFormGroup {
    const formGroup = new UntypedFormGroup({
      nomusuario: new FormControl(''),
      email: new FormControl(''),
      nombres: new FormControl(''),
      apellidos: new FormControl(''),
      dni: new FormControl(''),
      telefono: new FormControl(''),
      direccion: new FormControl(''),
    });
  
    // Ahora puedes agregar controles dinámicamente sin errores
    if (this.router.snapshot.paramMap.get('id') === 'nuevo') {
      formGroup.addControl('password', new FormControl(''));
    }
  
    return formGroup;
  }
  
  cargarPost(postid:number): void{
    this.usuarioService.getUsuarioById(postid)
    .subscribe(
      (data) => {
        console.log(data)
        const {nomusuario, email, nombres, apellidos, dni, telefono, direccion} = data
        this.postForm.setValue({nomusuario, email, nombres, apellidos, dni, telefono, direccion})
      })
  }
  guardarPost(): void {
    if (this.formType === FormType.Crear) {
      this.registrarPost();
    } else {
      this.actualizarPost();
    }
  }

  registrarPost(): void {
    if (this.postForm.valid) {
      this.usuarioService.createUsuario(this.postForm.getRawValue()).subscribe(
        data => {
          this.route.navigate(['/usuario']);
          Notiflix.Notify.success(data.message);
        },
        error => {
          console.error('Error al crear usuario:', error);
        }
      );
    }
  }

  actualizarPost(): void {
    if (this.postForm.valid) {
      const usuario: Usuario = { idusuario: this.postId, ...this.postForm.getRawValue() };
      this.usuarioService.updateUsuario(usuario).subscribe(
        data => {
          this.route.navigate(['/usuario']);
          Notiflix.Notify.success(data.message);
        },
        error => {
          console.error('Error al actualizar usuario:', error);
        }
      );
    }
  }



}



