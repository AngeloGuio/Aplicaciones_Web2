// productos.component.ts
import { Component, OnInit, ViewChild } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { CommonModule } from '@angular/common';
import { VentaService } from './venta.service';
import { Venta } from './venta';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-venta',
  standalone: true,
  imports: [MaterialModule, CommonModule],
  templateUrl: './venta.component.html',
  styleUrls: ['./venta.component.css']
})
export class VentaComponent implements OnInit {
  displayedColumns: string[] = ['iddetalle', 'idventa', 'idproducto', 'cantidad', 'precio', 'nombreProducto', 'descripcionTipoProducto', 'nombreProveedor'];
  dataSource = new MatTableDataSource<Venta>([]);

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private ventaService: VentaService) {}

  ngOnInit() {
    this.ventaService.getAllVentas().subscribe((ventas: Venta[]) => {
      console.log(ventas)
      this.dataSource.data = ventas;
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

}
