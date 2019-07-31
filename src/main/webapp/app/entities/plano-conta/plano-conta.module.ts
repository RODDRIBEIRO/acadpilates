import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AcadpilatesSharedModule } from 'app/shared';
import {
  PlanoContaComponent,
  PlanoContaDetailComponent,
  PlanoContaUpdateComponent,
  PlanoContaDeletePopupComponent,
  PlanoContaDeleteDialogComponent,
  planoContaRoute,
  planoContaPopupRoute
} from './';

const ENTITY_STATES = [...planoContaRoute, ...planoContaPopupRoute];

@NgModule({
  imports: [AcadpilatesSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    PlanoContaComponent,
    PlanoContaDetailComponent,
    PlanoContaUpdateComponent,
    PlanoContaDeleteDialogComponent,
    PlanoContaDeletePopupComponent
  ],
  entryComponents: [PlanoContaComponent, PlanoContaUpdateComponent, PlanoContaDeleteDialogComponent, PlanoContaDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AcadpilatesPlanoContaModule {}
