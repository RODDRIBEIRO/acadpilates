import { HttpResponse } from '@angular/common/http';
import { Component, ElementRef, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ILancamentos } from 'app/shared/model/lancamentos.model';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { Observable } from 'rxjs';
import { LancamentosService } from './lancamentos.service';
import { IPessoa } from 'app/shared/model/pessoa.model';

@Component({
  selector: 'jhi-lancamentos-update',
  templateUrl: './lancamentos-update.component.html'
})
export class LancamentosUpdateComponent implements OnInit {
  isSaving: boolean;
  lancamentos: ILancamentos;

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected lancamentosService: LancamentosService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ lancamentos }) => {
      this.lancamentos = lancamentos;
      this.init();
      if (!this.lancamentos.id) {
      }
    });
  }
  init() {}

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.lancamentos.id !== undefined) {
      this.subscribeToSaveResponse(this.lancamentosService.update(this.lancamentos));
    } else {
      this.subscribeToSaveResponse(this.lancamentosService.create(this.lancamentos));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILancamentos>>) {
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

  trackPessoaById(index: number, item: IPessoa) {
    return item.id;
  }
}
