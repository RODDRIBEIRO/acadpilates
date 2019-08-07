import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, ElementRef, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import * as moment from 'moment';
import { ICentroCusto } from 'app/shared/model/centro-custo.model';
import { IConta } from 'app/shared/model/conta.model';
import { IDocumento } from 'app/shared/model/documento.model';
import { ILancamento } from 'app/shared/model/lancamento.model';
import { IPessoa } from 'app/shared/model/pessoa.model';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { Observable, from } from 'rxjs';
import { CentroCustoService } from '../centro-custo';
import { ContaService } from '../conta';
import { DocumentoService } from '../documento';
import { PessoaService } from '../pessoa';
import { LancamentoService } from './lancamento.service';
//import { Ng2CompleterModule } from 'ng2-completer';

@Component({
  selector: 'jhi-lancamento-update',
  templateUrl: './lancamento-update.component.html'
})
export class LancamentoUpdateComponent implements OnInit {
  isSaving: boolean;
  lancamento: ILancamento;
  pessoas: IPessoa[];
  contas: IConta[];
  documentos: IDocumento[];
  centroCustos: ICentroCusto[];

  dataCompetencia: string;
  dataConciliacao: string;

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected lancamentoService: LancamentoService,
    protected pessoaService: PessoaService,
    protected contaService: ContaService,
    protected documentoService: DocumentoService,
    protected centroCustoService: CentroCustoService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ lancamento }) => {
      this.lancamento = lancamento;
      this.init();
      if (!this.lancamento.id) {
      }
    });
    this.pessoaService.search({ situacao: 1, filter: 'pessoa-is-null', size: 100 }).subscribe(
      (res: HttpResponse<IPessoa[]>) => {
        if (!this.lancamento.pessoaId) {
          this.pessoas = res.body;
        } else {
          this.pessoaService.find(this.lancamento.pessoaId).subscribe(
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
        if (!this.lancamento.contaId) {
          this.contas = res.body;
        } else {
          this.contaService.find(this.lancamento.contaId).subscribe(
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
        if (!this.lancamento.documentoId) {
          this.documentos = res.body;
        } else {
          this.documentoService.find(this.lancamento.documentoId).subscribe(
            (subRes: HttpResponse<IDocumento>) => {
              this.documentos = [subRes.body].concat(res.body);
            },
            (subRes: HttpErrorResponse) => this.onError(subRes.message)
          );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.centroCustoService.search({ situacao: 1, filter: 'centro-custo-is-null', size: 100 }).subscribe(
      (res: HttpResponse<ICentroCusto[]>) => {
        if (!this.lancamento.centroCustoId) {
          this.centroCustos = res.body;
        } else {
          this.centroCustoService.find(this.lancamento.centroCustoId).subscribe(
            (subRes: HttpResponse<ICentroCusto>) => {
              this.centroCustos = [subRes.body].concat(res.body);
            },
            (subRes: HttpErrorResponse) => this.onError(subRes.message)
          );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }
  init() {
    this.dataCompetencia = this.lancamento.dataCompetencia ? this.lancamento.dataCompetencia.format(DATE_FORMAT) : undefined;
    this.dataConciliacao = this.lancamento.dataConciliacao ? this.lancamento.dataConciliacao.format(DATE_FORMAT) : undefined;
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.lancamento.dataCompetencia = moment(this.dataCompetencia, DATE_FORMAT);
    this.lancamento.dataConciliacao = moment(this.dataConciliacao, DATE_FORMAT);
    if (this.lancamento.id !== undefined) {
      this.subscribeToSaveResponse(this.lancamentoService.update(this.lancamento));
    } else {
      this.subscribeToSaveResponse(this.lancamentoService.create(this.lancamento));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILancamento>>) {
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
