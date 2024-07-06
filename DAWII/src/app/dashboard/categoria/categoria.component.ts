import { Component, OnInit, ViewChild } from '@angular/core';
import { Categoria } from './categoria';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { CategoriaService } from './categoria.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MaterialModule } from '../../material/material.module';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-categoria',
  standalone: true,
  imports: [MaterialModule, CommonModule],
  templateUrl: './categoria.component.html',
  styleUrl: './categoria.component.css'
})
export class CategoriaComponent implements OnInit{

  displayedColumns: string[] = ['idtipopro', 'desctipopro', 'actions'];
  dataSource = new MatTableDataSource<Categoria>([]);

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private categoriaService: CategoriaService,  private route: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    this.categoriaService.getAllCategory().subscribe((categoria: Categoria[]) => {
      console.log(categoria)
      this.dataSource.data = categoria;
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
