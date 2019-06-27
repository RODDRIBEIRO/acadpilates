import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { AcadpilatesSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [AcadpilatesSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [AcadpilatesSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AcadpilatesSharedModule {
  static forRoot() {
    return {
      ngModule: AcadpilatesSharedModule
    };
  }
}
