import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { AcadpilatesSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';
import { DateParserFormatter } from './util/date-parser-formatter';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { TextMaskModule } from 'angular2-text-mask';

@NgModule({
  imports: [AcadpilatesSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [AcadpilatesSharedCommonModule, AutoCompleteModule, TextMaskModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AcadpilatesSharedModule {
  static forRoot() {
    return {
      ngModule: AcadpilatesSharedModule
    };
  }
}
