import { HttpResponse } from '@angular/common/http';
import { Component, ElementRef, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { momentTz } from 'app/shared';
import * as moment from 'moment';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IContato } from 'app/shared/model/contato.model';
import { IEndereco, Endereco } from 'app/shared/model/endereco.model';
import { IPessoa, Pessoa } from 'app/shared/model/pessoa.model';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { Observable } from 'rxjs';
import { PessoaService } from './pessoa.service';

@Component({
  selector: 'jhi-pessoa-update',
  templateUrl: './pessoa-update.component.html'
})
export class PessoaUpdateComponent implements OnInit {
  isSaving: boolean;
  isCadEndereco: boolean;
  isCadContato: boolean;
  itemIndex: number; // Indice do item em edicao

  pessoa: IPessoa;
  endereco: IEndereco;
  contato: IContato;

  dataNascimento: string;
  dataCadastro: string;

  foto?: any;
  fotoContentType?: string;

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected pessoaService: PessoaService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.pessoa = new Pessoa();
    this.activatedRoute.data.subscribe(({ pessoa }) => {
      this.pessoa = pessoa;
      this.dataNascimento = this.pessoa.dataNascimento ? this.pessoa.dataNascimento.format(DATE_FORMAT) : undefined;
      this.dataCadastro = this.pessoa.dataCadastro ? this.pessoa.dataCadastro.format(DATE_TIME_FORMAT) : undefined;
      this.isCadEndereco = false;
      this.isCadContato = false;
      if (!this.pessoa.id) {
        this.init();
      }
    });
  }
  init() {
    this.endereco = {};
    this.contato = {};
    this.pessoa.tipo = 0;
    this.pessoa.categoria = 0;
    this.pessoa.situacao = true;
  }
  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  setFileData(event, entity, field, isImage) {
    this.dataUtils.setFileData(event, entity, field, isImage);
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string) {
    this.dataUtils.clearInputImage(this.pessoa, this.elementRef, field, fieldContentType, idInput);
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.pessoa.dataNascimento = moment(this.dataNascimento, DATE_FORMAT);
    if (this.pessoa.id !== undefined) {
      this.subscribeToSaveResponse(this.pessoaService.update(this.pessoa));
    } else {
      this.subscribeToSaveResponse(this.pessoaService.create(this.pessoa));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPessoa>>) {
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

  addEndereco() {
    if (!this.itemIndex) {
      this.endereco = new Endereco();
      this.pessoa.enderecos.push(Object.assign({}, this.endereco)); // ADD
    } else {
      this.pessoa.enderecos[this.itemIndex] = this.endereco; // UPDATE
    }
    this.itemIndex = undefined;
    this.isCadEndereco = false;
  }

  addContato() {
    if (!this.itemIndex) {
      this.pessoa.contatos.push(Object.assign({}, this.contato)); // ADD
    } else {
      this.pessoa.enderecos[this.itemIndex] = this.endereco; // UPDATE
    }
    this.isCadEndereco = false;
    this.itemIndex = undefined;
  }

  initUpdateEndereco(endereco: IEndereco, index: number) {
    this.endereco = Object.assign({}, endereco);
    this.itemIndex = index;
  }

  initUpdateContato(contato: IContato, index: number) {
    this.contato = Object.assign({}, contato);
    this.itemIndex = index;
  }

  updateEndereco() {
    this.pessoa.enderecos[this.itemIndex] = this.endereco; // UPDATE
    this.itemIndex = undefined;
  }

  updateContato() {
    this.pessoa.contatos[this.itemIndex] = this.contato; // UPDATE
    this.itemIndex = undefined;
  }

  removeEndereco(index: number) {
    this.pessoa.enderecos.splice(index, 1); // REMOVE
    this.itemIndex = undefined;
  }

  removeContato(index: number) {
    this.pessoa.contatos.splice(index, 1); // REMOVE
    this.itemIndex = undefined;
  }

  changePessoaTipo(value: number) {
    if (value) {
      this.pessoa.tipo = value;
    } else {
      this.pessoa.tipo = undefined;
    }
  }
}
