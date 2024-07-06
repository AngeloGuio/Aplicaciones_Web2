import { Component } from '@angular/core';
import { MaterialModule } from '../../../material/material.module';
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CategoriaService } from '../categoria.service';
import { Categoria } from '../categoria';
import Notiflix from 'notiflix';

enum FormType{
  Crear = 0,
  Actualizar = 1
}

@Component({
  selector: 'app-categoria-detalle',
  standalone: true,
  imports: [MaterialModule, FormsModule, ReactiveFormsModule],
  templateUrl: './categoria-detalle.component.html',
  styleUrl: './categoria-detalle.component.css'
})
export class CategoriaDetalleComponent {

  postId: string | null = ''
  postForm!: FormGroup
  formType!: FormType
  formTitulo!: string
  constructor(private route : Router, private router: ActivatedRoute, private categoriaService: CategoriaService){

  }

  ngOnInit(): void {
    this.postId = this.router.snapshot.paramMap.get('id');
    this.postForm = this.formulario()
    if(this.postId !== 'nuevo'){
      this.formTitulo = "EDITAR CATEGORIA"
      this.formType = FormType.Actualizar
      this.cargarPost(Number(this.postId))
    }else{
      this.formTitulo = "NUEVA CATEGORIA"
      this.formType = FormType.Crear
    }
  }

  formulario():FormGroup{
    return new FormGroup({
      idtipopro: new FormControl({ value: '', disabled: true }),
      desctipopro: new FormControl('')
    })
  }

  cargarPost(postid:number): void{
    this.categoriaService.getCategoryById(postid)
    .subscribe(
      (data) => {
        const {idtipopro, desctipopro} = data
        this.postForm.setValue({idtipopro, desctipopro})
      })
  }

  guardarPost():void{
    if(this.formType === FormType.Crear){
      this.registrarPost(this.postForm.value)
    }else{
      const postValue = {...this.postForm.value, idtipopro: this.postId }
      this.actualizarPost(postValue)
    }
  }

  registrarPost(categoria: Categoria){
    this.categoriaService.createCategory(categoria)
    .subscribe(
      (data) => {
        this.route.navigate(['/categoria']);
        Notiflix.Notify.success(data.message);
      }
    )
  }

  actualizarPost(categoria: Categoria){
    this.categoriaService.updateCategory(categoria)
    .subscribe(
      (data) => {
        this.route.navigate(['/categoria']);
        Notiflix.Notify.success(data.message);
      }
    )
  }

}
