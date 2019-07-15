import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPessoa } from 'app/shared/model/pessoa.model';

type EntityResponseType = HttpResponse<IPessoa>;
type EntityArrayResponseType = HttpResponse<IPessoa[]>;

@Injectable({ providedIn: 'root' })
export class PessoaService {
  public resourceUrl = SERVER_API_URL + 'api/pessoas';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/pessoas';

  constructor(protected http: HttpClient) {}

  create(pessoa: IPessoa): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(pessoa);
    return this.http
      .post<IPessoa>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(pessoa: IPessoa): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(pessoa);
    return this.http
      .put<IPessoa>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPessoa>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPessoa[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(pessoa: IPessoa): IPessoa {
    const copy: IPessoa = Object.assign({}, pessoa, {
      dataNascimento: pessoa.dataNascimento != null && pessoa.dataNascimento.isValid() ? pessoa.dataNascimento.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    res.body.dataNascimento = res.body.dataNascimento != null ? moment(res.body.dataNascimento, DATE_FORMAT) : null;
    res.body.dataCadastro = res.body.dataCadastro != null ? moment(res.body.dataCadastro, DATE_TIME_FORMAT) : null;
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    res.body.forEach((pessoa: IPessoa) => {
      pessoa.dataNascimento = pessoa.dataNascimento != null ? moment(pessoa.dataNascimento) : null;
    });

    return res;
  }
  // Criado o serviço de busca do Backend
  search(req?: any): Observable<EntityArrayResponseType> {
    return this.http.get<IPessoa[]>(this.resourceSearchUrl, {
      params: createRequestOption(req),
      observe: 'response'
    });
  }
}
