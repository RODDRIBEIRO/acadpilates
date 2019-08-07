import { HttpResponse } from '@angular/common/http';
import { Component, ElementRef, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IContato } from 'app/shared/model/contato.model';
import { IEndereco } from 'app/shared/model/endereco.model';
import { IPessoa } from 'app/shared/model/pessoa.model';
import * as moment from 'moment';
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
    this.activatedRoute.data.subscribe(({ pessoa }) => {
      this.pessoa = pessoa;
      this.init();
      if (!this.pessoa.id) {
        this.pessoa.enderecos = [];
        this.pessoa.contatos = [];
        this.pessoa.tipo = 0;
        this.pessoa.categoria = 0;
        this.pessoa.situacao = true;
      }
    });
  }
  init() {
    this.endereco = {};
    this.contato = {};
    this.dataNascimento = this.pessoa.dataNascimento ? this.pessoa.dataNascimento.format(DATE_FORMAT) : undefined;
    this.dataCadastro = this.pessoa.dataCadastro ? this.pessoa.dataCadastro.format(DATE_TIME_FORMAT) : undefined;
    this.isCadEndereco = false;
    this.isCadContato = false;
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
  enderecoCadButton() {
    this.isCadEndereco = true;
  }
  contatoCadButton() {
    this.isCadContato = true;
  }
  addEndereco() {
    if (this.itemIndex === undefined) {
      this.pessoa.enderecos.push(Object.assign({}, this.endereco)); // ADD
    } else {
      this.pessoa.enderecos[this.itemIndex] = this.endereco; // UPDATE
    }
    this.initAddEndereco();
    this.isCadEndereco = false;
  }

  addContato() {
    if (this.itemIndex === undefined) {
      this.pessoa.contatos.push(Object.assign({}, this.contato)); // ADD
    } else {
      this.pessoa.contatos[this.itemIndex] = this.contato; // UPDATE
    }
    this.initAddContato();
    this.isCadContato = false;
  }

  cancelarBtnEndereco() {
    this.initAddEndereco();
    this.isCadEndereco = false;
  }

  cancelarBtnContato() {
    this.initAddContato();
    this.isCadContato = false;
  }

  initAddEndereco() {
    this.itemIndex = undefined;
    this.endereco = {};
  }

  initAddContato() {
    this.itemIndex = undefined;
    this.contato = {};
  }

  initUpdateEndereco(endereco: IEndereco, index: number) {
    this.isCadEndereco = true;
    this.endereco = Object.assign({}, endereco);
    this.itemIndex = index;
  }

  initUpdateContato(contato: IContato, index: number) {
    this.isCadContato = true;
    this.contato = Object.assign({}, contato);
    this.itemIndex = index;
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
