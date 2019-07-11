import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IPessoa, Pessoa } from 'app/shared/model/pessoa.model';
import { PessoaService } from './pessoa.service';
import { IEndereco } from 'app/shared/model/endereco.model';
import { IContato } from 'app/shared/model/contato.model';

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
      }
    });
  }
  init() {
    this.endereco = {};
    this.contato = {};
    this.pessoa.tipo = 0;
    this.pessoa.categoria = 0;
    this.pessoa.situacao = true;
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

  cadastrarEndereco() {
    this.isCadEndereco = true;
  }

  cancelarEndereco() {
    this.isCadEndereco = false;
  }

  cadastrarContato() {
    this.isCadContato = true;
  }

  cancelarContato() {
    this.isCadContato = false;
  }

  addEndereco() {
    this.pessoa.enderecos.push(Object.assign({}, this.endereco)); // ADD
    this.initAddEndereco();
  }

  addContato() {
    this.pessoa.contatos.push(Object.assign({}, this.contato)); // ADD
    this.initAddContato();
  }

  isEnderecoInsert(): boolean {
    return this.itemIndex === undefined;
  }

  isContatoInsert(): boolean {
    return this.itemIndex === undefined;
  }

  isEnderecoUpdate(): boolean {
    return this.itemIndex !== undefined;
  }

  isContatoUpdate(): boolean {
    return this.itemIndex !== undefined;
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
    this.endereco = Object.assign({}, endereco);
    this.itemIndex = index;
  }

  updateEndereco() {
    this.pessoa.enderecos[this.itemIndex] = this.endereco; // UPDATE
    this.initAddEndereco();
  }

  removeEndereco(index: number) {
    this.pessoa.enderecos.splice(index, 1); // REMOVE
    this.initAddEndereco();
  }

  changePessoaTipo(value: number) {
    if (value) {
      this.pessoa.tipo = value;
    } else {
      this.pessoa.tipo = undefined;
    }
  }
}
