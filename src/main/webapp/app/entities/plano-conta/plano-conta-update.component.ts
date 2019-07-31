import { HttpResponse } from '@angular/common/http';
import { Component, ElementRef, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IPlanoConta } from 'app/shared/model/plano-conta.model';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { Observable } from 'rxjs';
import { PlanoContaService } from './plano-conta.service';

@Component({
  selector: 'jhi-plano-conta-update',
  templateUrl: './plano-conta-update.component.html'
})
export class PlanoContaUpdateComponent implements OnInit {
  isSaving: boolean;
  planoConta: IPlanoConta;

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected planoContaService: PlanoContaService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ planoConta }) => {
      this.planoConta = planoConta;
      this.init();
      if (!this.planoConta.id) {
      }
    });
  }
  init() {}

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.planoConta.id !== undefined) {
      this.subscribeToSaveResponse(this.planoContaService.update(this.planoConta));
    } else {
      this.subscribeToSaveResponse(this.planoContaService.create(this.planoConta));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlanoConta>>) {
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
