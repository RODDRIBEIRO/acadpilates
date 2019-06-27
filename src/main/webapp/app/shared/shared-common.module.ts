import { NgModule } from '@angular/core';

import { AcadpilatesSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
  imports: [AcadpilatesSharedLibsModule],
  declarations: [JhiAlertComponent, JhiAlertErrorComponent],
  exports: [AcadpilatesSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class AcadpilatesSharedCommonModule {}
