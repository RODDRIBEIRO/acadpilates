import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Lancamentos } from 'app/shared/model/lancamentos.model';
import { LancamentosService } from './lancamentos.service';
import { LancamentosComponent } from './lancamentos.component';
import { LancamentosDetailComponent } from './lancamentos-detail.component';
import { LancamentosUpdateComponent } from './lancamentos-update.component';
import { LancamentosDeletePopupComponent } from './lancamentos-delete-dialog.component';
import { ILancamentos } from 'app/shared/model/lancamentos.model';

@Injectable({ providedIn: 'root' })
export class LancamentosResolve implements Resolve<ILancamentos> {
  constructor(private service: LancamentosService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ILancamentos> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Lancamentos>) => response.ok),
        map((lancamentos: HttpResponse<Lancamentos>) => lancamentos.body)
      );
    }
    return of(new Lancamentos());
  }
}

export const lancamentosRoute: Routes = [
  {
    path: '',
    component: LancamentosComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'Lancamentos'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LancamentosDetailComponent,
    resolve: {
      lancamentos: LancamentosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Lancamentos'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LancamentosUpdateComponent,
    resolve: {
      lancamentos: LancamentosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Lancamentos'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LancamentosUpdateComponent,
    resolve: {
      lancamentos: LancamentosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Lancamentos'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const lancamentosPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: LancamentosDeletePopupComponent,
    resolve: {
      lancamentos: LancamentosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Lancamentos'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
