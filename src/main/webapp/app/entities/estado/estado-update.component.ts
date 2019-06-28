import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IEstado, Estado } from 'app/shared/model/estado.model';
import { EstadoService } from './estado.service';
import { IPais } from 'app/shared/model/pais.model';
import { PaisService } from 'app/entities/pais';

@Component({
  selector: 'jhi-estado-update',
  templateUrl: './estado-update.component.html'
})
export class EstadoUpdateComponent implements OnInit {
  isSaving: boolean;

  pais: IPais[];

  editForm = this.fb.group({
    id: [],
    nomeEstado: [],
    paisId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected estadoService: EstadoService,
    protected paisService: PaisService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ estado }) => {
      this.updateForm(estado);
    });
    this.paisService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPais[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPais[]>) => response.body)
      )
      .subscribe((res: IPais[]) => (this.pais = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(estado: IEstado) {
    this.editForm.patchValue({
      id: estado.id,
      nomeEstado: estado.nomeEstado,
      paisId: estado.paisId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const estado = this.createFromForm();
    if (estado.id !== undefined) {
      this.subscribeToSaveResponse(this.estadoService.update(estado));
    } else {
      this.subscribeToSaveResponse(this.estadoService.create(estado));
    }
  }

  private createFromForm(): IEstado {
    return {
      ...new Estado(),
      id: this.editForm.get(['id']).value,
      nomeEstado: this.editForm.get(['nomeEstado']).value,
      paisId: this.editForm.get(['paisId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEstado>>) {
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

  trackPaisById(index: number, item: IPais) {
    return item.id;
  }
}
