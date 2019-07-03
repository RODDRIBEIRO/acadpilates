import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators, FormArray, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IPessoa, Pessoa } from 'app/shared/model/pessoa.model';
import { PessoaService } from './pessoa.service';
import { IEndereco } from 'app/shared/model/endereco.model';

@Component({
  selector: 'jhi-pessoa-update',
  templateUrl: './pessoa-update.component.html'
})
export class PessoaUpdateComponent implements OnInit {
  isSaving: boolean;

  enderecos: FormArray;

  editForm = this.fb.group({
    id: [],
    nome: [],
    tipo: [],
    cpfCnpj: [],
    rgInscEst: [],
    dataCadastro: [],
    dataNascimento: [],
    foto: [],
    fotoContentType: [],
    situacao: [],
    enderecos: this.fb.array([this.addEnderecoGroup()])
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected pessoaService: PessoaService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ pessoa }) => {
      this.updateForm(pessoa);
    });
  }

  updateForm(pessoa: IPessoa) {
    this.editForm.patchValue({
      id: pessoa.id,
      nome: pessoa.nome,
      tipo: pessoa.tipo,
      cpfCnpj: pessoa.cpfCnpj,
      rgInscEst: pessoa.rgInscEst,
      dataCadastro: pessoa.dataCadastro != null ? pessoa.dataCadastro.format(DATE_TIME_FORMAT) : null,
      dataNascimento: pessoa.dataNascimento != null ? pessoa.dataNascimento.format(DATE_TIME_FORMAT) : null,
      foto: pessoa.foto,
      fotoContentType: pessoa.fotoContentType,
      situacao: pessoa.situacao,
      enderecos: this.fb.array([this.addEnderecoGroup()])
    });
  }

  addEnderecoGroup(): FormGroup {
    return this.fb.group({
      logradouro: []
    });
  }

  addEnderecos() {
    this.enderecosArray.push(this.addEnderecoGroup());
  }

  removeEnderecos(index) {
    this.enderecosArray.removeAt(index);
  }

  get enderecosArray() {
    return <FormArray>this.editForm.get('enderecos');
  }

  submitHandler() {
    console.log(this.editForm.value);
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  setFileData(event, field: string, isImage) {
    return new Promise((resolve, reject) => {
      if (event && event.target && event.target.files && event.target.files[0]) {
        const file = event.target.files[0];
        if (isImage && !/^image\//.test(file.type)) {
          reject(`File was expected to be an image but was found to be ${file.type}`);
        } else {
          const filedContentType: string = field + 'ContentType';
          this.dataUtils.toBase64(file, base64Data => {
            this.editForm.patchValue({
              [field]: base64Data,
              [filedContentType]: file.type
            });
          });
        }
      } else {
        reject(`Base64 data was not set as file could not be extracted from passed parameter: ${event}`);
      }
    }).then(
      () => console.log('blob added'), // sucess
      this.onError
    );
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string) {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const pessoa = this.createFromForm();
    if (pessoa.id !== undefined) {
      this.subscribeToSaveResponse(this.pessoaService.update(pessoa));
    } else {
      this.subscribeToSaveResponse(this.pessoaService.create(pessoa));
    }
  }

  private createFromForm(): IPessoa {
    return {
      ...new Pessoa(),
      id: this.editForm.get(['id']).value,
      nome: this.editForm.get(['nome']).value,
      tipo: this.editForm.get(['tipo']).value,
      cpfCnpj: this.editForm.get(['cpfCnpj']).value,
      rgInscEst: this.editForm.get(['rgInscEst']).value,
      dataCadastro:
        this.editForm.get(['dataCadastro']).value != null ? moment(this.editForm.get(['dataCadastro']).value, DATE_TIME_FORMAT) : undefined,
      dataNascimento:
        this.editForm.get(['dataNascimento']).value != null
          ? moment(this.editForm.get(['dataNascimento']).value, DATE_TIME_FORMAT)
          : undefined,
      fotoContentType: this.editForm.get(['fotoContentType']).value,
      foto: this.editForm.get(['foto']).value,
      situacao: this.editForm.get(['situacao']).value,
      enderecos: this.editForm.get(['enderecos']).value
    };
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
}
