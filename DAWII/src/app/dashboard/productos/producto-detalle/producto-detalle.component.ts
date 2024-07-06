import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductoService } from '../producto.service';
import { Producto } from '../producto';
import { MaterialModule } from '../../../material/material.module';
import { ProductoDetalleService } from './producto-detalle.service';
import Notiflix from 'notiflix';

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
  proveedores: any[] = [];
  tiposProducto: any[] = [];
  animales: any[] = [];
  estados: any[] = [];

  postId: string | null = ''
  postForm!: FormGroup
  formType!: FormType
  formTitulo!: string
  constructor(private router: ActivatedRoute,
    private productoService: ProductoService,
    private productoDetalleService: ProductoDetalleService,
    private route: Router
  ){
  }

  ngOnInit(): void {
    this.cargarProveedor();
    this.cargarTipoProducto();
    this.cargarAnimal();
    this.cargarEstado();
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

  cargarProveedor(): void {
    this.productoDetalleService.getAllProveedor().subscribe(
      (data) => {
        console.log(data)
        this.proveedores = data;
      },
      (error) => {
        console.error('Error al obtener los proveedores:', error);
      }
    );
  }

  cargarTipoProducto(): void {
    this.productoDetalleService.obtenerTipoProducto().subscribe(
      (data) => {
        console.log(data)
        this.tiposProducto = data;
      },
      (error) => {
        console.error('Error al obtener los proveedores:', error);
      }
    );
  }

  cargarAnimal(): void {
    this.productoDetalleService.obtenerAnimales().subscribe(
      (data) => {
        console.log(data)
        this.animales = data;
      },
      (error) => {
        console.error('Error al obtener los proveedores:', error);
      }
    );
  }

  cargarEstado(): void {
    this.productoDetalleService.obtenerEstados().subscribe(
      (data) => {
        console.log(data)
        this.estados = data;
      },
      (error) => {
        console.error('Error al obtener los proveedores:', error);
      }
    );
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
      const postValue = {...this.postForm.value, idproducto: this.postId }
      this.actualizarPost(postValue)
    }
  }
  registrarPost(producto: Producto){
    this.productoService.createProducto(producto)
    .subscribe(
      (data) => {
        this.route.navigate(['/producto']);
        Notiflix.Notify.success(data.message);
      }
    )
  }
  actualizarPost(producto: Producto){
    this.productoService.updateProducto(producto)
    .subscribe(
      (data) => {
        this.route.navigate(['/producto']);
        Notiflix.Notify.success(data.message);
      }
    )
  }
}
