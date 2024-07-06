// productos.component.ts
import { Component, OnInit, ViewChild } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { CommonModule } from '@angular/common';
import { ProductoService } from './producto.service';
import { Producto } from './producto';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-productos',
  standalone: true,
  imports: [MaterialModule, CommonModule],
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css']
})
export class ProductosComponent implements OnInit {
  displayedColumns: string[] = ['idproducto', 'tipoproducto', 'proveedor', 'nombre', 'cantidad', 'preciopublico', 'stockminimo', 'stockmaximo', 'estado', 'animal', 'precioproveedor', 'actions'];
  dataSource = new MatTableDataSource<Producto>([]);

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private productoService: ProductoService,  private route: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    this.productoService.getAllProducts().subscribe((productos: Producto[]) => {
      console.log(productos)
      this.dataSource.data = productos;
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  onNavigatePostDetail(usuarioId: string): void {
    this.router.navigate([usuarioId], {relativeTo: this.route});
  }

  onNavigateCreatePost(): void{
    this.router.navigate(['nuevo'], {relativeTo: this.route});
  }
}
