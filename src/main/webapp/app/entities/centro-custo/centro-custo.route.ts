import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CentroCusto } from 'app/shared/model/centro-custo.model';
import { CentroCustoService } from './centro-custo.service';
import { CentroCustoComponent } from './centro-custo.component';
import { CentroCustoDetailComponent } from './centro-custo-detail.component';
import { CentroCustoUpdateComponent } from './centro-custo-update.component';
import { CentroCustoDeletePopupComponent } from './centro-custo-delete-dialog.component';
import { ICentroCusto } from 'app/shared/model/centro-custo.model';

@Injectable({ providedIn: 'root' })
export class CentroCustoResolve implements Resolve<ICentroCusto> {
  constructor(private service: CentroCustoService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICentroCusto> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CentroCusto>) => response.ok),
        map((centroCusto: HttpResponse<CentroCusto>) => centroCusto.body)
      );
    }
    return of(new CentroCusto());
  }
}

export const centroCustoRoute: Routes = [
  {
    path: '',
    component: CentroCustoComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'CentroCustos'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CentroCustoDetailComponent,
    resolve: {
      centroCusto: CentroCustoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'CentroCustos'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CentroCustoUpdateComponent,
    resolve: {
      centroCusto: CentroCustoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'CentroCustos'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CentroCustoUpdateComponent,
    resolve: {
      centroCusto: CentroCustoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'CentroCustos'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const centroCustoPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CentroCustoDeletePopupComponent,
    resolve: {
      centroCusto: CentroCustoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'CentroCustos'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
