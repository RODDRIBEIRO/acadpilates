import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IEndereco, Endereco } from 'app/shared/model/endereco.model';
import { EnderecoService } from './endereco.service';
import { IEstado } from 'app/shared/model/estado.model';
import { EstadoService } from 'app/entities/estado';

@Component({
  selector: 'jhi-endereco-update',
  templateUrl: './endereco-update.component.html'
})
export class EnderecoUpdateComponent implements OnInit {
  isSaving: boolean;

  estados: IEstado[];

  editForm = this.fb.group({
    id: [],
    logradouro: [],
    numero: [],
    bairro: [],
    complemento: [],
    principal: [],
    estadoId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected enderecoService: EnderecoService,
    protected estadoService: EstadoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ endereco }) => {
      this.updateForm(endereco);
    });
    this.estadoService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IEstado[]>) => mayBeOk.ok),
        map((response: HttpResponse<IEstado[]>) => response.body)
      )
      .subscribe((res: IEstado[]) => (this.estados = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(endereco: IEndereco) {
    this.editForm.patchValue({
      id: endereco.id,
      logradouro: endereco.logradouro,
      numero: endereco.numero,
      bairro: endereco.bairro,
      complemento: endereco.complemento,
      principal: endereco.principal,
      estadoId: endereco.estadoId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const endereco = this.createFromForm();
    if (endereco.id !== undefined) {
      this.subscribeToSaveResponse(this.enderecoService.update(endereco));
    } else {
      this.subscribeToSaveResponse(this.enderecoService.create(endereco));
    }
  }

  private createFromForm(): IEndereco {
    return {
      ...new Endereco(),
      id: this.editForm.get(['id']).value,
      logradouro: this.editForm.get(['logradouro']).value,
      numero: this.editForm.get(['numero']).value,
      bairro: this.editForm.get(['bairro']).value,
      complemento: this.editForm.get(['complemento']).value,
      principal: this.editForm.get(['principal']).value,
      estadoId: this.editForm.get(['estadoId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEndereco>>) {
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

  trackEstadoById(index: number, item: IEstado) {
    return item.id;
  }
}
