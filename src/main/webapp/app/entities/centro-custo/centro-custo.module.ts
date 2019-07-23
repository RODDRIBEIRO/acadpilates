import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AcadpilatesSharedModule } from 'app/shared';
import {
  CentroCustoComponent,
  CentroCustoDetailComponent,
  CentroCustoUpdateComponent,
  CentroCustoDeletePopupComponent,
  CentroCustoDeleteDialogComponent,
  centroCustoRoute,
  centroCustoPopupRoute
} from './';

const ENTITY_STATES = [...centroCustoRoute, ...centroCustoPopupRoute];

@NgModule({
  imports: [AcadpilatesSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CentroCustoComponent,
    CentroCustoDetailComponent,
    CentroCustoUpdateComponent,
    CentroCustoDeleteDialogComponent,
    CentroCustoDeletePopupComponent
  ],
  entryComponents: [CentroCustoComponent, CentroCustoUpdateComponent, CentroCustoDeleteDialogComponent, CentroCustoDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AcadpilatesCentroCustoModule {}
