import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AcadpilatesSharedModule } from 'app/shared';
import {
  DocumentoComponent,
  DocumentoDetailComponent,
  DocumentoUpdateComponent,
  DocumentoDeletePopupComponent,
  DocumentoDeleteDialogComponent,
  documentoRoute,
  documentoPopupRoute
} from './';

const ENTITY_STATES = [...documentoRoute, ...documentoPopupRoute];

@NgModule({
  imports: [AcadpilatesSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    DocumentoComponent,
    DocumentoDetailComponent,
    DocumentoUpdateComponent,
    DocumentoDeleteDialogComponent,
    DocumentoDeletePopupComponent
  ],
  entryComponents: [DocumentoComponent, DocumentoUpdateComponent, DocumentoDeleteDialogComponent, DocumentoDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AcadpilatesDocumentoModule {}
