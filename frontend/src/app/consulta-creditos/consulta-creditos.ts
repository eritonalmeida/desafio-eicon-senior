import { Component } from '@angular/core';
import { CurrencyPipe, DatePipe } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { FormsModule } from '@angular/forms';
import { MatRadioModule, MatRadioChange } from '@angular/material/radio';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatTableModule } from '@angular/material/table';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

export interface CreditoDto {
  numeroCredito: string,
  numeroNfse: string,
  dataConstituicao: string,
  valorIssqn: number,
  tipoCredito: string,
  simplesNacional: string,
  aliquota: number,
  valorFaturado: number,
  valorDeducao: number,
  baseCalculo: number,
}

@Component({
  selector: 'app-consulta-creditos',
  imports: [
    MatCardModule,
    CurrencyPipe,
    DatePipe,
    FormsModule,
    MatRadioModule,
    MatFormFieldModule,
    MatInputModule,
    MatGridListModule,
    MatTableModule
  ],
  templateUrl: './consulta-creditos.html',
  styleUrl: './consulta-creditos.css'
})
export class ConsultaCreditos {
  constructor(private http: HttpClient) { }

  displayedColumns: string[] = [
    'numeroCredito',
    'numeroNfse',
    'dataConstituicao',
    'valorIssqn',
    'tipoCredito',
    'simplesNacional',
    'aliquota',
    'valorFaturado',
    'valorDeducao',
    'baseCalculo'
  ];

  tpBusca: string = 'numeroCredito';

  codigo: string = '';

  dataSource: CreditoDto[] = [];

  handleInput(event: Event) {
    const target = event.target as HTMLInputElement;
    this.codigo = target.value;
    this.buscaCreditos();
  }

  handleRadio($event: MatRadioChange) {
    this.buscaCreditos();
  }

  buscaCreditos() {
    this.dataSource = [];

    if (this.codigo.length < 5) {
      return;
    }

    var url = `${environment.apiUrl}/api/creditos`;

    if (this.tpBusca == 'numeroCredito') {
      this.http.get<CreditoDto>(`${url}/credito/${this.codigo}`).subscribe(response => {
        if (!response) {
          return;
        }

        this.dataSource = [response];
      });
    }

    if (this.tpBusca == 'numeroNfse') {
      this.http.get<CreditoDto[]>(`${url}/${this.codigo}`).subscribe(response => {
        this.dataSource = response;
      });
    }
  }
}
