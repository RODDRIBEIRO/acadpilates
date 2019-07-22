import { HttpResponse } from '@angular/common/http';
import { Component, ElementRef, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IDocumento } from 'app/shared/model/documento.model';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { Observable } from 'rxjs';
import { DocumentoService } from './documento.service';

@Component({
  selector: 'jhi-documento-update',
  templateUrl: './documento-update.component.html'
})
export class DocumentoUpdateComponent implements OnInit {
  isSaving: boolean;
  documento: IDocumento;

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected documentoService: DocumentoService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ documento }) => {
      this.documento = documento;
      this.init();
      if (!this.documento.id) {
        this.documento.situacao = true;
      }
    });
  }
  init() {}

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.documento.id !== undefined) {
      this.subscribeToSaveResponse(this.documentoService.update(this.documento));
    } else {
      this.subscribeToSaveResponse(this.documentoService.create(this.documento));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDocumento>>) {
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
