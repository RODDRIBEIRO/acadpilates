import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IContato, Contato } from 'app/shared/model/contato.model';
import { ContatoService } from './contato.service';
import { IPessoa } from 'app/shared/model/pessoa.model';
import { PessoaService } from 'app/entities/pessoa';

@Component({
  selector: 'jhi-contato-update',
  templateUrl: './contato-update.component.html'
})
export class ContatoUpdateComponent implements OnInit {
  isSaving: boolean;

  pessoas: IPessoa[];

  editForm = this.fb.group({
    id: [],
    tipo: [],
    numero: [],
    ddd: [],
    principal: [],
    pessoaId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected contatoService: ContatoService,
    protected pessoaService: PessoaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ contato }) => {
      this.updateForm(contato);
    });
    this.pessoaService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPessoa[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPessoa[]>) => response.body)
      )
      .subscribe((res: IPessoa[]) => (this.pessoas = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(contato: IContato) {
    this.editForm.patchValue({
      id: contato.id,
      tipo: contato.tipo,
      numero: contato.numero,
      ddd: contato.ddd,
      principal: contato.principal,
      pessoaId: contato.pessoaId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const contato = this.createFromForm();
    if (contato.id !== undefined) {
      this.subscribeToSaveResponse(this.contatoService.update(contato));
    } else {
      this.subscribeToSaveResponse(this.contatoService.create(contato));
    }
  }

  private createFromForm(): IContato {
    return {
      ...new Contato(),
      id: this.editForm.get(['id']).value,
      tipo: this.editForm.get(['tipo']).value,
      numero: this.editForm.get(['numero']).value,
      ddd: this.editForm.get(['ddd']).value,
      principal: this.editForm.get(['principal']).value,
      pessoaId: this.editForm.get(['pessoaId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IContato>>) {
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
