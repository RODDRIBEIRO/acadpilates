import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AcadpilatesSharedModule } from 'app/shared';
import {
  EstadoComponent,
  EstadoDetailComponent,
  EstadoUpdateComponent,
  EstadoDeletePopupComponent,
  EstadoDeleteDialogComponent,
  estadoRoute,
  estadoPopupRoute
} from './';

const ENTITY_STATES = [...estadoRoute, ...estadoPopupRoute];

@NgModule({
  imports: [AcadpilatesSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [EstadoComponent, EstadoDetailComponent, EstadoUpdateComponent, EstadoDeleteDialogComponent, EstadoDeletePopupComponent],
  entryComponents: [EstadoComponent, EstadoUpdateComponent, EstadoDeleteDialogComponent, EstadoDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AcadpilatesEstadoModule {}
