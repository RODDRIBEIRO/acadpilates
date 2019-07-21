import { HttpResponse } from '@angular/common/http';
import { Component, ElementRef, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IConta } from 'app/shared/model/conta.model';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { Observable } from 'rxjs';
import { ContaService } from './conta.service';

@Component({
  selector: 'jhi-conta-update',
  templateUrl: './conta-update.component.html'
})
export class ContaUpdateComponent implements OnInit {
  isSaving: boolean;
  conta: IConta;

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected contaService: ContaService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ conta }) => {
      this.conta = conta;
      this.init();
      if (!this.conta.id) {
        this.conta.tipo = 0;
        this.conta.situacao = true;
      }
    });
  }
  init() {}

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.conta.id !== undefined) {
      this.subscribeToSaveResponse(this.contaService.update(this.conta));
    } else {
      this.subscribeToSaveResponse(this.contaService.create(this.conta));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IConta>>) {
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
