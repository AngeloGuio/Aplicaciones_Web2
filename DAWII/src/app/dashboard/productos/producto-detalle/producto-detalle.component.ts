import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ProductoService } from '../producto.service';
import { Producto } from '../producto';
import { MaterialModule } from '../../../material/material.module';

enum FormType{
  Crear = 0,
  Actualizar = 1
}

@Component({
  selector: 'app-producto-detalle',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, MaterialModule],
  templateUrl: './producto-detalle.component.html',
  styleUrl: './producto-detalle.component.css'
})
export class ProductoDetalleComponent {
  postId: string | null = ''
  postForm!: FormGroup
  formType!: FormType
  formTitulo!: string
  constructor(private router: ActivatedRoute,
    private productoService: ProductoService){
  }

  ngOnInit(): void {
    this.postId = this.router.snapshot.paramMap.get('id');
    this.postForm = this.formulario()
    if(this.postId !== 'nuevo'){
      this.formTitulo = "EDITAR PRODUCTO"
      this.formType = FormType.Actualizar
      this.cargarPost(Number(this.postId))
    }else{
      this.formTitulo = "NUEVO PRODUCTO"
      this.formType = FormType.Crear
    }
  }
  formulario():FormGroup{
    return new FormGroup({
      idproducto: new FormControl({ value: '', disabled: true }),
      idtipopro: new FormControl(''),
      idproveedor: new FormControl(''),
      nombre: new FormControl(''),
      cantidad: new FormControl(''),
      preciopublico: new FormControl(''),
      stockminimo: new FormControl(''),
      stockmaximo: new FormControl(''),
      codestado: new FormControl(''),
      idanimal: new FormControl(''),
      precioproveedor: new FormControl(''),
    })
  }
  cargarPost(postid:number): void{
    this.productoService.getProductoById(postid)
    .subscribe(
      (data) => {
        const {idproducto, tipoproducto, proveedor, nombre, cantidad, preciopublico, stockminimo, stockmaximo, estado, animal, precioproveedor} = data
        this.postForm.setValue({idproducto, idtipopro:tipoproducto.idtipopro, idproveedor:proveedor.idproveedor, nombre, cantidad, preciopublico, stockminimo, stockmaximo, codestado:estado.codestado, idanimal: animal.idanimal, precioproveedor})
      })
  }
  guardarPost():void{
    if(this.formType === FormType.Crear){
      this.registrarPost(this.postForm.value)
    }else{
      const postValue = {...this.postForm.value, id: this.postId }
      this.actualizarPost(postValue)
    }
  }
  registrarPost(producto: Producto){
    this.productoService.createProducto(producto)
    .subscribe(
      (data) => {
        console.log(data);
      }
    )
  }
  actualizarPost(producto: Producto){
    this.productoService.updateProducto(producto)
    .subscribe(
      (data) => {
        console.log(data);
      }
    )
  }
}
