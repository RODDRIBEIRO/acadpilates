import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PlanoConta } from 'app/shared/model/plano-conta.model';
import { PlanoContaService } from './plano-conta.service';
import { PlanoContaComponent } from './plano-conta.component';
import { PlanoContaDetailComponent } from './plano-conta-detail.component';
import { PlanoContaUpdateComponent } from './plano-conta-update.component';
import { PlanoContaDeletePopupComponent } from './plano-conta-delete-dialog.component';
import { IPlanoConta } from 'app/shared/model/plano-conta.model';

@Injectable({ providedIn: 'root' })
export class PlanoContaResolve implements Resolve<IPlanoConta> {
  constructor(private service: PlanoContaService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPlanoConta> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<PlanoConta>) => response.ok),
        map((planoConta: HttpResponse<PlanoConta>) => planoConta.body)
      );
    }
    return of(new PlanoConta());
  }
}

export const planoContaRoute: Routes = [
  {
    path: '',
    component: PlanoContaComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'PlanoContas'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PlanoContaDetailComponent,
    resolve: {
      planoConta: PlanoContaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PlanoContas'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PlanoContaUpdateComponent,
    resolve: {
      planoConta: PlanoContaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PlanoContas'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PlanoContaUpdateComponent,
    resolve: {
      planoConta: PlanoContaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PlanoContas'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const planoContaPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PlanoContaDeletePopupComponent,
    resolve: {
      planoConta: PlanoContaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PlanoContas'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
