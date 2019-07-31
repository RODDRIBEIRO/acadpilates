import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AcadpilatesSharedModule } from 'app/shared';
import {
  LancamentoComponent,
  LancamentoDeleteDialogComponent,
  LancamentoDeletePopupComponent,
  LancamentoDetailComponent,
  lancamentoPopupRoute,
  lancamentoRoute,
  LancamentoUpdateComponent
} from '.';

const ENTITY_STATES = [...lancamentoRoute, ...lancamentoPopupRoute];

@NgModule({
  imports: [AcadpilatesSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    LancamentoComponent,
    LancamentoDetailComponent,
    LancamentoUpdateComponent,
    LancamentoDeleteDialogComponent,
    LancamentoDeletePopupComponent
  ],
  entryComponents: [LancamentoComponent, LancamentoUpdateComponent, LancamentoDeleteDialogComponent, LancamentoDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AcadpilatesLancamentoModule {}
