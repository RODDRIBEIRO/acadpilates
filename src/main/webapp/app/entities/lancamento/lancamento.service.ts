import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import * as moment from 'moment';
import { createRequestOption } from 'app/shared';
import { ILancamento } from 'app/shared/model/lancamento.model';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

type EntityResponseType = HttpResponse<ILancamento>;
type EntityArrayResponseType = HttpResponse<ILancamento[]>;

@Injectable({ providedIn: 'root' })
export class LancamentoService {
  public resourceUrl = SERVER_API_URL + 'api/lancamento';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/lancamento';

  constructor(protected http: HttpClient) {}

  create(lancamento: ILancamento): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(lancamento);
    return this.http
      .post<ILancamento>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(lancamento: ILancamento): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(lancamento);
    return this.http
      .put<ILancamento>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }
  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ILancamento>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ILancamento[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
  protected convertDateFromClient(lancamento: ILancamento): ILancamento {
    const copy: ILancamento = Object.assign({}, lancamento, {
      dataCompetencia:
        lancamento.dataCompetencia != null && lancamento.dataCompetencia.isValid() ? lancamento.dataCompetencia.toJSON() : null,
      dataConciliacao:
        lancamento.dataConciliacao != null && lancamento.dataConciliacao.isValid() ? lancamento.dataConciliacao.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    res.body.dataCompetencia = res.body.dataCompetencia != null ? moment(res.body.dataCompetencia, DATE_FORMAT) : null;
    res.body.dataConciliacao = res.body.dataConciliacao != null ? moment(res.body.dataConciliacao, DATE_FORMAT) : null;
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    res.body.forEach((lancamento: ILancamento) => {
      lancamento.dataCompetencia = lancamento.dataCompetencia != null ? moment(lancamento.dataCompetencia) : null;
      lancamento.dataConciliacao = lancamento.dataConciliacao != null ? moment(lancamento.dataConciliacao) : null;
    });

    return res;
  }

  // Criado o serviço de busca do Backend
  search(req?: any): Observable<EntityArrayResponseType> {
    return this.http.get<ILancamento[]>(this.resourceSearchUrl, {
      params: createRequestOption(req),
      observe: 'response'
    });
  }
}
