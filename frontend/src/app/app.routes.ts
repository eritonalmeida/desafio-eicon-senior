import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConsultaCreditos } from './consulta-creditos/consulta-creditos';

export const routes: Routes = [{
    path: '',
    title: 'Consulta de créditos NFS-e',
    pathMatch: 'full',
    component: ConsultaCreditos
}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
