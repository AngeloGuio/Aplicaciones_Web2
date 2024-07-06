import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Usuario } from '../usuario';
import { MaterialModule } from '../../../material/material.module';
import { UsuarioService } from '../usuario.service';

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
  postId: string | null = ''
  postForm!: FormGroup
  formType!: FormType
  formTitulo!: string
  constructor(private router: ActivatedRoute,
    private usuarioService: UsuarioService){
  }

  ngOnInit(): void {
    this.postId = this.router.snapshot.paramMap.get('id');
    this.postForm = this.formulario()
    if(this.postId !== 'nuevo'){
      this.formTitulo = "EDITAR USUARIO"
      this.formType = FormType.Actualizar
      this.cargarPost(Number(this.postId))
    }else{
      this.formTitulo = "NUEVO USUARIO"
      this.formType = FormType.Crear
    }
  }
  formulario():FormGroup{
    return new FormGroup({
      nomusuario: new FormControl({ value: '', disabled: true }),
      email: new FormControl(''),
      password: new FormControl(''),
      nombres: new FormControl(''),
      apellidos: new FormControl(''),
      dni: new FormControl(''),
      telefono: new FormControl(''),
      direccion: new FormControl(''),
    })
  }
  cargarPost(postid:number): void{
    this.usuarioService.getUsuarioById(postid)
    .subscribe(
      (data) => {
        console.log(data)
        const {nomusuario, email, password, nombres, apellidos, dni, telefono, direccion} = data
        this.postForm.setValue({nomusuario, email, password, nombres, apellidos, dni, telefono, direccion})
      })
  }
  guardarPost():void{
    if(this.formType === FormType.Crear){
      this.registrarPost(this.postForm.value)
    }else{
      const postValue = {...this.postForm.value, idusuario: this.postId }
      this.actualizarPost(postValue)
    }
  }
  registrarPost(usuario: Usuario){
    this.usuarioService.createUsuario(usuario)
    .subscribe(
      (data) => {
        console.log(data);
      }
    )
  }
  actualizarPost(usuario: Usuario){
    this.usuarioService.updateUsuario(usuario)
    .subscribe(
      (data) => {
        console.log(data);
      }
    )
  }



}



