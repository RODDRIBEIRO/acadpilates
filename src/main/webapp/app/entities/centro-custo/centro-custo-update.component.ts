import { HttpResponse } from '@angular/common/http';
import { Component, ElementRef, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ICentroCusto } from 'app/shared/model/centro-custo.model';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { Observable } from 'rxjs';
import { CentroCustoService } from './centro-custo.service';

@Component({
  selector: 'jhi-centro-custo-update',
  templateUrl: './centro-custo-update.component.html'
})
export class CentroCustoUpdateComponent implements OnInit {
  isSaving: boolean;
  centroCusto: ICentroCusto;

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected centroCustoService: CentroCustoService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ centroCusto }) => {
      this.centroCusto = centroCusto;
      this.init();
      if (!this.centroCusto.id) {
        this.centroCusto.situacao = true;
      }
    });
  }
  init() {}

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.centroCusto.id !== undefined) {
      this.subscribeToSaveResponse(this.centroCustoService.update(this.centroCusto));
    } else {
      this.subscribeToSaveResponse(this.centroCustoService.create(this.centroCusto));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICentroCusto>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
