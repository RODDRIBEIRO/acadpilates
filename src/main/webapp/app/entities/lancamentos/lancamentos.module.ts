import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AcadpilatesSharedModule } from 'app/shared';
import {
  LancamentosComponent,
  LancamentosDetailComponent,
  LancamentosUpdateComponent,
  LancamentosDeletePopupComponent,
  LancamentosDeleteDialogComponent,
  lancamentosRoute,
  lancamentosPopupRoute
} from './';

const ENTITY_STATES = [...lancamentosRoute, ...lancamentosPopupRoute];

@NgModule({
  imports: [AcadpilatesSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    LancamentosComponent,
    LancamentosDetailComponent,
    LancamentosUpdateComponent,
    LancamentosDeleteDialogComponent,
    LancamentosDeletePopupComponent
  ],
  entryComponents: [LancamentosComponent, LancamentosUpdateComponent, LancamentosDeleteDialogComponent, LancamentosDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AcadpilatesLancamentosModule {}
