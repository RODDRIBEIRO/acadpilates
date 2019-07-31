import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Component, ElementRef, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ILancamentos } from 'app/shared/model/lancamentos.model';
import { IPessoa } from 'app/shared/model/pessoa.model';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { Observable } from 'rxjs';
import { PessoaService } from '../pessoa';
import { LancamentosService } from './lancamentos.service';
import { IConta } from 'app/shared/model/conta.model';
import { ContaService } from '../conta';
import { IDocumento } from 'app/shared/model/documento.model';
import { ICentroCusto, CentroCusto } from 'app/shared/model/centro-custo.model';
import { DocumentoService } from '../documento';
import { CentroCustoService } from '../centro-custo';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

@Component({
  selector: 'jhi-lancamentos-update',
  templateUrl: './lancamentos-update.component.html'
})
export class LancamentosUpdateComponent implements OnInit {
  isSaving: boolean;
  lancamentos: ILancamentos;
  pessoas: IPessoa[];
  contas: IConta[];
  documentos: IDocumento[];
  centroCustos: ICentroCusto[];

  dataCompetencia: string;
  dataConciliacao: string;

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected lancamentosService: LancamentosService,
    protected pessoaService: PessoaService,
    protected contaService: ContaService,
    protected documentoService: DocumentoService,
    protected centroCustoService: CentroCustoService,
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
    this.pessoaService.search({ situacao: 1, filter: 'pessoa-is-null', size: 100 }).subscribe(
      (res: HttpResponse<IPessoa[]>) => {
        if (!this.lancamentos.pessoaId) {
          this.pessoas = res.body;
        } else {
          this.pessoaService.find(this.lancamentos.pessoaId).subscribe(
            (subRes: HttpResponse<IPessoa>) => {
              this.pessoas = [subRes.body].concat(res.body);
            },
            (subRes: HttpErrorResponse) => this.onError(subRes.message)
          );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.contaService.search({ situacao: 1, filter: 'conta-is-null', size: 100 }).subscribe(
      (res: HttpResponse<IConta[]>) => {
        if (!this.lancamentos.contaId) {
          this.contas = res.body;
        } else {
          this.contaService.find(this.lancamentos.contaId).subscribe(
            (subRes: HttpResponse<IConta>) => {
              this.contas = [subRes.body].concat(res.body);
            },
            (subRes: HttpErrorResponse) => this.onError(subRes.message)
          );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.documentoService.search({ situacao: 1, filter: 'documento-is-null', size: 100 }).subscribe(
      (res: HttpResponse<IDocumento[]>) => {
        if (!this.lancamentos.documentoId) {
          this.documentos = res.body;
        } else {
          this.documentoService.find(this.lancamentos.documentoId).subscribe(
            (subRes: HttpResponse<IDocumento>) => {
              this.documentos = [subRes.body].concat(res.body);
            },
            (subRes: HttpErrorResponse) => this.onError(subRes.message)
          );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }
  init() {
    this.dataCompetencia = this.lancamentos.dataCompetencia ? this.lancamentos.dataCompetencia.format(DATE_FORMAT) : undefined;
    this.dataConciliacao = this.lancamentos.dataConciliacao ? this.lancamentos.dataConciliacao.format(DATE_FORMAT) : undefined;
  }

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

  trackById(index: number, item: any) {
    return item.id;
  }
}
